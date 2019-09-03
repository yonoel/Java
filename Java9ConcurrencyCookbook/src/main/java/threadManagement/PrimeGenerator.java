package threadManagement;

public class PrimeGenerator extends Thread {
    public static void main(String[] args) {
        PrimeGenerator task = new PrimeGenerator();
        task.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.interrupt();
        // 这里直接输出。。还活着，因为需要时间来停止线程?官方说的是，线程没执行到中断判断和处理中断。
        System.out.printf("Main : Status of thread %s %n",task.getState());
        System.out.printf("Main : isInterrupted %s %n",task.isInterrupted());
        System.out.printf("Main : is alive %s %n",task.isAlive());
    }
    @Override
    public void run() {
        long num = 1L;
        for (; ; ) {
            if (Calculator.isPrime(num)) System.out.printf("Number %d is prime %n", num);
            if (isInterrupted()) {
                System.out.printf("The prime generator has been interrupted ");
                return;
            }
            num++;
        }
    }


}
