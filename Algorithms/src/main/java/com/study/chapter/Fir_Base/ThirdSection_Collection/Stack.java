package com.study.chapter.Fir_Base.ThirdSection_Collection;

// FIFO
public class Stack<T> {
    private Node first;
    private int N;

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

    void push(T t) {
        Node oldFirst = first;
        first = new Node();
        first.t = t;
        first.next = oldFirst;
        N++;
    }

    T peek() {
        if (isEmpty()) throw new NullPointerException();
        return first.t;
    }

    T pop() {
        if (isEmpty()) throw new NullPointerException();
        T t = first.t;
        first = first.next;
        N--;
        return t;
    }
}
