package customConcurrent;

import java.util.Date;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("task begin");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task end");
        };
        MyScheduledThreadPoolExecutor executor = new MyScheduledThreadPoolExecutor(4);
        System.out.printf("now %s %n" ,new Date());
        executor.schedule(task, 1, TimeUnit.SECONDS);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("now %s %n" ,new Date());
        executor.scheduleAtFixedRate(task,1,3,TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    public MyScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize);
    }

    @Override
    protected <V> RunnableScheduledFuture<V> decorateTask(Runnable runnable, RunnableScheduledFuture<V> task) {
        MyScheduledTask<V> myScheduledTask = new MyScheduledTask<>(runnable, null, task, this);
        return myScheduledTask;
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        ScheduledFuture<?> task = super.scheduleAtFixedRate(command, initialDelay, period, unit);
        MyScheduledTask<?> myScheduledTask = (MyScheduledTask<?>) task;
        myScheduledTask.setPeriod(TimeUnit.MICROSECONDS.convert(period, unit));
        return myScheduledTask;
    }
}
