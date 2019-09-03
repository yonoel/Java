package threadExecutor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
    private final Date initDate;
    private final String name;

    public Task(String name) {
        this.name = name;
        initDate = new Date();
    }

    @Override
    public void run() {
        System.out.printf("%s: task %s created on: %s %n", Thread.currentThread().getName(), name, initDate);
        System.out.printf("%s: task %s started on: %s %n", Thread.currentThread().getName(), name, new Date());

        try {
            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: task %s doing a task during%d %n", Thread.currentThread().getName(), name, duration);

            TimeUnit.SECONDS.sleep(duration);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("%s: task %s finished on: %s %n", Thread.currentThread().getName(), name, new Date());

    }
}
