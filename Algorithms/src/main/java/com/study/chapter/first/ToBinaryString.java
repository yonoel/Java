package com.study.chapter.first;

public class ToBinaryString {
    public static void main(String[] args) {
        ToBinaryString.test();
    }
    public static void test(){
        String str = "";
        int a = 132546;
        for (int i = a ; i > 0; i /= 2) {
            str = (i%2)+str;
        }
        System.out.println(str);
        System.out.println("after"+Integer.toBinaryString(a));
    }
}
