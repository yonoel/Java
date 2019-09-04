package customConcurrent;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MyPriorityTransferQueue<E> extends PriorityBlockingQueue<E> implements TransferQueue<E> {
    public static void main(String[] args) throws Exception {
        MyPriorityTransferQueue<Event> events = new MyPriorityTransferQueue<>();
        Producer producer = new Producer(events);
        Thread[] p_Thread = new Thread[10];

        for (int i = 0; i < p_Thread.length; i++) {
            p_Thread[i] = new Thread(producer);
            p_Thread[i].start();
        }

        Consumer consumer = new Consumer(events);
        Thread c_thread = new Thread(consumer);
        c_thread.start();
        System.out.printf("main: buffer consumer count %d%n", events.getWaitingConsumerCount());

        Event core_event = new Event("Core event", 0);
        events.transfer(core_event);
        System.out.println("transfer core event");

        for (int i = 0; i < p_Thread.length; i++) {
            p_Thread[i].join();
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.printf("main: buffer consumer count %d%n", events.getWaitingConsumerCount());
        core_event = new Event("Core event2", 0);
        events.transfer(core_event);
        c_thread.join();


    }

    // counter是消费数量
    private final AtomicInteger counter;
    private final LinkedBlockingDeque<E> transferred;
    private final ReentrantLock lock;

    public MyPriorityTransferQueue() {
        counter = new AtomicInteger(0);
        transferred = new LinkedBlockingDeque<>();
        lock = new ReentrantLock();
    }

    // 传递给消费者
    @Override
    public boolean tryTransfer(E e) {
        boolean val = false;
        try {
            lock.lock();
            if (counter.get() == 0) {
                val = false;
            } else {
                put(e);
                val = true;
            }
        } finally {
            lock.unlock();
        }
        return val;
    }

    @Override
    public void transfer(E e) throws InterruptedException {
        lock.lock();
        if (counter.get() != 0) {
            try {
                put(e);
            } finally {
                lock.unlock();
            }
        } else {
            // 没有消费者，把对象塞到队列里，并且阻塞对象的线程
            try {
                transferred.add(e);
            } finally {
                lock.unlock();
            }
            synchronized (e) {
                e.wait();
            }
        }

    }

    @Override
    public boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        lock.lock();
        if (counter.get() != 0) {
            try {
                put(e);
            } finally {
                lock.unlock();
            }
            return true;
        } else {
            long newTimeout = 0;
            try {
                transferred.add(e);
                newTimeout = TimeUnit.MILLISECONDS.convert(timeout, unit);
            } finally {
                lock.unlock();
            }
            e.wait(newTimeout);
            lock.lock();
            boolean val;
            try {
                if (transferred.contains(e)) {
                    transferred.remove(e);
                    val = false;
                } else {
                    val = true;
                }
            } finally {
                lock.unlock();
            }
            return val;
        }
    }

    @Override
    public boolean hasWaitingConsumer() {
        return counter.get() != 0;
    }

    @Override
    public int getWaitingConsumerCount() {
        return counter.get();
    }

    @Override
    public E take() throws InterruptedException {
        lock.lock();
        try {
            counter.incrementAndGet();
            E poll = transferred.poll();
            if (poll == null) {
                lock.unlock();
                poll = super.take();
                lock.lock();
            } else {
                synchronized (poll) {
                    poll.notify();
                }
            }
            counter.decrementAndGet();
            return poll;
        } finally {
            lock.unlock();
        }
    }
}
