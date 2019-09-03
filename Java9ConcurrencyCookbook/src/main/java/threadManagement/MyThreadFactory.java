package threadManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MyThreadFactory implements ThreadFactory {
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
        Thread thread;
        System.out.printf(" starting the threads %n");
        for (int i = 0; i < 10; i++) {
            thread = factory.newThread(task);
            thread.start();
        }
        System.out.printf(" factory stats : %n");
        System.out.printf(" %s %n", factory.getStats());
    }

    private int counter;
    private String name;
    private List<String> stats;

    public MyThreadFactory(String name) {
        this.name = name;
        counter = 0;
        stats = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, name + "-Thread_" + counter);
        counter++;
        stats.add(String.format(" created thread %d with name %s on %s %n"
                , thread.getId(), thread.getName(), new Date()));
        return thread;
    }

    public String getStats() {
        return stats.stream().collect(Collectors.joining("\n"));
    }
}
