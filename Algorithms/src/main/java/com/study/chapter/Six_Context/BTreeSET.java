package com.study.chapter.Six_Context;

public class BTreeSET<Key extends Comparable<Key>> {
    private Page root = new Page(true);

    public BTreeSET(Key sentinel) {
        put(sentinel);
    }

    public boolean contains(Key key) {
        return contains(root, key);
    }

    private boolean contains(Page root, Key key) {
        if (root.isExternal()) return root.contains(key);
        return contains(root.next(key), key);
    }

    public void add(Key key) {
        put(root, key);
        if (root.isFull()) {
            Page leftHalf = root;
            Page rightHalf = root.split();
            root = new Page(false);
            root.put(leftHalf);
            root.put(rightHalf);
        }
    }

    public void add(Page page, Key key) {
        if (page.isExternal()) {
            page.put(key);
            return;
        }
        Page next = page.next(key);
        put(next, key);
        if (next.isFull()) page.put(next.split());
        next.close();
    }

    private void put(Page root, Key key) {
    }

    private void put(Key sentinel) {
    }
}
