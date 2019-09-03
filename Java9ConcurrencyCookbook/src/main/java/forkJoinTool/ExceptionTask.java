package forkJoinTool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ExceptionTask extends RecursiveTask<Integer> {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        int[] array = new int[100];
        ExceptionTask exceptionTask = new ExceptionTask(array, 0, 100);

        pool.execute(exceptionTask);

        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (exceptionTask.isCompletedAbnormally()) {
            System.out.println("Main an exception has occurred ");
            System.out.printf("%s %n", exceptionTask.getException());
        }
        System.out.printf(" Result %d", exceptionTask.join());
    }

    private int[] array;
    private int start, end;

    public ExceptionTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        System.out.printf("Task: start from %d to %d %n", start, end);
        if (end - start < 10) {
            if (3 > start && 3 < end) {
                throw new RuntimeException("this task thrown an exception from " + start + " to " + end);
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            int mid = (start + end) / 2;
            ExceptionTask task1 = new ExceptionTask(array, start, mid);
            ExceptionTask task2 = new ExceptionTask(array, mid, end);
            invokeAll(task1, task2);
            System.out.printf("task result from %d to %d : %d %n", start, mid, task1.join());
            System.out.printf("task result from %d to %d : %d %n", mid, end, task2.join());
        }
        System.out.printf("task result from %d to %d  %n", start, end);
        return 0;
    }
}
