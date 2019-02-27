package com.study.chapter.Four_Graph.Sec_DiGraph;

public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph g) {
        marked = new boolean[g.getV()];
        id = new int[g.getV()];
        // 反向图，进行搜索，然后排序 - ，- 巧妙地利用里有向图的有向性，然后逆序，来反证连通分量
        DepthFirstOrder order = new DepthFirstOrder(g.reverse());
        Iterable<Integer> orders = order.getReversePost();
        for (Integer integer : orders) {
            if(!marked[integer]){
                dfs(g,integer);
                count++;
            }
        }
    }
    private void dfs(Digraph g,int v){
        marked[v] = true;
        id[v] = count;
        Iterable<Integer> adjs = g.adj(v);
        for (Integer w : adjs) {
            if(!marked[w]) dfs(g,w);
        }
    }
    public boolean stronglyConnected(int v, int w){
        return id[v] == id[w];
    }
    public int id(int v){
        return id[v];
    }

    public int getCount() {
        return count;
    }
}
