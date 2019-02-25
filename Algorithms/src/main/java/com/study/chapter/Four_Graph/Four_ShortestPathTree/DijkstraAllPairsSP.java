package com.study.chapter.Four_Graph.Four_ShortestPathTree;

public class DijkstraAllPairsSP {
    private DijkstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDigraph g) {
        this.all = new DijkstraSP[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
            all[i] = new DijkstraSP(g,i);
        }
    }
}
