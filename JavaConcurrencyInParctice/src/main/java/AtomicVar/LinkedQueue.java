package AtomicVar;

import java.util.concurrent.atomic.AtomicReference;

public class LinkedQueue<E> {
    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<>(next);
        }
    }

    private final Node<E> dummy = new Node<>(null, null);
    private final AtomicReference<Node<E>> head = new AtomicReference<>(dummy);
    private final AtomicReference<Node<E>> tail = new AtomicReference<>(dummy);

    public boolean put(E item) {
        Node<E> newNode = new Node<>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if (curTail == tailNext) {
                // 当前的尾节点为尾节点的下一个节点（当前处于中间状态，推进尾节点）
                tail.compareAndSet(curTail, tailNext);
            } else {
                // 处于稳定状态，尝试插入
                if (curTail.next.compareAndSet(null, newNode)) {
//                    插入成功，推进尾节点
                    tail.compareAndSet(curTail, newNode);
                    return true;
                }
            }
        }
    }

}
