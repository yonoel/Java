package com.miaoder.future;

import java.util.ArrayList;

public class TestAddAll {
    public static void main(String[] args) {
        ArrayList list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        ArrayList list2 = new ArrayList<>();
        list2.add(5);
        list2.add(6);
        list2.add(7);
        list.addAll(0,list2);
        list.forEach(System.out::println);
    }
}
