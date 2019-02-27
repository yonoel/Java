package com.study.chapter.Thr_Select.Five_App;


import edu.princeton.cs.algs4.LinearProbingHashST;

public class SparseVector {
    public static void standard(int N){
        double[][] a = new double[N][N];
        double[] x = new double[N];
        double[] b = new double[N];
        // ... init a[][] and x[]
        for (int i = 0; i < N; i++) {
            double sum = 0.0;
            for (int j = 0; j < N; j++) {
                sum += a[i][j] * x[j];
            }
            b[i] = sum;
        }
    }
    private LinearProbingHashST<Integer,Double> st;

    public SparseVector() {
        st = new LinearProbingHashST<>();
    }
    public int size(){
        return st.size();
    }
    public void put(int i, double x){
        st.put(i,x);
    }
    public double get(int i){
        if(!st.contains(i)) return 0.0;
        return st.get(i);
    }
    public double dot(double[] that){
        double sum = 0.0;
        for (int i : st.keys()){
            sum += that[i] * this.get(i);
        }
        return sum;
    }
}
