package com.study.chapter.Six_Context;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Arrays;
import java.util.Stack;

public class LCP {
    public static int lcp(String a, String b) {
        int M = Math.min(a.length(), b.length());
        for (int i = 0; i < M; i++) {
            if (a.charAt(i) != b.charAt(i)) return i;
        }
        return M;
    }

    public static void subStringTolcp(String s) {
        String[] arr = new String[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.substring(i);
        }
        Arrays.sort(arr);
        int n = 0;
        String longprefix = "";
        for (int i = 1; i < arr.length; i++) {
            String a = arr[i];
            String b = arr[i - 1];
            int k = 0;
            int M = Math.min(a.length(), b.length());
            for (int j = 0; j < M; j++) {
                if (a.charAt(j) == b.charAt(j)) {
                    k++;
                } else {
                    break;
                }
            }
            if (k > n) {
                n = k;
                longprefix = a.substring(0,n);
            }
        }
        System.out.println(longprefix);
    }

    public static void main(String[] args) {
        String a = "aacaagtttacaagc";
        subStringTolcp(a);
    }
}
