package com.study.chapter.first.ThirdSection;

// 1.3.37
public class Josephus<T> {
    private Node head;
    private Node tail;
    private int N;

    private class Node {
        private T item;
        private Node pre;
        private Node next;

        public Node(T item) {
            this.item = item;
        }
    }



    void insert(T item) {
        Node insertedNode = new Node(item);
        if (N == 0) {
            head = insertedNode;
            tail = head;
            head.next = tail;
            head.pre = tail;
            tail.pre = head;
            tail.next = head;
        }
        else {

        }
        N++;
    }

    void delete(int k) {
        if (N == 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {

        }
    }

    public static void main(String[] args) {
        Josephus<Integer> list = new Josephus<Integer>();

    }
}
