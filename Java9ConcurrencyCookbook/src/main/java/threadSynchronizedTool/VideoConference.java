package threadSynchronizedTool;

import java.util.concurrent.CountDownLatch;

public class VideoConference implements Runnable {
    public static void main(String[] args) {
        VideoConference videoConference = new VideoConference(10);
        new Thread(videoConference).start();
        for (int i = 0; i < 10; i++) {
            Participant participant = new Participant(videoConference, "participant " + i);
            new Thread(participant).start();
        }
    }
    private final CountDownLatch controller;

    public VideoConference(int size) {
        controller = new CountDownLatch(size);
    }

    @Override
    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants.%n", controller.getCount());
        try {
            controller.await();
            System.out.printf("VideoConference: All the participants have came%n");
            System.out.printf("VideoConference: let's start %n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void arrive(String name) {
        System.out.printf("%s has arrived", name);
        controller.countDown();
        System.out.printf("VideoConference: Waiting for %d participants.%n", controller.getCount());
    }
}
