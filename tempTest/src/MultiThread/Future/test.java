package MultiThread.Future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class test {
    public static void main(String[] args) {
        FutureTask<String> task = new FutureTask<String>(() ->
                "你好"
        );
        try {
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
