package concurrentTest;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class TestingThreadFactory implements ThreadFactory {
    public final AtomicInteger numCreated = new AtomicInteger();
    private final ThreadFactory factory = Executors.defaultThreadFactory();

    @Override
    public Thread newThread(Runnable r) {
        numCreated.incrementAndGet();
        return factory.newThread(r);
    }

    @Test
    public void testPoolExpansion() throws InterruptedException {
        TestingThreadFactory threadFactory = new TestingThreadFactory();
        int MAX = 10;
        ExecutorService service = Executors.newFixedThreadPool(MAX);
        for (int i = 0; i < 10 * MAX; i++) {
            service.execute(() -> {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        for (int i = 0; i < 20 && threadFactory.numCreated.get() < MAX; i++) {
            Thread.sleep(100);
        }
        service.shutdown();
    }
}
