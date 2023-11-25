package me.xiaoying.secure.file.files;

import me.xiaoying.secure.configuration.YamlConfiguration;
import me.xiaoying.secure.constant.ConfigConstant;
import me.xiaoying.secure.file.SubFile;
import me.xiaoying.secure.utils.SystemUtil;

import java.io.File;

public class FileConfig extends SubFile {
    public static YamlConfiguration configuration;
    File file = new File(SystemUtil.getSystemPath(), "Config.yml");

    @Override
    public void newFile() {
        if (!this.file.exists()) saveResource("Config.yml", false);
        configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    @Override
    public void delFile() {
        this.file.delete();
    }

    @Override
    public void initFile() {
        ConfigConstant.SERVER_HOST = this.getString(ConfigConstant.SERVER_HOST, "Server.Host");
        ConfigConstant.SERVER_PORT = this.getInt(ConfigConstant.SERVER_PORT, "Server.Port");
        ConfigConstant.WEBSOCKET_PORT = this.getInt(ConfigConstant.WEBSOCKET_PORT, "Server.Websocket.Port");

        ConfigConstant.SET_PASSWORD_ENCRYPT = this.getString(ConfigConstant.SET_PASSWORD_ENCRYPT, "Set.Password.Encrypt");

        ConfigConstant.SET_PASSWORD_PASSWORD = configuration.getString("Set.Password.Password");
        ConfigConstant.SET_DATA_TYPE = configuration.getString("Set.Data");
        ConfigConstant.SET_RESET_TIME = configuration.getLong("Set.ResetTime");

        ConfigConstant.MYSQL_HOST = this.getString(ConfigConstant.MYSQL_HOST, "Set.Mysql.Host");
        ConfigConstant.MYSQL_PORT = this.getInt(ConfigConstant.MYSQL_PORT, "Set.Mysql.Port");
        ConfigConstant.MYSQL_DATABASE = this.getString(ConfigConstant.MYSQL_DATABASE, "Set.Mysql.Database");
        ConfigConstant.MYSQL_USERNAME = this.getString(ConfigConstant.MYSQL_USERNAME, "Set.Mysql.Username");
        ConfigConstant.MYSQL_PASSWORD = this.getString(ConfigConstant.MYSQL_PASSWORD, "Set.Mysql.Password");

        ConfigConstant.MYSQL_RECONNECT_TIME = this.getInt(ConfigConstant.MYSQL_RECONNECT_TIME, "Set.Mysql.ReconnectTime");
        ConfigConstant.MYSQL_RECONNECT_DELAY = this.getInt(ConfigConstant.MYSQL_RECONNECT_DELAY, "Set.Mysql.ReconnectDelay");

        ConfigConstant.SET_DATEFORMAT = this.getString(ConfigConstant.SET_DATEFORMAT, "Set.DataFormat");
        ConfigConstant.SQLITE_PATH = this.getString(ConfigConstant.SQLITE_PATH, "Set.SQLite.Path");
    }

    private String getString(String object, String key) {
        if (configuration.getString(key) != null && !configuration.getString(key).equalsIgnoreCase("MemorySection[path='" + key + "', root='YamlConfiguration']"))
            object = configuration.getString(key);

        return object;
    }

    private int getInt(int object, String key) {
        if (configuration.getInt(key) != 0)
            object = configuration.getInt(key);

        return object;
    }
}