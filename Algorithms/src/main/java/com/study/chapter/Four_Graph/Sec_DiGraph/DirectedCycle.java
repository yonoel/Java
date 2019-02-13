package com.study.chapter.Four_Graph.Sec_DiGraph;

import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph g) {
        onStack = new boolean[g.getV()];
        marked = new boolean[g.getV()];
        edgeTo = new int[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
            if(!marked[i]) dfs(g,i);
        }
    }
    private void dfs(Digraph g,int v){
        onStack[v] = true;
        marked[v] = true;
        Iterable<Integer> adjs = g.adj(v);
        for (Integer w : adjs) {
            if(this.hasCycle()) return;
            else if(!marked[w]){
                edgeTo[w] = v;
                dfs(g,w);
            }else if (onStack[w]){
                cycle = new Stack<>();
                for (int i = v; i != w ; i = edgeTo[i]) {
                    cycle.push(i);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }
    public boolean hasCycle(){
        return cycle != null;
    }
    public Iterable<Integer> cycle(){
        return cycle;
    }

    public static void main(String[] args) {
        Digraph digraph = Digraph.getInstance();
        Digraph DAG = Digraph.getDAG();
        Digraph g = new Digraph(3);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(0,2);


//        g.addEdge(1,2);
//        DirectedCycle cycle = new DirectedCycle(digraph);
//        DirectedCycle cycle = new DirectedCycle(DAG);
        DirectedCycle cycle = new DirectedCycle(g);
        System.out.println(cycle.hasCycle());
    }
}
