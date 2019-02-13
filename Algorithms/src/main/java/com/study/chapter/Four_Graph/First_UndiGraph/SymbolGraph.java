package com.study.chapter.Four_Graph.First_UndiGraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class SymbolGraph {
    private ST<String, Integer> st;
    private String[] keys;
    private Graph graph;

    public SymbolGraph(String stream, String sp) {
        st = new ST<>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) st.put(a[i], st.size());
            }
        }
        keys = new String[st.size()];
        for (String key : st.keys()) {
            keys[st.get(key)] = key;
        }
        graph = new Graph(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                graph.addEdge(v, st.get(a[i]));
            }

        }
    }
    public boolean contains(String s){
        return st.contains(s);
    }
    public int index(String s){
        return st.get(s);
    }
    public String name(int i){
        return keys[i];
    }
    public Graph getGraph(){
        return graph;
    }
}
