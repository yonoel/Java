package threadSynchronizedTool;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Student implements Runnable {
    private Phaser phaser;

    public Student(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.printf("%s :has arrived to do the exam. %s %n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s :is going to do the first exercise. %s %n", Thread.currentThread().getName(), new Date());
        doExercise();
        System.out.printf("%s :has done the first exercise. %s %n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s :is going to do the second exercise. %s %n", Thread.currentThread().getName(), new Date());
        doExercise();
        System.out.printf("%s :has done the second exercise. %s %n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();

        System.out.printf("%s :is going to do the third exercise. %s %n", Thread.currentThread().getName(), new Date());
        doExercise();
        System.out.printf("%s :has finish the exam. %s %n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();


    }

    private void doExercise() {
        try {
            long dur = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(dur);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
