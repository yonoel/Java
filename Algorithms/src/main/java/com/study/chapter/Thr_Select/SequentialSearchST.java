package com.study.chapter.Thr_Select;

public class SequentialSearchST<Key, Value> {
    private Node first;

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int n = 0;

    Value get(Key key) {
        for (Node i = first; i != null; i = i.next) {
            if (key.equals(i.key)) return i.value;
        }
        return null;
    }

    void put(Key key, Value value) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        first = new Node(key, value, first);
        n++;
    }

    void delete(Key key) {
        if (key.equals(first.key)) {
            first = null;
            n--;
            return;
        }
        for (Node pre = first, next = pre.next; next != null; pre = pre.next, next = pre.next)
            if (key.equals(next.key)) {
                pre.next = next.next;
                n--;
                return;
            }

    }

    Key[] keys() {
        Key[] keys = (Key[]) new Object[n];
        int size = n;
        Node x = first;
        while (size != 0) {
            keys[size] = x.key;
            x = x.next;
            size--;
        }
        return keys;
    }

    int size() {
        return n;
    }

}
