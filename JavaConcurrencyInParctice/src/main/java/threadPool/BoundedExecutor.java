package threadPool;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.*;

@ThreadSafe
public class BoundedExecutor {
    private static final int CAPACITY = 30;
    private static int N_threads = 10;

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                N_threads
                , N_threads
                , 0L
                , TimeUnit.MILLISECONDS
                , new LinkedBlockingQueue<Runnable>(CAPACITY)
                , new ThreadPoolExecutor.CallerRunsPolicy());


    }

    private final Executor executor;
    private final Semaphore semaphore;

    public BoundedExecutor(Executor executor, Semaphore semaphore) {
        this.executor = executor;
        this.semaphore = semaphore;
    }

    public void submitTask(final Runnable command) throws InterruptedException {
        semaphore.acquire();
        try {
            executor.execute(() -> {
                try {
                    command.run();
                } finally {
                    semaphore.release();
                }
            });
        } catch (RejectedExecutionException e) {
            semaphore.release();
        }
    }
}
