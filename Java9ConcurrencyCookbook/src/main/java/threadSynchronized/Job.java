package threadSynchronized;


public class Job implements Runnable {
    public static void main(String[] args) {
        System.out.printf("Running example with fair-mode=false %n");
        testPrintQueue(false);
        System.out.printf("Running example with fair-mode=true %n");
        testPrintQueue(true);
    }

    private static void testPrintQueue(boolean b) {
        PrintQueue printQueue = new PrintQueue(b);
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(printQueue), "Thread " + i);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private PrintQueue queue;

    public Job(PrintQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.printf("%s :going to print a doc %n", Thread.currentThread().getName());
        queue.printJob(new Object());
        System.out.printf("%s :the doc has been printed %n", Thread.currentThread().getName());
    }
}
