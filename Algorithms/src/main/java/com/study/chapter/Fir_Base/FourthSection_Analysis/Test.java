package com.study.chapter.Fir_Base.FourthSection_Analysis;

import java.util.Arrays;

public class Test {
    // 1.4.8 计算输入文件中相等的整数队的数量
    public static int count(int[] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] == a[j]) count++;
            }
        }
        return count;
    }

    public static void count2(int[] a) {
        int count = 0;
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            a:
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] == a[j]) count++;
                if (a[j] > a[i]) break a;
            }
        }
    }

    // 1.4.12 两个有序数组N，打印公共元素，并有序，保证最坏情况与N成正比
    public static void soutArr() {
        int[] a = {1, 2, 3, 4, 5, 8};
        int[] b = {2, 3, 4, 6, 8, 9};
        int j = 0;
        int i = 0;
        for (; i < a.length; i++) {
            a:
            for (; j < b.length; j++) {
                if (a[i] < b[j]) {
                    System.out.println(a[i]);
                    break a;
                } else {
                    System.out.println(b[j]);
                }
            }
        }
        if (j == i) {
            System.out.println(a[--i]);
        } else {
            soutRest(j, b);
        }
    }

    public static void soutRest(int a, int[] ar) {
        if (a < ar.length) {
            while (a < ar.length) {
                System.out.println(ar[a]);
                a++;
            }
        }
    }

    public static void main(String[] args) {
        soutArr();
    }
}
