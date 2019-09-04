package customConcurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory {
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        MyThreadFactory test = new MyThreadFactory("test");

        Thread thread = test.newThread(task);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread);
    }

    private AtomicInteger counter;
    private String prefix;

    public MyThreadFactory(String prefix) {
        this.prefix = prefix;
        counter = new AtomicInteger(1);
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyThread(r, prefix + "-" + counter.getAndIncrement());
    }
}
