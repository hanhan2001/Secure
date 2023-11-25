package me.xiaoying.secure.function.functions;

import me.xiaoying.secure.Application;
import me.xiaoying.secure.constant.CommonConstant;
import me.xiaoying.secure.constant.ConfigConstant;
import me.xiaoying.secure.file.FileService;
import me.xiaoying.secure.function.Function;
import me.xiaoying.secure.task.TaskService;
import me.xiaoying.secure.utils.PasswordUtil;

import java.io.IOException;

public class ResetPasswordFunction implements Function {
    @Override
    public boolean enable() {
        return true;
    }

    @Override
    public void onEnable() {
        Application.getLogger().info("AutoReset password function is enabled!");

        TaskService taskService = new TaskService();

        taskService.execute(() -> {
            while(true) {
                String[] spl = PasswordUtil.getRandomPwd(24).split("");

                int ran = (int) (Math.random() * 25);
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < spl.length; i++) {
                    if (ran == i)
                        stringBuilder.append("live");

                    stringBuilder.append(spl[i]);
                }

                String password = stringBuilder.toString();
                CommonConstant.PASSWORD = password;

                try {
                    Runtime.getRuntime().exec("net user administrator " + password);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Application.getLogger().info("密码重置为: " + password);
                try {
                    synchronized(this) {
                        wait(ConfigConstant.SET_RESET_TIME);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void onDisable() {
        Application.getLogger().info("AutoReset password function is disabled");
    }

    @Override
    public void stop() {

    }

    @Override
    public String getName() {
        return "AutoResetPassword";
    }

    @Override
    public String getAliasName() {
        return "自动重置密码";
    }

    @Override
    public String getDescription() {
        return "定时重置密码";
    }

    @Override
    public FileService getFileService() {
        return null;
    }
}
