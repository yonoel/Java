package com.study.chapter.Fir_Base.ThirdSection_Collection;

import java.util.Iterator;

/**
 * FIFO QUEUE
 *
 * @param <Item>
 */
public class Queue<Item> implements Iterable<Item>, Cloneable {


    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    private Node first;
    private Node last;
    private int N;

    private class Node {
        Item item;
        Node next;
    }

    public Queue() {
    }

    public Queue(Queue queue) {
        Queue retQueue = new Queue<>();
        // bianli -->tianjia
    }

    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    Item dequeue() {
        if (isEmpty()) return null;
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        N--;
        return item;
    }

    boolean isEmpty() {
        return first == null;
    }

    int size() {
        return N;
    }
}
