package com.study.chapter.first.two.collection;



/**
 * 双栈算术表达式求值
 */
public class Evaluate {
    /*public static void main(String[] args) {
        ResizingArrayStack<String> ops = new ResizingArrayStack<>();
        ResizingArrayStack<Double> vals = new ResizingArrayStack<>();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if ("(".equals(s));
                else if ("+".equals(s)) ops.push(s);
                else if ("-".equals(s)) ops.push(s);
                else if ("*".equals(s)) ops.push(s);
                else if ("/".equals(s)) ops.push(s);
                else if ("sqrt".equals(s)) ops.push(s);
                else if(")".equals(s)) {
                        String op = ops.pop();
                        double v = vals.pop();
                        if ("+".equals(s)) v = vals.pop() + v;
                        else if ("-".equals(s))  v = vals.pop() - v;
                        else if ("*".equals(s))  v = vals.pop() * v;
                        else if ("/".equals(s))  v = vals.pop() / v;
                        else if ("sqrt".equals(s))  v = Math.sqrt(v);
                        vals.push(v);
            }
                else vals.push(Double.parseDouble(s));
        }
        System.out.println(vals.pop());
    }*/
}
