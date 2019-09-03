package threadManagement;

public class Task implements Runnable {
    public static void main(String[] args) {
        Task task = new Task();
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }

    @Override
    public void run() {
        int numero = Integer.parseInt("TTT");
    }
}
