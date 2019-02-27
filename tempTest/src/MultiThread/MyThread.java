package MultiThread;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MyThread implements Runnable{
    private List<Object> list;

    private CountDownLatch countDownLatch;


    public MyThread(List<Object> list, CountDownLatch countDownLatch)
    {
        this.list = list;
        this.countDownLatch = countDownLatch;
    }

    public void run()
    {
        // 每个线程向List中添加100个元素
        for(int i = 0; i < 100; i++)
        {
            list.add(new Object());
        }

        // 完成一个子线程
        countDownLatch.countDown();
    }
}
