package me.xiaoying.secure.constant;

/**
 * 常量 Config.yml
 */
public class ConfigConstant {
    public static String SERVER_HOST = "0.0.0.0";
    public static String SET_PASSWORD_ENCRYPT = "SHA256",
            SET_PASSWORD_PASSWORD;

    public static String SET_DATEFORMAT = "yyyy/MM/dd-HH:mm:ss",
            SET_DATA_TYPE;

    public static Long SET_RESET_TIME = 1800000L;

    public static String MYSQL_HOST = "jdbc:mysql://localhost",
            MYSQL_DATABASE = "livegetweb",
            MYSQL_USERNAME = "root",
            MYSQL_PASSWORD = "root";

    public static String SQLITE_PATH = "livegetweb.db";

    public static int MYSQL_PORT = 3306,
            MYSQL_RECONNECT_TIME = 10,
            MYSQL_RECONNECT_DELAY = 3;

    public static int SERVER_PORT = 33214, WEBSOCKET_PORT = 33215;
}