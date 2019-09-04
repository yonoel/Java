package concurrentCollection;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayTask implements Runnable {
    public static void main(String[] args) throws Exception {
        DelayQueue<DelayEvent> queue = new DelayQueue<>();

        Thread[] threads = new Thread[5];

        for (int i = 0; i < threads.length; i++) {
            DelayTask delayTask = new DelayTask(i + 1, queue);
            threads[i] = new Thread(delayTask);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        do {
            int counter = 0;
            DelayEvent event;
            do {
                event = queue.poll();
                if (event != null) {
                    counter++;
                }
            } while (event != null);
            System.out.printf("at %s you have read %d events %n", new Date(), counter);
            TimeUnit.MILLISECONDS.sleep(500);
        } while (queue.size() > 0);
    }

    private final int id;
    private final DelayQueue<DelayEvent> queue;

    public DelayTask(int id, DelayQueue<DelayEvent> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        Date now = new Date();
        Date delay = new Date();
        delay.setTime(now.getTime() + (id * 1000));
        System.out.printf("Thread %s %s %n", id, delay);

        for (int i = 0; i < 100; i++) {
            DelayEvent event = new DelayEvent(delay);
            queue.add(event);
        }
    }
}
