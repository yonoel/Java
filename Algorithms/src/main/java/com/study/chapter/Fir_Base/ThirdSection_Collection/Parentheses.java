package com.study.chapter.Fir_Base.ThirdSection_Collection;


import java.util.StringJoiner;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']'
 * , determine if the input string is valid.
 */
public class Parentheses {
    static String no1 = "()";
    static String no2 = "{}";
    static String no3 = "[]";

    public static void main(String[] args) {
        String s = "([])";
        System.out.println(isValid(s));

    /*    while (search(s)) {
            s = split(s);
        }

        System.out.println(s.length() == 0);*/
       /* if (s.length() % 2 != 0 ) System.out.println(false);

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if(c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else {
            //  说明是反向的 // FILO
                char res  = stack.pop();
                if(res != c) {
                    System.out.println(false);
                    break;
                }
            }
        }
        System.out.println(stack.isEmpty());*/

    }
    /**
     * 最高效解法
     */
    public static boolean isValid(String s)
    {
        int sLength = s.length();

        if (sLength % 2 != 0)
        {
            return false;
        }

        int stackSize = sLength / 2 + 1;
        char[] stack = new char[stackSize];
        int top = 0;

        for (int i = 0; i < sLength; i++)
        {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[')
            {
                stack[top++] = c;
                if (top > sLength - i)
                {
                    return false;
                }
            }
            else
            {
                if (top == 0) return false;
                if (c == ')' && stack[--top] != '(') return false;
                if (c == '}' && stack[--top] != '{') return false;
                if (c == ']' && stack[--top] != '[') return false;
            }
        }
        return top == 0;
    }
    public static String split(String s) {
        String ret = "";
        StringJoiner sj = new StringJoiner("");
        return ret;

    }

    public static boolean search(String s) {
        boolean flag = true;
        if (!s.contains(no1) && !s.contains(no2) && !s.contains(no3)) {
            flag = false;
        }
        return flag;
    }

    public int getValue(char c) {
        int value = 0;
        switch (c) {
            case '(':
                value = 1;
                break;
            case ')':
                value = -1;
                break;
            case '[':
                value = 2;
                break;
            case ']':
                value = -2;
                break;
            case '{':
                value = 3;
                break;
            case '}':
                value = -3;
                break;
        }
        return value;
    }
}
