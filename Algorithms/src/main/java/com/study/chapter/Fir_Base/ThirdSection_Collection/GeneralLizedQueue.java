package com.study.chapter.Fir_Base.ThirdSection_Collection;

// 1.3.38 也可以用链表实现
public class GeneralLizedQueue<Item> {
    private Item[] items;
    private int N;

    public GeneralLizedQueue(int cap) {
        items = (Item[]) new Object[cap];
    }

    boolean isEmpty() {
        return N == 0;
    }

    void insert(Item item) {
        if (N == items.length) resize(N * 2);
        items[N++] = item;
    }

    Item delete(int k) {
        if (N == items.length / 4) resize(N / 2);
        return items[N--];
    }

    boolean isFull() {
        return items.length == N;
    }

    void resize(int cap) {
        Item[] temp = (Item[]) new Object[cap];
        for (int i = 0; i < N; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }
}
