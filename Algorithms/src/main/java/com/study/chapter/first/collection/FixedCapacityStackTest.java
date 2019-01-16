package com.study.chapter.first.collection;




public class FixedCapacityStackTest {

    public static void main(String[] args) {
        FixedCapacityStack<String> s = new FixedCapacityStack<String>(100);
        s.push("hehe");
        System.out.println(s.pop());
    }
}