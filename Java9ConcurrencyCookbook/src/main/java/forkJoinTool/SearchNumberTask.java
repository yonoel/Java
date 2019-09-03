package forkJoinTool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class SearchNumberTask extends RecursiveTask<Integer> {
    public static void main(String[] args) {
        ArrayGenerator generator = new ArrayGenerator();
        int[] generateArray = generator.generateArray(1000);

        TaskManager manager = new TaskManager();
        ForkJoinPool pool = new ForkJoinPool();

        SearchNumberTask task = new SearchNumberTask(generateArray, 0, 1000, 5, manager);
        pool.execute(task);

        pool.shutdown();
        try {
            pool.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private int numbers[];
    private int start, end;
    private int number;
    private TaskManager taskManager;
    private final static int NOT_FOUND = -1;

    public SearchNumberTask(int[] numbers, int start, int end, int number, TaskManager taskManager) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.number = number;
        this.taskManager = taskManager;
    }

    @Override
    protected Integer compute() {
        System.out.println("task :" + start + " : " + end);
        int ret;
        if (end - start > 10) ret = launchTasks();
        else ret = lookForNumber();
        return ret;
    }

    private int lookForNumber() {
        for (int i = start; i < end; i++) {
            if (numbers[i] == number) {
                System.out.printf("Task: number %d found in position %d %n", number, i);
                taskManager.cancelTask(this);
                return i;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return NOT_FOUND;
    }

    private int launchTasks() {
        int mid = (start + end) / 2;
        SearchNumberTask task1 = new SearchNumberTask(numbers, start, mid, number, taskManager);
        SearchNumberTask task2 = new SearchNumberTask(numbers, start, mid, number, taskManager);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        task1.fork();
        task2.fork();
        int ret;
        ret = task1.join();
        if (ret != NOT_FOUND)
            return ret;
        return task2.join();
    }

    public void logCancelMessage() {
        System.out.printf("Task: canceled task from %d to %d%n", start, end);
    }
}
