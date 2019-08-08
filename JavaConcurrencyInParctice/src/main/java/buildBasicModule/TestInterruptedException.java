package buildBasicModule;

import java.util.concurrent.BlockingQueue;

public class TestInterruptedException {
    public static void main(String[] args) {
//        test1();
        TestTaskRunnable taskRunnable = new TestTaskRunnable();
        Thread work = new Thread(taskRunnable);
        work.start();
        taskRunnable.add(()->{
            System.out.println(1);
        });
        taskRunnable.add(()->{
            int i = 1/0;
        });
        taskRunnable.add(()->{
            System.out.println(2);
        });
        try {
            Thread.sleep(200000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test1() {
        Thread one = new Thread(() -> {
            try {
                for (; ; ) {
                    Boolean a = Thread.currentThread().isInterrupted();
                    System.out.println("in run() - about to sleep for 20 seconds-------" + a);
                    Thread.sleep(20000);
                    System.out.println("in run() - woke up");
                }
            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
                //如果不加上这一句，那么cd将会都是false，因为在捕捉到InterruptedException异常的时候就会自动的中断标志置为了false
                Boolean c = Thread.interrupted();
                Boolean d = Thread.interrupted();
                System.out.println("c=" + c);
                System.out.println("d=" + d);
//                throw new RuntimeException(e);
                System.out.println(1);
            }
        });
        one.start();

        //主线程休眠2秒，从而确保刚才启动的线程有机会执行一段时间
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("in main() - interrupting other thread");
        //中断线程t
        one.interrupt();
        System.out.println("in main() - leaving");
        try {
            Thread.sleep(2000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
