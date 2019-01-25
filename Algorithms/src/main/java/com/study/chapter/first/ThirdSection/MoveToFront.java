package com.study.chapter.first.ThirdSection;

// 1.3.40 前移编码，从标准输入读取字符串，使用链表保存并删除重复字符，
// 1.读取未见过的字符时，插入表头
// 2.读取重复的字符时，删除之前的，插表头
// 假设最近访问过的元素会再次访问，适用于缓存和数据压缩
public class MoveToFront<Item> {
    private Node head;
    private int n;

    private class Node {
        private Node next;
        private Item item;
    }

    boolean isEmpty() {
        return n == 0;
    }

    boolean isExsit(Item item) {
        if (isEmpty()) {
            return true;
        }
        for (Node i = head; i.next != null; i = i.next) {
            if (i.item == item) return false;
        }
        return true;
    }

    void enqueue(Item item) {
        if (!isEmpty()) {
            dequeue(item);
        }
        Node newHead = new Node();
        newHead.item = item;
        newHead.next = head;
        head = newHead;
        n++;
    }

    void dequeue(Item item) {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (String.valueOf(head.item).equals(String.valueOf(item))) {
            if (head.next != null) {
                Node newhead = head.next;
                head = null;
                head = newhead;
            } else {
                head = null;
            }
        } else {
            for (Node i = head, j = i.next; i.next != null; i = i.next, j = j.next) {
                if (String.valueOf(j.item).equals(String.valueOf(item))) {
                    i.next = j.next;
                    j = null;
                    return;
                }
            }
        }
        n--;
    }

    public static void main(String[] args) {
        MoveToFront<Integer> moveToFront = new MoveToFront();
        for (int i = 0; i < 1000; i++) {
            moveToFront.enqueue(i);
        }
        moveToFront.enqueue(997);
    }
}
