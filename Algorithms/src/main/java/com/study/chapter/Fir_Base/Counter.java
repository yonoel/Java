package com.study.chapter.Fir_Base;

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
        return "com.chapter.Fir_Base.Counter{" +
                "name='" + name + '\'' +
                '}';
    }
}
