package com.study.chapter.Fir_Base.ThirdSection_Collection;

import com.study.chapter.Fir_Base.FourthSection_Analysis.StopWatch;

// 1.3.39 环形缓冲区，环形队列，定长为N的先进先出，适合进程间的异步数据传输和记录日志
// 1.0 数组，缓冲流数组版
// 2.0 链表
public class RingBuffer<Item> {
    private Item[] items;
    private int inPoint;
    private int outPoint;

    public RingBuffer(int cap) {
        items = (Item[]) new Object[cap];
    }
    boolean isEmpty(){
        return  inPoint == outPoint;
    }
    boolean isFull(){
        return inPoint >= items.length;
    }
    void enqueue(Item item){
        if(!isFull()) items[++inPoint] = item;
    }
    boolean canOut(){
        return inPoint > outPoint;
    }
    Item item(){
        if(canOut())return items[++outPoint];
        else throw new ArrayIndexOutOfBoundsException();
    }
   /* private Node tail;
    private Node head;

    private class Node {
        private Node next;
        private Item item;
    }

    public RingBuffer() {
        Node node = new Node();
        node.item = null;
        this.tail = this.head = node;
    }

    boolean isEmpty() {
        return head == tail;
    }*/


   /* void enqueue(Item item) {
        Node node = new Node();
        node.item = item;
        node.next = tail;
        tail = node;
        if(head.next == null) head = node;
    }
*/
/*
    Item dequeue() {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException();
        Item out = head.item;
        head = head.next;
        return out;
    }

*/
    public static void main(String[] args) {
        // linked 6.634s //array1.94s...数组快
        StopWatch watch = new StopWatch();
        RingBuffer<Integer> ringBuffer = new RingBuffer<>(10000001);
        int n = 0;
        while (n< 10000000){
            ringBuffer.enqueue(n);
            n++;
        }
        System.out.println(watch.elapsedTime());
    }
}
