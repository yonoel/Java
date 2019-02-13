package com.study.chapter.Four_Graph.Sec_DiGraph;
// 很明显这个复杂度太大了，小型图可以
public class TransitiveClosure {
    private DirectedDFS[] all;

    public TransitiveClosure(Digraph digraph) {
        all = new DirectedDFS[digraph.getV()];
        for (int i = 0; i < digraph.getV(); i++) {
            all[i] = new DirectedDFS(digraph,i);
        }
    }
    public boolean reachable(int v, int w){
        return all[v].marked(w);
    }

    public static void main(String[] args) {
        Digraph DAG = Digraph.getDAG();
        Digraph g = Digraph.getInstance();
        TransitiveClosure t1 = new TransitiveClosure(DAG);
        System.out.println(t1.reachable(0,3));
    }
}
