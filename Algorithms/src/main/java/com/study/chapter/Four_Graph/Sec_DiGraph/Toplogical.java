package com.study.chapter.Four_Graph.Sec_DiGraph;

public class Toplogical {
    private Iterable<Integer> order;

    public Toplogical(Digraph g) {
        DirectedCycle cycle = new DirectedCycle(g);
        if(!cycle.hasCycle()){
            DepthFirstOrder dfs = new DepthFirstOrder(g);
            order = dfs.getReversePost();
        }
    }

    public Iterable<Integer> getOrder() {
        return order;
    }

    public static void main(String[] args) {
        Digraph DAG = Digraph.getDAG();
        Toplogical top = new Toplogical(DAG);
        for (Integer integer : top.getOrder()) {
            System.out.println(integer);
        }
    }
}
