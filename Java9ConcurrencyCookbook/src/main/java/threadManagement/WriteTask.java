package threadManagement;

import java.io.Writer;
import java.util.Date;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

public class WriteTask implements Runnable {
    public static void main(String[] args) {
        ConcurrentLinkedDeque<Event> events = new ConcurrentLinkedDeque<>();
        WriteTask write = new WriteTask(events);
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            Thread thread = new Thread(write);
            thread.start();
        }
        CleanerTask cleanerTask = new CleanerTask(events);
        cleanerTask.setDaemon(true);
        cleanerTask.start();
    }

    private Deque<Event> deque;

    public WriteTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent(String.format(" the thread %s has generated an event", Thread.currentThread().getId()));

            deque.addFirst(event);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
