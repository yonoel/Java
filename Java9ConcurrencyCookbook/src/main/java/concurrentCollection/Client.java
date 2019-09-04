package concurrentCollection;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Client implements Runnable {
    public static void main(String[] args) throws Exception {
        LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>();
        Client client = new Client(deque);
        Thread thread = new Thread(client);
        thread.start();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                String take = deque.take();
                System.out.println("Main remove " + take + " at " + new Date() + " .size is " + deque.size());
            }
            TimeUnit.MILLISECONDS.sleep(300);
        }
        System.out.println("Main over");
    }

    private final LinkedBlockingDeque<String> deque;

    public Client(LinkedBlockingDeque<String> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(i).append(':').append(j);
                try {
                    deque.put(stringBuilder.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Client add " + stringBuilder.toString() +" "+ new Date());
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Client is over");
    }
}
