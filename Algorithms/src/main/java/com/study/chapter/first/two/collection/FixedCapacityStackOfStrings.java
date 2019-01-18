package com.study.chapter.first.two.collection;

class FixedCapacityStack<Item> {
    protected Item[] items;
    protected int N;

    public FixedCapacityStack(
            int cap) {
        // 注意 new Item[cap] 目前不能这么写
        items = (Item[]) new Object[cap];
    }

    void push(Item item) {
        items[N++] = item;
    }

    Item pop() {
        return items[--N];
    }

    boolean isEmpty() {
        return N == 0;
    }

    int size() {
        return N;
    }
}
