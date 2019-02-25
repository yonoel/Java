package com.study.chapter.Fiv_String.Fir_Sort;

import java.util.Arrays;

public class LSD {
    public static void sort(String[] a, int w) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        for (int d = w - 1; d >= 0; d--) {
            // 根据第i个字符用键索引计数法排序
            int[] count = new int[R + 1];
            for (int j = 0; j < N; j++) {
                int c = a[j].charAt(d);
                count[c + 1]++;
            }
            for (int j = 0; j < R; j++) {
                count[j + 1] += count[j];
            }
            for (int j = 0; j < N; j++) {
                int c = a[j].charAt(d);
                aux[count[c]++] = a[j];
            }

            for (int j = 0; j < N; j++) {
                a[j] = aux[j];
            }
        }
    }

    public static void main(String[] args) {
//        for (String veh : getVehs()) {
//            System.out.println(veh);
//        }
        String[] a = getVehs();
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        sort(a, 6);
        System.out.println("----------after sort------");
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }

    public static String[] getVehs() {
        return new String[]{
                "4PGC938"
                , "2IYE230"
                , "3CI0720"
                , "1ICK750"
                , "10HV845"
                , "4JZY524"
                , "1ICK750"
                , "3CI0720"
                , "10HV845"
                , "10HV845"
                , "2RLA629"
                , "2RLA629"
                , "3ATW723"
        };
    }
}
