package com.study.chapter.first.ThirdSection;

import java.util.Optional;

// 1.3.30 环形链表结构的queue
public class CircleQueue<T> {
    private Node last;
    private int N;

    public class Node {
        private T t;
        private Node next;
    }

    public CircleQueue(T t) {
        Node newLast = new Node();
        newLast.t = t;
        newLast.next = newLast;
        last = newLast;
        N++;
    }

    public CircleQueue() {
    }

    boolean isEmpty() {
        return N == 0;
    }

    void enqueue(T t) {
        Node newNode = new Node();
        newNode.t = t;
        if (isEmpty()) {
            last = newNode;
        } else {
            newNode.next = last;
            Node temp = last;
            if (temp.next != null) {
                while (temp.next != last) {
                    temp = temp.next;
                }
            }
            temp.next = newNode;
            last = newNode;
        }
        N++;
    }

    T deleteByPosition(int K) {
        if (N < 2) throw new ArrayIndexOutOfBoundsException();
        Node temp = last;
        Node pre = null;
        for (int i = 1; i < K; i++) {
            if (i == K - 1) pre = temp;
            temp = temp.next;
        }
        pre.next = temp.next;
        if (temp == last) {
            last = temp.next;
        }
        N--;
        return temp.t;
    }

    T dequeue() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException();
        Node pre = last.next;
        T out = last.t;
        if (pre != null) {

        } else {
            last = null;
        }
        N--;
        return out;
    }

    public static void main(String[] args) {
        CircleQueue<String> circleQueue = new CircleQueue<>();
        for (int i = 0; i < 7; i++) {
            circleQueue.enqueue(String.valueOf(i));

        }
//        System.out.println(circleQueue.deleteByPosition(2));
        while (circleQueue.N != 1) {
            System.out.printf("kill the %s \n", circleQueue.deleteByPosition(2));
        }
        System.out.printf("the last is %s ", circleQueue.last.t);
    }
}
