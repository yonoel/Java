package threadSynchronized;

import java.util.Date;

public class Reader implements Runnable {
    private PriceInfo priceInfo;

    public Reader(PriceInfo priceInfo) {
        this.priceInfo = priceInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.printf("%s : %s Price 1: %f %n",new Date(),Thread.currentThread().getName(),priceInfo.getPrice1());
            System.out.printf("%s : %s Price 2: %f %n",new Date(),Thread.currentThread().getName(),priceInfo.getPrice2());
        }
    }
}
