package threadManagement;

import java.util.Date;
import java.util.Deque;

public class CleanerTask extends Thread {
    private Deque<Event> deque;

    public CleanerTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (; ; ) {
            Date date = new Date();
            clean(date);
        }
    }

    private void clean(Date date) {
        long difference;
        boolean delete;
        if (deque.size() == 0) {
            return;
        }
        delete = false;
        do {
            Event last = deque.getLast();
            difference = date.getTime() - last.getDate().getTime();
            if (difference > 10000) {
                System.out.printf("Cleaner: %s %n", last.getEvent());
                deque.removeLast();
                delete = true;
            }
        } while (difference > 10000);
        if (delete) {
            System.out.printf(" cleaner: size of the queue %d %n", deque.size());
        }
    }
}
