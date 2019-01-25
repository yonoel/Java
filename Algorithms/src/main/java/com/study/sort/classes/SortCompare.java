package com.study.sort.classes;


import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare {
    public static double time(String alg,Double[] a){
        Stopwatch timer = new Stopwatch();
        if("Insertion".equals(alg)) Insertion.sort(a);
        if("Selection".equals(alg)) Selection.sort(a);
        if("Shell".equals(alg)) Shell.sort(a);
        return timer.elapsedTime();
    }
    public static double timeRandomInput(String alg,int N,int T){
            // 使用算法1将T个长度为N的数组排序
        double total = 0.0;
        Double[] a = new Double[N];
        for (int t = 0; t < T; t++) {
            // 进行一次测试（生成一个数组，并排序）
            for (int i = 0; i < N; i++) {
                a[i] = StdRandom.uniform();
            }
            total += time(alg,a);
        }
        return total;
    }

    public static void main(String[] args) {

        // double t1 = timeRandomInput("Insertion",1000,100);
        // double t2 = timeRandomInput("Selection",1000,100);
        double t3 = timeRandomInput("Shell",100,100);
        // System.out.printf("insertion is cast %.5f\n",t1);
        // System.out.printf("Selection is cast %.5f\n",t2);
        System.out.printf("Shell is cast %.5f\n",t3);
    }
}
