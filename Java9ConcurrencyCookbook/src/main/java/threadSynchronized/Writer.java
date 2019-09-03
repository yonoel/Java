package threadSynchronized;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Writer implements Runnable {
    public static void main(String[] args) {
        PriceInfo priceInfo = new PriceInfo();
        Reader[] readers = new Reader[5];
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            readers[i] = new Reader(priceInfo);
            threads[i] = new Thread(readers[i]);
        }

        Thread writeThread = new Thread(new Writer(priceInfo));
        for (int i = 0; i < 5; i++) {
            threads[i].start();
        }
        writeThread.start();
    }

    private PriceInfo priceInfo;

    public Writer(PriceInfo priceInfo) {
        this.priceInfo = priceInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.printf(" %s : Writer : attempt to modify the price.%n", new Date());
            priceInfo.setPrices(Math.random() * 10, Math.random() * 8);
            System.out.printf("%s:Writer: prices has been modified.%n", new Date());
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
