package customConcurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.TimeUnit;

public class MyWorkerThread extends ForkJoinWorkerThread {
    public static void main(String[] args) {
        MyWorkerThreadFactory factory = new MyWorkerThreadFactory();
        ForkJoinPool pool = new ForkJoinPool(4, factory, null, false);

        int array[] = new int[10_0000];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }

        MyRecursiveTask recursiveTask = new MyRecursiveTask(array, 0, 10_0000);

        pool.execute(recursiveTask);

        recursiveTask.join();
        pool.shutdown();
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("result is " + recursiveTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private final static ThreadLocal<Integer> taskCounter = new ThreadLocal<>();

    protected MyWorkerThread(ForkJoinPool pool) {
        super(pool);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.printf("MyWorkThread %d initializing task counter %n", getId());
        taskCounter.set(0);
    }

    @Override
    protected void onTermination(Throwable exception) {
        System.out.printf("MyWorkThread %d %d %n", getId(), taskCounter.get());
        super.onTermination(exception);
    }

    public void addTask() {
        taskCounter.set(taskCounter.get() + 1);
    }
}
