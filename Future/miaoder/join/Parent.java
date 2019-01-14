package com.miaoder.join;

import javafx.concurrent.Worker;

import java.util.concurrent.CountDownLatch;

public class Parent {
    public static void main(String[] args) throws Exception {
        System.out.println("main start");
        String[] array = new String[100];
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            array[i] = "name" + i;
        }
        final String[] arrCopy = array.clone();
        for (String str:arrCopy){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(str+"run");
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        /*Thread total = new Thread(new Runnable() {
            @Override
            public void run() {
                for (String str : arrCopy) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                System.out.println(str);

                            } catch (
                                    Exception e
                            ) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }).start();
                }
            }
        });
        total.start();
        while (total.isAlive()) {
            total.join();
        }*/
        //等待t1结束，这时候t1线程并未启动(上面的数组好像太快了。。)
        System.out.println("main end");
    }
}
