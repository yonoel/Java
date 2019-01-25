package com.study.chapter.first;

import java.util.Iterator;

// 1.3.33
public class Deque<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }

    private Node head;
    private Node tail;
    private int N;

    private class Node {
        private Node pre;
        private Node next;
        private T t;

        public Node(T t) {
            this.t = t;
        }
    }

    boolean isEmpty() {
        return N == 0;
    }

    int site() {
        return N;
    }

    void pushLeft(T t) {
        Node newHead = new Node(t);
        newHead.next = head;
        if (N == 0) head = tail;
        else head.pre = newHead;
        head = newHead;
        N++;
    }

    void pushRight(T t) {
        Node newTail = new Node(t);
        newTail.pre = tail;
        if (isEmpty()) tail = head;
        else tail.next = newTail;
        tail = newTail;
        N++;
    }

    T popLeft() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException();
        Node ret = head;
        head = ret.next;
        if (N == 1) tail = null;
        else head.pre = null;
        N--;
        return ret.t;
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.pushLeft("1");
        deque.popLeft();
    }
}
