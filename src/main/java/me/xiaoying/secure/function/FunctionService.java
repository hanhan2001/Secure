package me.xiaoying.secure.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FunctionService {
    Stack<Function> functions = new Stack<>();

    /**
     * 注册 功能
     *
     * @param function 功能
     */
    public void registerFunction(Function function) {
        if (this.functions.contains(function))
            return;

        this.functions.add(function);
    }

    /**
     * 取消注册 功能
     *
     * @param function 功能
     */
    public void unregisterFunction(Function function) {
        if (!this.functions.contains(function))
            return;

        function.onDisable();
    }

    /**
     * 开启 功能
     *
     * @param function 功能
     */
    public void enableFunction(Function function) {
        if (!this.functions.contains(function))
            return;

        function.onEnable();
    }

    /**
     * 卸载 功能
     *
     * @param function 功能
     */
    public void disableFunction(Function function) {
        if (!this.functions.contains(function))
            return;

        function.onDisable();
    }

    /**
     * 开启 所有功能
     */
    public void enableFunctions() {
        this.functions.forEach(Function::onEnable);
    }

    /**
     * 关闭 所有功能
     */
    public void disableFunctions() {
        this.functions.forEach(Function::onDisable);
    }

    /**
     * 获取 所有功能
     *
     * @return ArrayList
     */
    public List<Function> getFunctions() {
        return new ArrayList<>(this.functions);
    }

    /**
     * 获取 所有功能
     *
     * @param clazz 获取类型
     * @return Object
     */
    public Object getFunctions(Class<?> clazz) {
        if (clazz == Stack.class)
            return this.functions;
        if (clazz == List.class)
            return new ArrayList<>(this.functions);

        return this.functions;
    }
}