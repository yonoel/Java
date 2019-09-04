package concurrentCollection;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class AddTask implements Runnable {
    private final ConcurrentLinkedDeque<String> deque;

    public AddTask(ConcurrentLinkedDeque<String> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10_000; i++) {
            deque.add(name+": element "+i);
        }
    }
}
