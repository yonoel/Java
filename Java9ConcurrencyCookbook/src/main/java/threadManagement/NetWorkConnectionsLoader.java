package threadManagement;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NetWorkConnectionsLoader implements Runnable {
    @Override
    public void run() {
        System.out.printf(" beginning net work connect  %s %n", new Date());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf(" net work connect has finished %s %n", new Date());
    }
}
