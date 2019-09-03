package threadExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class FactorialCalculator implements Callable<Integer> {
    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> futures = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int nextInt = random.nextInt(10);
            FactorialCalculator factorialCalculator = new FactorialCalculator(nextInt);
            Future<Integer> submit = executorService.submit(factorialCalculator);
            futures.add(submit);
        }

        do {
            System.out.printf("Main : number of completed task %d%n", executorService.getCompletedTaskCount());
            for (int i = 0; i < futures.size(); i++) {
                Future<Integer> future = futures.get(i);
                System.out.printf("Main: task %d is %s %n", i, future.isDone());
            }
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (executorService.getCompletedTaskCount() < futures.size());

        System.out.printf("Main: result %n");
        for (int i = 0; i < futures.size(); i++) {
            Future<Integer> future = futures.get(i);
            Integer num = null;
            try {
                num = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.printf("Main: task %d is %d %n", i, num);
        }
        executorService.shutdown();
    }

    private final Integer number;

    public FactorialCalculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if (result == 0 || result == 1) {
            result = 1;
        } else {
            for (int i = 2; i < number; i++) {
                result *= 2;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        System.out.printf("%s : %d %n", Thread.currentThread().getName(), result);
        return result;
    }
}
