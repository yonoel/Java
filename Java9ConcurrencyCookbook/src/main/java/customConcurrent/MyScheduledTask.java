package customConcurrent;

import java.util.Date;
import java.util.concurrent.*;

public class MyScheduledTask<V> extends FutureTask<V> implements RunnableScheduledFuture<V> {
    private RunnableScheduledFuture<V> task;
    private ScheduledThreadPoolExecutor executor;
    private long period;
    private long startDate;

    public MyScheduledTask(Runnable runnable, V result, RunnableScheduledFuture<V> task, ScheduledThreadPoolExecutor executor) {
        super(runnable, result);
        this.task = task;
        this.executor = executor;
    }

    @Override
    public void run() {
        if (isPeriodic() && !executor.isShutdown()) {
            startDate = new Date().getTime() + period;
            executor.getQueue().add(this);
        }
        System.out.printf("Pre-MyScheduledTask %s %n", new Date());
        System.out.printf("MyScheduledTask is periodic %s %n", isPeriodic());
        runAndReset();
        System.out.printf("Post-MyScheduledTask %s %n", new Date());
    }

    @Override
    public boolean isPeriodic() {
        return task.isPeriodic();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        if (isPeriodic()) {
            return task.getDelay(unit);
        } else {
            if (startDate == 0) {
                return task.getDelay(unit);
            } else {
                Date date = new Date();
                return unit.convert(startDate - date.getTime(), TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override
    public int compareTo(Delayed o) {
        return task.compareTo(o);
    }

    public void setPeriod(long period) {
        this.period = period;
    }
}
