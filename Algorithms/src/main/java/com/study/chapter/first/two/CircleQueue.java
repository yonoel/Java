package com.study.chapter.first.two;
// 1.3.30 环形链表结构的queue
public class CircleQueue<T> {
    private Node last;
    private Node first;
    private class Node{
        private T t;
        private Node next;
    }

    public CircleQueue(T t) {
        Node newLast = new Node();
        last.t = t;
        this.last = last;
    }
}
