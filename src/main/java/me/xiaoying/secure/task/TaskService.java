package me.xiaoying.secure.task;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskService {
    Map<String, Task> knownTask = new HashMap<>();

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(200);

    public void scheduleRepeating(Runnable runnable, long l1) {
        this.executor.schedule(runnable, l1, TimeUnit.MILLISECONDS);
    }

    public void scheduleSyncDelayedTask(Runnable runnable, long l1) {
        this.executor.scheduleWithFixedDelay(runnable, 0, l1, TimeUnit.MILLISECONDS);
    }

    public void execute(Runnable runnable) {
        this.executor.execute(runnable);
    }

    public void registerTask(String name, Task task) {

    }
}