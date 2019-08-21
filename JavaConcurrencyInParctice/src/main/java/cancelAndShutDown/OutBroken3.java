package cancelAndShutDown;

import java.util.concurrent.*;

public class OutBroken3 {
    private static final ScheduledExecutorService cancelService = Executors.newScheduledThreadPool(3);

    public static void timedRun(final Runnable runnable, long timeout, TimeUnit timeUnit) throws InterruptedException {
        Future task = cancelService.submit(runnable);
        try {
            task.get(timeout, timeUnit);
        } catch (ExecutionException e) {
            e.printStackTrace();
            // throw it
        } catch (TimeoutException e) {
            e.printStackTrace();
            // cancel
        } finally {
            task.cancel(true);
        }
    }
}
