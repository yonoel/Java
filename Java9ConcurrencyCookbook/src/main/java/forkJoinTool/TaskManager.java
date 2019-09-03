package forkJoinTool;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ForkJoinTask;

public class TaskManager {
    private final ConcurrentLinkedDeque<SearchNumberTask> tasks;

    public TaskManager() {
        tasks = new ConcurrentLinkedDeque<>();
    }
    public void addTask(SearchNumberTask task){
        tasks.add(task);
    }

    public void cancelTask(SearchNumberTask task){
        for (SearchNumberTask task1 : tasks){
            if (task != task1){
                task1.cancel(true);
                task1.logCancelMessage();
            }
        }
    }
}
