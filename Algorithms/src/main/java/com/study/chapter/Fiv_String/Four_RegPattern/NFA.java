package com.study.chapter.Fiv_String.Four_RegPattern;

import com.study.chapter.Four_Graph.First_UndiGraph.Graph;
import com.study.chapter.Four_Graph.Sec_DiGraph.Digraph;
import edu.princeton.cs.algs4.Stack;

public class NFA {
    private char[] re;
    private Digraph digraph;
    private int M;

    public NFA(String reg) {
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

}
