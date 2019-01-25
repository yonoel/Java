package com.study.chapter.first.ThirdSection;

// 1.3.32
public class Steque<T> {
    private Node first;
    private int N;
    private Node last;
    private class Node {
        T t;
        Node next;
    }

    boolean isEmpty() {
        return first == null;
    }

    int size() {
        return N;
    }
    void enqueue(T t){
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException();
        Node newlast = new Node();
        newlast.t = t;
        last.next = newlast;
        last = newlast;
        N++;
    }
    void push(T t) {
        Node oldFirst = first;
        first = new Node();
        first.t = t;
        first.next = oldFirst;
        if(N == 0) first = last;
        N++;
    }

    T pop() {
        if (isEmpty()) throw new NullPointerException();
        T t = first.t;
        first = first.next;
        N--;
        return t;
    }
}
