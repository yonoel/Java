package com.study.chapter.Thr_Select.Sec_BinaryTree;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BST<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N;

        public Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    private Node root;

    int size() {
        return size(root);
    }

    int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    Value get(Key key) {
        return get(root, key);
    }

    Value get(Node root, Key key) {
        if (root == null) return null;

        int cmp = key.compareTo(root.key);
        if (cmp < 0) return get(root.left, key);
        else if (cmp > 0) return get(root.right, key);
        else return root.value;
    }

    void put(Key key, Value value) {
        root = put(root, key, value);
    }

    Node put(Node root, Key key, Value value) {
        if (root == null) return new Node(key, value, 1);
        int cmp = key.compareTo(root.key);
        if (cmp < 0) root.left = put(root.left, key, value);
        else if (cmp > 0) root.right = put(root.right, key, value);
        else root.value = value;
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    Key min() {
        return min(root).key;
    }

    Key max() {
        return max(root).key;
    }

    Node max(Node root) {
        if (root == null) return null;
        return max(root.right);
    }

    int rank(Key key) {
        return rank(root, key);
    }

    int rank(Node root, Key key) {
        if (root == null) return 0;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) return rank(root.left, key);
        else if (cmp > 0) return 1 + size(root.left) + rank(root.right, key);
        else return size(root.left);
    }

    Key select(int key) {
        return select(root, key).key;
    }

    Node select(Node root, int k) {
        if (root == null) return null;
        int t = size(root.left);
        if (t > k) return select(root.left, k);
        else if (t < k) return select(root.right, k - t - 1);
        else return root;
    }

    Node min(Node root) {
        if (root.left == null) return root;
        return min(root.left);
    }

    Key floor(Key key) {
        Node temp = floor(root, key);
        if (temp == null) return null;
        return temp.key;
    }

    Node floor(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) return floor(root.left, key);
        else if (cmp == 0) return root;
        Node temp = floor(root.right, key);
        if (temp != null) return temp;
        else return root;
    }

    void deleteMin() {
        root = deleteMin(root);
    }

    Node deleteMin(Node root) {
        if (root.left == null) return root.right;
        root.left = deleteMin(root.left);
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    void delete(Key key) {
        root = delete(root, key);
    }

    Node delete(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp < 0) root.left = delete(root.left, key);
        else if (cmp > 0) root.right = delete(root.right, key);
        else {
            if (root.right == null) return root.left;
            if (root.left == null) return root.right;
            Node temp = root;
            // 见3.2.3.6
            root = min(root.right);
            root.right = deleteMin(temp.right);
            root.left = temp.left;
        }
        root.N = size(root.left) + size(root.right) - 1;
        return root;
    }

    void print(Node root) {
        if (root == null) return;
        print(root.left);
        StdOut.println(root.key);
        print(root.right);
    }

    Iterable<Key> keys() {
        return keys(min(), max());
    }

    Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> keyQueue = new Queue<>();
        keys(root, keyQueue, lo, hi);
        return keyQueue;
    }

    void keys(Node root, Queue<Key> queue, Key lo, Key hi) {
        if (root == null) return;
        int cmpLo = lo.compareTo(root.key), cmpHi = hi.compareTo(root.key);
        if (cmpLo < 0) keys(root.left, queue, lo, hi);
        if (cmpLo <= 0 && cmpHi >= 0) queue.enqueue(root.key);
        if (cmpHi > 0) keys(root.right, queue, lo, hi);
    }

    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        for (int i = 0; i < 10; i++) {
            bst.put(String.valueOf(i), i);
        }
        bst.print(bst.root);
    }
}
