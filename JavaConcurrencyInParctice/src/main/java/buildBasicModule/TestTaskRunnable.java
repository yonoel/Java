package buildBasicModule;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class TestTaskRunnable implements Runnable {
    final BlockingQueue<Runnable> tasks = new LinkedBlockingDeque<>(20);

    public TestTaskRunnable() {
    }

    public void add(Runnable runnable) {
        try {
            tasks.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        for (; ; )
            try {
                tasks.take().run();
            } catch (Exception e) {
//                System.out.println(1);
                System.out.println(e.toString());
            }
    }
}
