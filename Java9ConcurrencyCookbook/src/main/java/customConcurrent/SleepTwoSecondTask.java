package customConcurrent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class SleepTwoSecondTask implements Callable<String> {
    public static void main(String[] args) {
        MyExecutor myExecutor = new MyExecutor(4, 8, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());

        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            SleepTwoSecondTask task = new SleepTwoSecondTask();
            futures.add(myExecutor.submit(task));
        }

        for (int i = 0; i < 5; i++) {
            try {
                String result = futures.get(i).get();
                System.out.printf("main result for task %d %s %n", i, result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        myExecutor.shutdown();

        for (int i = 5; i < 10; i++) {
            try {
                String result = futures.get(i).get();
                System.out.printf("main result for task %d %s %n", i, result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        try {
            myExecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        return new Date().toString();
    }
}
