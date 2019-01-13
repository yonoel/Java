package com.study.chapter.first;

public class Counter {
    private String name;
    public Counter(String name) {
        this.name = name;
    }
    public void increment(){

    }
    public int tally() {
        return 1;
    }

    @Override
    public String toString() {
        return "com.chapter.first.Counter{" +
                "name='" + name + '\'' +
                '}';
    }
}
