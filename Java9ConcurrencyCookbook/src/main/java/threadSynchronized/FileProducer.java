package threadSynchronized;

public class FileProducer implements Runnable {
    public static void main(String[] args) {
        FileMock mock = new FileMock(100, 10);
        Buffer buffer = new Buffer(20);
        Thread thread = new Thread(new FileProducer(mock, buffer));

        FileConsumer[] consumers = new FileConsumer[3];
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            consumers[i] = new FileConsumer(buffer);
            threads[i] = new Thread(consumers[i], "Consumer " + i);
        }
        thread.start();
        for (int i = 0; i < 3; i++) {
            threads[i].start();
        }

    }

    private FileMock mock;
    private Buffer buffer;

    public FileProducer(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.setPendingLines(true);
        while (mock.hasMoreLines()) {
            String line = mock.getLine();
            buffer.insert(line);
        }
        buffer.setPendingLines(false);
    }
}
