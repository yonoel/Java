package com.study.chapter.Fir_Base.ThirdSection_Collection;

public class DoubleLinkedList<T> {
    private DoubleNode first;
    private DoubleNode last;
    private int N;

    private class DoubleNode {
        private T t;
        private DoubleNode pre;
        private DoubleNode next;

        public DoubleNode(T t) {
            this.t = t;
        }
    }

    void insertHead(T t) {
        DoubleNode newFirst = new DoubleNode(t);
        newFirst.next = first;
        first = newFirst;
        if(N == 0 ){
            this.last = this.first;
        }else{
            first.next.pre = first;
        }
        N++;
    }

    void insertTail(T t) {
        DoubleNode newLast = new DoubleNode(t);
        if (N == 0) {
            last = newLast;
            this.first = this.last;
        }else{
            last.next = newLast;
            newLast.pre = last;
            last = newLast;
        }
        N++;
    }

    void deleteHead() {
        if (N == 0) throw new ArrayIndexOutOfBoundsException();
        if (N == 1) {
            // one node
            first = null;
            last = null;
        } else {
            DoubleNode sec = first.next;
            sec.pre = null;
            first = sec;
        }
        N--;
    }
    void deleteTail(){
        if (N == 0) throw new ArrayIndexOutOfBoundsException();
        if (N == 1) {
            // one node
            first = null;
            last = null;
        } else {
           DoubleNode preTail = last.pre;
           preTail.next = null;
           last = preTail;
        }
        N--;
    }
    void insertBerforeNode(DoubleNode insertedNode,DoubleNode node){
        // 1.这个节点存在？
        // 2.插入
        if(N == 0) throw new ArrayIndexOutOfBoundsException();
        for (DoubleNode temp = first;temp!=null; temp= temp.next) {
            if(temp == node) {
                DoubleNode oldPre = temp.pre;
                oldPre.next = insertedNode;
                insertedNode.next = temp;
                break;
            }
        }
    }
    void insertAfterNode(DoubleNode insertedNode,DoubleNode node){
        if(N == 0) throw new ArrayIndexOutOfBoundsException();
        for (DoubleNode temp = last;temp!=null; temp= temp.pre) {
            if(temp == node) {
                DoubleNode oldNext = temp.next;
                temp.next = insertedNode;
                insertedNode.next = oldNext;
                break;
            }
        }
    }
    void deleteAfterNode(DoubleNode node){
        if(node == last || N == 0) throw new ArrayIndexOutOfBoundsException();
        for (DoubleNode temp = last;temp!=null; temp= temp.pre) {
            if(temp == node){
                temp.next = temp.next.next;
            }
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList<String> linkedList = new DoubleLinkedList<>();
        linkedList.insertTail("5");
//        linkedList.deleteTail();
       linkedList.insertHead("1");
       linkedList.deleteHead();
       linkedList.insertHead("2");
       linkedList.insertTail("6");
       linkedList.insertHead("3");
       linkedList.insertHead("4");
    }
}
