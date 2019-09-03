package threadExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PrintTask implements Callable<Result> {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<PrintTask> taskList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            taskList.add(new PrintTask("task -" + i));
        }

        List<Future<Result>> futures = null;

        try {
            futures = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();

        System.out.println("Main printing the results");
        for (int i = 0; i < futures.size(); i++) {
            Future<Result> future = futures.get(i);
            try {
                Result result = future.get();
                System.out.println(result.getName() + " : " + result.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private final String name;

    public PrintTask(String name) {
        this.name = name;
    }

    @Override
    public Result call() throws Exception {
        System.out.printf("%s starting%n", this.name);
        try {
            long duration = (long) Math.random() * 10;
            System.out.printf("%s waiting %d seconds for results%n", this.name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int value = 0;
        for (int i = 0; i < 5; i++) {
            value += (int) Math.random() * 100;
        }
        Result result = new Result();
        result.setName(this.name);
        result.setValue(value);
        System.out.println(this.name + "end.");
        return result;
    }
}
