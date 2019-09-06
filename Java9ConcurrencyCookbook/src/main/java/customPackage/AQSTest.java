package customPackage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest<V> {
    public static void main(String[] args) throws Exception {
        ReentrantLock lock = new ReentrantLock(true);

        Runnable task = () -> {
            lock.lock();
            System.out.printf("%s上 锁%n", Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.printf("%s解锁", Thread.currentThread().getName());
            }
        };

        Runnable task_b = () -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s上 锁%n", Thread.currentThread().getName());
            lock.unlock();

        };
        Thread a = new Thread(task);
        a.start();

        TimeUnit.SECONDS.sleep(1);
        Thread b = new Thread(task_b);
        b.start();

        a.join();
        b.join();
    }

    int state;
    Node head;
    Node tail;

    private Node enq(final Node node) {
        for (; ; ) {
            Node tail = this.tail;
            if (tail == null) {
                // 并没有把新来的节点作为第一个节点啊。。默认了一个空节点
                if (compareAndSetHead(new Node())) ;
                tail = head;
            } else {
                node.prev = tail;
                if (compareAndSetTail(tail, node)) {
                    tail.next = node;
                    return tail;
                }
            }
        }
    }

    private boolean compareAndSetTail(Node tail, Node node) {
        return false;
    }

    private boolean compareAndSetHead(Node node) {
        if (head == null) {
            head = node;
            return true;
        } else {
            Node preHead = this.head;
            preHead.prev = node;
            node.next = preHead;
            return true;
        }
    }

    private class Node {
        Node prev;
        Node next;
    }
}
