package me.xiaoying.secure.function;

import me.xiaoying.secure.file.FileService;
import me.xiaoying.secure.task.TaskService;

public interface Function {
    boolean enable();
    void onEnable();
    void onDisable();
    void stop();

    String getName();
    String getAliasName();
    String getDescription();

    FileService getFileService();
//    TaskService getTaskService();
}