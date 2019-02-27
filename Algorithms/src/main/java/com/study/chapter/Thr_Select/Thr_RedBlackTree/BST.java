package com.study.chapter.Thr_Select.Thr_RedBlackTree;

public class BST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int N;
        boolean color;

        public Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            N = n;
            this.color = color;
        }
    }

    void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    Node put(Node root, Key key, Value value) {
        if (root == null) {
            return new Node(key, value, 1, RED);
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) root.left = put(root.left, key, value);
        else if (cmp > 0) root.right = put(root.right, key, value);
        else root.value = value;
        // 处理旋转
        if (isRed(root.right) && !isRed(root.left)) root = rotateLeft(root);
        if (isRed(root.left) && isRed(root.left.left)) root = rotateRight(root);
        if (isRed(root.left) && isRed(root.right)) filpColors(root);
        root.N = size(root.left) + size(root.right) + 1;
        return root;
    }

    boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    void filpColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    // 右侧有红链接，右侧这个键应当充当根节点的位置，所以这个红链接左旋
    Node rotateLeft(Node h) {
        if (h == null) return null;
        Node root = h.right;
        // root.left可能为空啊。。。
        h.right = root.left;
        root.left = h;
        root.color = h.color;
        h.color = RED;
        root.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return root;
    }

    // 左侧连续两个红链接，中间的键应作为根节点
    Node rotateRight(Node h) {
        if (h == null) return null;
        Node root = h.left;
        h.left = root.right;
        root.right = h;
        root.color = h.color;
        h.color = RED;
        root.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return root;
    }

    int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.N;
    }

    public static void main(String[] args) {
        BST<String,Integer> bst = new BST();
        bst.put("A",1);
        bst.put("C",2);
        bst.put("E",3);
        bst.put("H",3);
        bst.put("L",3);
        bst.put("M",3);
        bst.put("P",3);
        bst.put("R",3);
        bst.put("S",3);
        bst.put("X",3);
    }
}
