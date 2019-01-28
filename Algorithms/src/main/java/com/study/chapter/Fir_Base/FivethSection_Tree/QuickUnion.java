package com.study.chapter.Fir_Base.FivethSection_Tree;
// 树结构
public class QuickUnion {
    private int[] id;
    private int count;

    int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args) {
        int[] a = {1,1,1,8,3,0,5,1,8,8};
        QuickUnion quickUnion = new QuickUnion();
        quickUnion.id = a;
//        quickUnion.find(5);
//        quickUnion.find(9);
        quickUnion.union(5,9);
        quickUnion.union(3,8);
    }
}
