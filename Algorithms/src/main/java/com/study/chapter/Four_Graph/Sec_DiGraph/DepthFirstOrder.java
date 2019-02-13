package com.study.chapter.Four_Graph.Sec_DiGraph;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph g) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[g.getV()];
        for (int i = 0; i < g.getV(); i++) {
            if (!marked[i]) dfs(g, i);
        }
    }
    private void dfs(Digraph g,int v){
        pre.enqueue(v);
        marked[v] = true;
        for (int w: g.adj(v)) {
            if(!marked[w]) dfs(g,w);
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> getPre() {
        return pre;
    }

    public Iterable<Integer> getPost() {
        return post;
    }

    public Iterable<Integer> getReversePost() {
        return reversePost;
    }
}
