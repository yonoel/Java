package buildBasicModule;

import java.util.concurrent.*;

public class TestBlockQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(10);

        Thread one = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                queue.put(11);
                System.out.println("take 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        one.start();
        Thread two = new Thread(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        if (!queue.offer(121, 1000L, TimeUnit.MILLISECONDS)) {
            queue.take();
        }

    }
}
