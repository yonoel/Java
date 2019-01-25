package com.study.chapter.first.ThirdSection;

public class LinkedList<Item> {

    Node first;
    Node last;
    private int N = 0;

    // 私有类，一个节点
    protected class Node {
        Item item;
        Node next;//next 指向下一个node

        public Node(Item item) {
            this.item = item;
        }

        public Node() {
        }
    }

    //  删除一个节点只需要 x.next = x.next.next 插入反之亦然


   /* public LinkedList(int n) {
        N = n;
        first = new Node();
    }*/

    int size() {
        return N;
    }

    boolean isEmpty() {
        return N == 0;
    }

    void insertHead(Item item) {
        Node newHead = new Node(item);
        newHead.next = first;
        if (isEmpty()) {
            last = newHead;
            newHead.next = last;
        }
        first = newHead;

        N++;
    }

    void insertTail(Item item) {
        Node newTail = new Node(item);
        if (isEmpty()) insertHead(item);
        last.next = newTail;
        last = newTail;
        N++;
    }

    // 1.3.19 删除尾节点
    void deleteTail() {
        // 1.不知道尾节点之前的节点，只能一个个找下去，
        if (isEmpty()) return;
        if (N == 1) {
            first = null;
            last = null;
            N--;
            return;
        }
        Node pre = first;
        Node temp = pre.next;
        while (temp != last) {
            pre = temp.next;
            temp = pre.next;
        }
        // temp = last;pre
        temp = null;
        last = pre;
        pre.next = null;
        N--;
    }

    void deleteHead() {
        if (isEmpty()) return;
        if (N == 1) {
            first = null;
            last = null;
        } else {
            Node newHead = first.next;
            first = newHead;
        }
        N--;
    }

    // 1.3.20 给定参数k，删除第k个节点
    void deleteK(int k) {
        if (isEmpty() || size() < k) return;
        if (k == 1) deleteHead();
        else if (k == N) deleteTail();
        else {
            Node pre = first;
            Node temp = pre.next;
            int n = 2;
            while (n != k) {
                pre = pre.next;// temp
                temp = pre.next;
                n++;
            }
            //del temp
            pre.next = temp.next;
        }
        N--;
    }

    // 1.3.21 find
    boolean find(Item item) {
        boolean flag = false;
        if (isEmpty()) return flag;
        for (Node temp = first; temp.next != null; temp = temp.next) {
            if (temp.item == item) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    // 1.3.24
    void removeAfter(Node node) {
        if (node == first) deleteHead();
        if (node == last) deleteTail();
        Node temp = first.next;
        int n = 2;
        while (temp != node) {
            temp = temp.next;
            n++;
        }
        last = temp;
        temp.next = null;
        N = n;
    }

    // 1.3.25
    void insertAfter(Node pre, Node newNode) {
        Node temp = pre.next;
        pre.next = newNode;
        newNode.next = temp;

    }

    // 1.3.26
    void remove(LinkedList linkedList, Item key) {
        if (linkedList.size() == 0) return;
        // 需要处理只有1个节点的情况
        int n = linkedList.N;
        Node pre = linkedList.first;
        while (pre.item == key){
            linkedList.deleteHead();
            n--;
        }
        for(Node temp = pre.next;temp.next != null;temp=temp.next){
            if(temp.item == key){
                pre.next = temp.next;
                n -- ;
            }else{
                pre = pre.next;
            }
        }
        linkedList.N = n;
    }
    // 1.3.26
    void remove2(LinkedList linkedList,Item key){
        if (linkedList.size() == 0) return;
        // 需要处理只有1个节点的情况
        int n = linkedList.N;
        Node pre = linkedList.first;
//        for(Node temp = pre.next;temp.next != null;temp=temp.next){
//            if(temp.item == key){
//                pre.next = temp.next;
//                n -- ;
//            }else{
//                pre = pre.next;
//            }
//        }
        if(linkedList.first.item == key) linkedList.deleteHead();
    }
    // 1.3.27
    Item max(){
       if(isEmpty()) throw new ArrayIndexOutOfBoundsException();
       Item max = (Item) new Object();
        for (Node temp = first; temp.next != null; temp=temp.next) {
            if(String.valueOf(temp.item).compareTo(String.valueOf(max)) > 0){
                max = temp.item;
            }
        }
        return max;
    }
    Integer max(LinkedList<Integer> list){
        if(list.first == null)
            return 0;

        int first = list.first.item;//first (save every item as first)
        list.first = list.first.next;//remove first item in the list
        int max = max(list);//calculate the maximum value of the new list

        if(first > max) // when return 0 as last....last as sec last
            return first;
        else
            return max;
    }
    // 1.3.30 接收一个链表的首节点，反转链表，并返回结果链表的首节点
    public Node reverse(Node x){
        Node first = x;
        Node reverse = null;
        while (first != null){
            Node second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }
        return  reverse;
    }
    public Node reverseByrecursion(Node first){
        if(first == null) return null;
        if(first.next == null) return this.first;
        Node sec = first.next;
        Node rest = reverseByrecursion(first);
        sec.next = first;
        first.next = null;
        return  rest;
    }
    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.insertHead(1);
        linkedList.insertHead(1);
        linkedList.insertHead(1);
        linkedList.insertHead(1);
        linkedList.insertHead(2);
//        linkedList.deleteHead();
        linkedList.insertTail(3);
        linkedList.insertTail(4);
        System.out.println(linkedList.max(linkedList));
//        linkedList.remove(linkedList,1);
//        linkedList.removeAfter();
//        linkedList.find(3);
//        linkedList.deleteK(3);
//        linkedList.deleteTail();
//        linkedList.deleteTail();
    }
}

