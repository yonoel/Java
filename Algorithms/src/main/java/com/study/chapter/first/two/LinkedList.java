package com.study.chapter.first.two;

public class LinkedList <Item> {
    Node first;
    Node last;
    private int N = 0;
    // 私有类，一个节点
    private class Node {
        Item item;
        Node next;//next 指向下一个node
    }
//  删除一个节点只需要 x.next = x.next.next 插入反之亦然

    public LinkedList() {

    }
    void add(Item item){
        Node oldLast = last;
        Node last = new Node();
        oldLast.next = last;
        last.item = item;
        N++;
    }
}

