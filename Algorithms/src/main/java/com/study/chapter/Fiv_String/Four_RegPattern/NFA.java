package com.study.chapter.Fiv_String.Four_RegPattern;

import com.study.chapter.Four_Graph.First_UndiGraph.Graph;
import com.study.chapter.Four_Graph.Sec_DiGraph.Digraph;
import com.study.chapter.Four_Graph.Sec_DiGraph.DirectedDFS;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;

public class NFA {
    public static void main(String[] args) {
        new NFA("AB(CD)E");
    }
    private char[] re;
    private Digraph digraph;
    private int M;

    public NFA(String reg) {
        // 根据pat构造一张图
        Stack<Integer> ops = new Stack<>();
        re = reg.toCharArray();
        M = re.length;
        digraph = new Digraph(M + 1);
        for (int i = 0; i < M; i++) {
            int lp = i;
            if (re[i] == '(' || re[i] == '|')
                ops.push(i);
            else if (re[i] == ')') {
                int or = ops.pop();
                if (re[or] == '|') {
                    lp = ops.pop();
                    digraph.addEdge(lp, or + 1);
                    digraph.addEdge(or, i);
                } else lp = or;
            }
            if (i < M - 1 && re[i + 1] == '*') {
                digraph.addEdge(lp, i + 1);
                digraph.addEdge(i + 1, lp);
            }
            if (re[i] == '(' || re[i] == '*' || re[i] == ')')
                digraph.addEdge(i, i + 1);
        }
    }

    public boolean recongnizes(String txt) {
        // 识别的流程
        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(digraph, 0);
        for (int v = 0; v < digraph.getV(); v++) {
            if (dfs.marked(v)) pc.add(v);
        }
        for (int i = 0; i < txt.length(); i++) {
            // 计算txt[i+1]可能到达的所有NFA状态，这个很长啊。。。
            Bag<Integer> match = new Bag<>();
            for (Integer v : pc) {
                if (v < M)
                    if (re[v] == txt.charAt(i) || re[v] == '.')
                        match.add(v + 1);
            }
            pc = new Bag<>();
            dfs = new DirectedDFS(digraph, match);
            for (int v = 0; v < digraph.getV(); v++) {
                if (dfs.marked(v)) pc.add(v);
            }
        }
        for (Integer v : pc) {
            if (v == M) return true;
        }
        return false;
    }
}
