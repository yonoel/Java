package com.study.chapter.Fir_Base.FivethSection_Tree;

public class tinyUF {
    private int[] id;
    private int count;
    int find(int p) {
        return id[p];
    }

    void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) return;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) id[i] = qID;
        }
        count -- ;
    }
}
