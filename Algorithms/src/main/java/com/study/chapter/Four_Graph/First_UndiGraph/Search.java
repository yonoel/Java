package com.study.chapter.Four_Graph.First_UndiGraph;

public class Search {
    //  第一种实现就是第一章的UF实现（1.普通版的节点测试，2，用数表示结构）
    // 这种基于深度优先DFS
    private Graph g;
    private int s;
    public Search(Graph G ,int s) {
        //找到和s连通的所有顶点
        this.g = G;
        this.s = s;
    }
    // s-v是否连通
    public boolean marked(int v){
        return false;
    }
    // 与s连通的总数
    public int count(){
        return 0;
    }
}
