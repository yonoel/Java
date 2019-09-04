package customConcurrent;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class MyExecutor extends ThreadPoolExecutor {
    private final ConcurrentHashMap<Runnable, Date> startTimes;

    public MyExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.startTimes = new ConcurrentHashMap<>();
    }

    @Override
    public void shutdown() {
        System.out.println("MyExecutor:Going to shutdown.");
        System.out.println("MyExecutor:Executed tasks  " + getCompletedTaskCount());
        System.out.println("MyExecutor:Running tasks  " + getActiveCount());
        System.out.println("MyExecutor:Pending tasks  " + getQueue().size());
        super.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        System.out.println("MyExecutor:Executed tasks  " + getCompletedTaskCount());
        System.out.println("MyExecutor:Running tasks  " + getActiveCount());
        System.out.println("MyExecutor:Pending tasks  " + getQueue().size());
        return super.shutdownNow();
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.printf("MyExecutor a task is beginning %s %s %n", t.getName(), t.hashCode());
        startTimes.put(r, new Date());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        Future<?> future = (Future<?>) r;
        try {
            System.out.println("*********************");
            System.out.println("a task is finishing");
            System.out.println("result is " + future.get());
            Date start = startTimes.remove(r);
            Date finish = new Date();
            long diff = finish.getTime() - start.getTime();
            System.out.println(" duration is " + diff);
            System.out.println("*********************");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
