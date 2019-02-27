package com.study.chapter.Fiv_String.Sec_SearchTree;

import com.study.chapter.Fir_Base.ThirdSection_Collection.Queue;

public class TrieST<Value> {
    private static int R = 256;
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    private Node get(Node node, String key, int d) {
        if (node == null) return null;
        if (d == key.length()) return node;
        char c = key.charAt(d);
        return get(node.next[c], key, d + 1);
    }

    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node node, String key, Value value, int d) {
        if (node == null) node = new Node();
        if (d == key.length()) {
            node.val = value;
            return node;
        }
        char c = key.charAt(d);
        node.next[c] = put(node.next[c], key, value, d + 1);
        return node;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String s) {
        Queue<String> queue = new Queue<>();
        collect(get(root, s, 0), s, queue);
        return queue;
    }

    private void collect(Node node, String s, Queue<String> queue) {
        if (node == null) return;
        if (node.val != null) queue.enqueue(s);
        for (char c = 0; c < R; c++) {
            collect(node.next[c], s + c, queue);
        }
    }

    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new Queue<>();
        collect(root, "", pat, q);
        return q;
    }

    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node root, String s, int d, int length) {
        if (root == null) return length;
        if (root.val != null) length = d;
        if (d == s.length()) return length;
        char c = s.charAt(d);
        return search(root.next[c], s, d + 1, length);
    }

    private void collect(Node root, String s, String pat, Queue<String> q) {
        int d = s.length();
        if (root == null) return;
        if (d == pat.length() && root.val != null) q.enqueue(s);
        if (d == pat.length()) return;
        char next = pat.charAt(d);
        for (char c = 0; c < R; c++) {
            if (next == '.' || next == c)
                collect(root.next[c], s + c, pat, q);
        }
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.val != null) {
            return x;
        }
        for (char c = 0; c < R; c++)
            if (x.next[c] != null) return x;
        return null;
    }

}
