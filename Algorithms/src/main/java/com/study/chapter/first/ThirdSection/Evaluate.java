package com.study.chapter.first.ThirdSection;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;
import java.util.stream.Stream;

public class Evaluate {

    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("("));
            else if(Stream.of("+","-","*","/","sqrt").anyMatch(a->s.equals(a))) ops.push(s);
            else if(s.equals(")")){
                String op = ops.pop();
                double v = vals.pop();
                if(op.equals("+")) v = vals.pop()+v;
                else if(op.equals("-")) v = vals.pop()-v;
                else if(op.equals("*")) v = vals.pop()*v;
                else if(op.equals("/")) v = vals.pop()/v;
                else if(op.equals("sqrt")) v = Math.sqrt(v);
                vals.push(v);
            }
            else vals.push(Double.parseDouble(s));
        }
        StdOut.print(vals.pop());
    }
}
