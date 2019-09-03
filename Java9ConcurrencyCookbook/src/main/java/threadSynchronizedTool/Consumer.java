package threadSynchronizedTool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Consumer implements Runnable {
    public static void main(String[] args) {
        List<String> buffer1 = new ArrayList<>();
        Exchanger<List<String>> exchanger = new Exchanger<>();
        Producer producer = new Producer(buffer1, exchanger);
        Consumer consumer = new Consumer(exchanger);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    private final Exchanger<List<String>> exchanger;

    public Consumer(Exchanger<List<String>> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        List<String> buffer = null;
        for (int cycle = 1; cycle <= 10; cycle++) {
            System.out.printf("Consumer: cycle %d %n", cycle);
            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer:" + buffer.size());
            for (int i = 0; i < 10; i++) {
                // 这里不能拿i作为下标，因为这个list里的值不一定十个地方全都有。。。
                String message = buffer.get(0);
                System.out.println("Consumer:" + message);
                buffer.remove(0);
            }
        }

    }
}
