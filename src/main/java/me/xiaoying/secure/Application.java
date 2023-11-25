package me.xiaoying.secure;

import me.xiaoying.mf.SqlFactory;
import me.xiaoying.mf.SqlType;
import me.xiaoying.secure.constant.ConfigConstant;
import me.xiaoying.secure.file.FileService;
import me.xiaoying.secure.file.files.FileConfig;
import me.xiaoying.secure.function.FunctionService;
import me.xiaoying.secure.function.functions.ResetPasswordFunction;
import me.xiaoying.secure.websocket.Websocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.InetSocketAddress;
import java.sql.Connection;

@SpringBootApplication
public class Application {
    private static final FileService fileService = new FileService();
    private static final FunctionService functionService = new FunctionService();
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("正在初始化...");
        initialize();
        logger.info("初始化完成!");

        System.getProperties().put("server.port", ConfigConstant.SERVER_PORT);

        // 网页服务
        logger.info("启动网页服务...");
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run();
        logger.info("网页服务启动完成!");

        // websocket 前后通讯
        logger.info("启动通信服务...");
        new Thread(() -> {
            Websocket websocket = new Websocket(new InetSocketAddress(ConfigConstant.WEBSOCKET_PORT));
            websocket.setConnectionLostTimeout(0);
            websocket.run();
        });
        logger.info("通信服务启动完成!");

        logger.info("加载 Function...");
        loadFunction();
        logger.info("Function 加载完成!");
    }

    // 初始化
    public static void initialize() {
        fileService.register("Config", new FileConfig());
        logger.info("初始化配置文件");
        fileService.fileAll();
        fileService.initAll();

        logger.info("配置服务地址及端口");
        System.getProperties().put("server.address", ConfigConstant.SERVER_HOST);
        System.getProperties().put("server.port", ConfigConstant.SERVER_PORT);
        logger.info("设置服务地址: " + ConfigConstant.SERVER_HOST + ":" + ConfigConstant.SERVER_PORT);

        // 密码加密方式过滤
        if (!ConfigConstant.SET_PASSWORD_ENCRYPT.equalsIgnoreCase("SHA256") && !ConfigConstant.SET_PASSWORD_ENCRYPT.equalsIgnoreCase("BASE64") && !ConfigConstant.SET_PASSWORD_ENCRYPT.equalsIgnoreCase("MD5")) {
            logger.error("错误的密码加密方式: " + ConfigConstant.SET_PASSWORD_ENCRYPT + ", 仅支持 base64/MD5/SHA256");
            System.exit(0);
        }
        logger.info("选用密码加密方式: " + ConfigConstant.SET_PASSWORD_ENCRYPT);

        // 数据存储方式过滤
        if (!ConfigConstant.SET_DATA_TYPE.equalsIgnoreCase("MYSQL") && !ConfigConstant.SET_DATA_TYPE.equalsIgnoreCase("SQLITE")) {
            logger.error("错误的数据存储方式: " + ConfigConstant.SET_DATA_TYPE + ", 仅支持 MYSQL/SQLite");
            System.exit(0);
        }
        logger.info("选用数据存储方式: " + ConfigConstant.SET_DATA_TYPE);

        if (ConfigConstant.SET_DATA_TYPE.equalsIgnoreCase("MYSQL")) {
            for (int i = 0; i < ConfigConstant.MYSQL_RECONNECT_TIME; i++) {
                try {
                    Connection connection = getSqlFactory().getConnection();
                    if (!connection.isClosed())
                        break;
                } catch (Exception e) {
                    logger.warn("无法连接至 Mysql 服务，正在尝试重连(" + (i + 1) + "/" + ConfigConstant.MYSQL_RECONNECT_TIME + ")");
                    Robot robot = null;
                    try {
                        robot = new Robot();
                    } catch (AWTException ex) {
                        throw new RuntimeException(ex);
                    }
                    robot.delay(ConfigConstant.MYSQL_RECONNECT_DELAY * 1000);

                    if (i == ConfigConstant.MYSQL_RECONNECT_TIME - 1)
                        System.exit(0);
                }
            }
        }

        // 初始表
        getSqlFactory().type(SqlType.CREATE)
                .table("secure")
                .create("account", "varchar", 255)
                .create("password", "varchar", 255)
                .run();
    }

    public static void loadFunction() {
        functionService.registerFunction(new ResetPasswordFunction());
        functionService.enableFunctions();
    }

    public static FileService getFileService() {
        return fileService;
    }

    public static FunctionService getFunctionService() {
        return functionService;
    }

    public static SqlFactory getSqlFactory() {
        SqlFactory sqlFactory = null;
        switch (ConfigConstant.SET_DATA_TYPE.toUpperCase()) {
            case "MYSQL":
                sqlFactory = new SqlFactory(ConfigConstant.MYSQL_HOST, ConfigConstant.MYSQL_PORT, ConfigConstant.MYSQL_DATABASE, ConfigConstant.MYSQL_USERNAME, ConfigConstant.MYSQL_PASSWORD);
                break;
            case "SQLITE":
                sqlFactory = new SqlFactory(ConfigConstant.MYSQL_HOST, ConfigConstant.SQLITE_PATH);
                break;
        }
        return sqlFactory;
    }

    public static Logger getLogger() {
        return logger;
    }
}