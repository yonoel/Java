package threadSynchronizedTool;

public class PrintJob implements Runnable {
    public static void main(String[] args) {
        PrintQueue queue = new PrintQueue();
        Thread[] threads = new Thread[12];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new PrintJob(queue),"PrintThread"+i);
        }
        for (int i = 0; i < 12; i++) {
            threads[i].start();
        }
    }
    private PrintQueue queue;

    public PrintJob(PrintQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.printf("%s:Going to print a job %n", Thread.currentThread().getName());
        queue.printJob(new Object());
        System.out.printf("%s: the doc has been printed%n", Thread.currentThread().getName());
    }
}
