package threadManagement;

import java.util.Random;

public class ThreadGroupTask implements Runnable {
    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors() * 2;
        MyThreadGroup myThreadGroup = new MyThreadGroup("MyThreadGroup");
        ThreadGroupTask task = new ThreadGroupTask();
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(myThreadGroup,task);
            thread.start();
        }
        System.out.printf(" number of threads %d %n", myThreadGroup.activeCount());
        System.out.printf(" information about the thread group %n");
        myThreadGroup.list();

        Thread[] threads = new Thread[myThreadGroup.activeCount()];
        myThreadGroup.enumerate(threads);
        for (int i = 0; i < myThreadGroup.activeCount(); i++) {
            System.out.printf(" thread %s :%s %n", threads[i].getName(), threads[i].getState());
        }
    }

    @Override
    public void run() {
        int result;
        Random random = new Random(Thread.currentThread().getId());
        for (; ; ) {
            result = 1000 / (int) (random.nextDouble() * 1000000000);
            if (Thread.currentThread().isInterrupted()) {
                System.out.printf(" %d : interrupted %n", Thread.currentThread().getId());
                return;
            }
        }
    }
}
