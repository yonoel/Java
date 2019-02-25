package com.study.chapter.Fiv_String.Fir_Sort;

import java.util.Map;

public class IndexAccmulator {
    public static void main(String[] args) {
        new IndexAccmulator(IndexAccmulator.getArray(),5);
    }
    private int N;
    private int R;
    private Student[] arr;

    public IndexAccmulator(Student[] a, int r) {
        N = a.length;
        R = r;
        arr = a;
        sort();
    }

    private void sort() {
        Student[] aux = new Student[N];
        int[] count = new int[R + 1];
        // 计算频率
        for (int i = 0; i < N; i++) {
            count[arr[i].key() + 1]++;
        }
        // 转换频率为索引位置
        for (int i = 0; i < R; i++) {
            count[i + 1] += count[i];
        }
        // 分类所有元素
        for (int i = 0; i < N; i++) {
            aux[count[arr[i].key()]++] = arr[i];
        }
        // 回写？也可以直接塞啊
        for (int i = 0; i < N; i++) {
            arr[i] = aux[i];
        }
    }

    public static Student[] getArray() {
        return new Student[]{
                new Student(2, "Anderson")
                ,new Student(3, "Brown")
                ,new Student(3, "Davis")
                ,new Student(4, "Garcia")
                ,new Student(1, "Harris")
                ,new Student(3, "Jackson")
                ,new Student(4, "Johnson")
                ,new Student(3, "Jones")
                ,new Student(1, "Martin")
                ,new Student(2, "Martinez")
                ,new Student(2, "Miller")
                ,new Student(1, "Moore")
                ,new Student(2, "Robinson")
                ,new Student(4, "Smith")
                ,new Student(3, "Taylor")
                ,new Student(4, "Thomas")
                ,new Student(4, "Thompson")
                ,new Student(2, "White")
                ,new Student(3, "Williams")
                ,new Student(4, "Wilson")
        };
    }
}
