package com.study.chapter.first.two;

public class FixedCapactiyStackOfStrings {
    private String[] a;
    private int n;

    public FixedCapactiyStackOfStrings(int n) {
        a = new String[n];
    }

    boolean isFull(){
        return n == a.length;
    }
    boolean isEmpty() {
        return n == 0;
    }

    int size() {
        return n;
    }

    void push(String s) {
        if(isFull()) throw new ArrayIndexOutOfBoundsException();
        a[n++] = s;
    }

    String pop() {

        return a[--n];
    }

}
