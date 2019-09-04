package customConcurrent;

public class Consumer implements Runnable {
    private final MyPriorityTransferQueue<Event> buffer;

    public Consumer(MyPriorityTransferQueue<Event> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1002; i++) {
            try {
                Event take = buffer.take();
                System.out.printf("consumer: %s %d %n",take.getThread(),take.getPriority());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
