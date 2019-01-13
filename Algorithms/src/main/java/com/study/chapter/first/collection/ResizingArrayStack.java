package com.study.chapter.first.collection;

import java.util.Iterator;

/**
 * 下压栈 LIFO
 *
 * @param <Item>
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
    @Override
    public Iterator<Item> iterator() {
        return new ReverArrayIterator() ;
    }
    // 默认1
    private Item[] items = (Item[]) new Object[1];
    // 这个N并不指向数组长度（数组长度是真实长度的2倍），而指向了数组的真实长度
    private Integer N = 0;

    void push(Item item) {
        if(N == items.length) resize(2*items.length);
        items[N++] = item;
    }

    Item pop() {
        Item item = items[--N];
        items[N] = null;
        if( N > 0 && N == items.length / 4) resize( items.length / 2);
        return item;
    }

    boolean isEmpty() {
        return N == 0;
    }

    int size() {
        return N;
    }

    public ResizingArrayStack(Integer n) {
        items = (Item[]) new Object[n];
    }

    public void resize(int max){
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = items[i];
        items = temp;
    }
    private class  ReverArrayIterator implements Iterator<Item>{
        int i = N;
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return items[--i];
        }
    }
}
