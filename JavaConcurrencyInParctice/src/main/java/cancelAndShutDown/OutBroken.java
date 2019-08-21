package cancelAndShutDown;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OutBroken {
    private static final ScheduledExecutorService cancelService = Executors.newScheduledThreadPool(3);

    public static void timedRun(Runnable runnable, long timeout , TimeUnit timeUnit){
        Thread thread = Thread.currentThread();

        cancelService.schedule(()->{
            thread.interrupt();
        },timeout,timeUnit);

        runnable.run();
    }
}
