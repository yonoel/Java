package com.study.chapter.Fir_Base;

public class ExceptionReturn {
    public static void main(String[] args) {
        System.out.println(test());
    }
    public static String test(){
        try {
            int i = 0;
            // int i = 1 / 0;
            return "after try";
        } catch (Exception e) {
            // return "after catch";
        } finally {
            // return "inner finally";
        }
        return "after block";
    }
}
