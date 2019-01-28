package com.study.chapter.Fir_Base.FivethSection_Tree;

import edu.princeton.cs.algs4.StdIn;

public class UF {
    private int n;
    private int[] id;

    public UF(int n) {
        this.n = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    void union(int p, int q) {
        //
    }

    int find(int p) {
        return 0;
    }

    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    int count() {
        return n;
    }

    public static void main(String[] args) {
        int n = 10;
        UF uf = new UF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + "components");
    }

}
