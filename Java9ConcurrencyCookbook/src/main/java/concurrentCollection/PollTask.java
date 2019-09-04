package concurrentCollection;

import java.util.concurrent.ConcurrentLinkedDeque;

public class PollTask implements Runnable {
    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> deque = new ConcurrentLinkedDeque<>();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            AddTask addTask = new AddTask(deque);
            threads[i] = new Thread(addTask);
            threads[i].start();
        }

        System.out.println("Main 100 addTask threads have been started");

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(" deque's size is " + deque.size());


        for (int i = 0; i < threads.length; i++) {
            PollTask pollTask = new PollTask(deque);
            threads[i] = new Thread(pollTask);
            threads[i].start();
        }
        System.out.println("Main 100 pollTask have been started");

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("deque's size " + deque.size());
    }

    private final ConcurrentLinkedDeque<String> deque;

    public PollTask(ConcurrentLinkedDeque<String> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            deque.pollFirst();
            deque.pollLast();
        }
    }
}
