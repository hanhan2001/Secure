package me.xiaoying.secure.file;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件管理
 */
public class FileService {
    private final Map<String, SubFile> files = new HashMap<>();

    /**
     * 新建所有文件
     */
    public void fileAll() {
        for (SubFile file : this.files.values())
            file.newFile();
    }

    /**
     * 新建文件
     *
     * @param name 注册文件名
     */
    public void file(String name) {
        this.files.get(name.toUpperCase()).newFile();
    }

    /**
     * 删除所有文件
     */
    public void delAll() {
        for (SubFile file : this.files.values())
            file.delFile();
    }

    /**
     * 删除文件
     *
     * @param name 注册文件名
     */
    public void del(String name) {
        this.files.get(name.toUpperCase()).delFile();
    }

    /**
     * 初始化文件
     *
     * @param name 注册文件名
     */
    public void init(String name) {
        this.files.get(name.toUpperCase()).initFile();
    }

    /**
     * 初始化所有文件
     */
    public void initAll() {
        for (SubFile file : this.files.values())
            file.initFile();
    }

    /**
     * 注册文件
     *
     * @param name 注册文件名
     * @param file 文件对象
     */
    public void register(String name, SubFile file) {
        this.files.put(name.toUpperCase(), file);
    }

    /**
     * 取消注册文件
     *
     * @param name 注册文件名
     */
    public void unregister(String name) {
        this.files.remove(name);
    }

    /**
     * 取消注册所有文件
     */
    public void unregisters() {
        this.files.clear();
    }
}