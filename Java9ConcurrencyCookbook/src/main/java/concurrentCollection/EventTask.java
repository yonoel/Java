package concurrentCollection;

import java.util.concurrent.PriorityBlockingQueue;

public class EventTask implements Runnable {
    public static void main(String[] args) {
        PriorityBlockingQueue<Event> events = new PriorityBlockingQueue<>();

        Thread[] threads = new Thread[5];

        for (int i = 0; i < threads.length; i++) {
            EventTask eventTask = new EventTask(i, events);
            threads[i] = new Thread(eventTask);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Main queue size is " + events.size());

        for (int i = 0; i < threads.length * 1000; i++) {
            Event event = events.poll();
            System.out.printf("Thread %s ,priority is %d %n",event.getThread(),event.getPriority());
        }

        System.out.println("Main queue size is " + events.size());
    }

    private final int id;
    private final PriorityBlockingQueue<Event> queue;

    public EventTask(int id, PriorityBlockingQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Event event = new Event(id, i);
            queue.add(event);
        }
    }
}
