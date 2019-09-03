package threadSynchronizedTool;

import java.util.concurrent.Phaser;

public class MyPhaser extends Phaser {
    public static void main(String[] args) {
        MyPhaser myPhaser = new MyPhaser();

        Student[] students = new Student[5];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(myPhaser);
            myPhaser.register();
        }

        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(students[i], "Student -" + i);
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Main: the pahser has finished %s %n",myPhaser.isTerminated());
    }

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0:
                return studentsArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    private boolean finishExam() {
        System.out.printf("Phaser: All students has finished the exam.%n");
        System.out.printf("Phaser: thank you for your time.%n");
        return true;
    }

    private boolean finishSecondExercise() {
        System.out.printf("Phaser: All students has finished the second exercise.%n");
        System.out.printf("Phaser: it's turn for the third one.%n");
        return false;
    }

    private boolean finishFirstExercise() {
        System.out.printf("Phaser: All students has finished the first exercise.%n");
        System.out.printf("Phaser: it's turn for the second one.%n");
        return false;
    }

    private boolean studentsArrived() {
        System.out.printf("Phaser: the exam are going to start.the students are ready.%n");
        System.out.printf("Phaser: we have %d students.%n", getRegisteredParties());
        return false;
    }
}
