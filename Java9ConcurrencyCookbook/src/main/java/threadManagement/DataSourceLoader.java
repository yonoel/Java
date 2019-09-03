package threadManagement;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataSourceLoader implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new DataSourceLoader());
        Thread thread1 = new Thread(new NetWorkConnectionsLoader());
        thread.start();
        thread1.start();

        try {
            thread.join(1000);
//            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main : configuration has been loaded %s %n",new Date());
    }

    @Override
    public void run() {
        System.out.printf(" beginning data sources loading %s %n", new Date());
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf(" data source loading has finished %s %n", new Date());
    }
}
