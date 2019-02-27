package com.study.chapter.Fir_Base.FivethSection_Tree;


public class WeightQuickUnionUF {
    private int[] id;
    private int[] sz;
    private int count = 0;

    public WeightQuickUnionUF(int count) {
        this.count = count;
        id = new int[count];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
        sz = new int[count];
        for (int i = 0; i < count; i++) {
            sz[i] = 1;
        }
    }

    int count() {
        return count;
    }

    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        // 将小树的根节点和大树链接
        if (sz[i] < sz[j]) {
            id[i] = id[j];
            sz[j] += sz[i];
        } else {
            id[j] = id[i];
            sz[i] += sz[j];
        }
        count--;
    }

    public static void main(String[] args) {
        WeightQuickUnionUF weightQuickUnionUF = new WeightQuickUnionUF(10);
        weightQuickUnionUF.union(1,2);
        weightQuickUnionUF.union(3,4);
        weightQuickUnionUF.union(1,3);
    }
}


