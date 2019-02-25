package com.study.chapter.Fiv_String;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Alphabet {
    private Character[] chars;

    public Alphabet(String s) {
        Set<Character> set = new HashSet<>(s.length());
        for (int i = 0; i < s.toCharArray().length; i++) {
            set.add(s.charAt(i));
        }
        chars = set.toArray(new Character[0]);
    }

    public char toChar(int index) {
        return chars[index];
    }

    // 返回索引
    public int toIndex(char c) {
        if (!contains(c)) return -1;
        return 0;
    }

    public boolean contains(char c) {
        if (toIndex(c) == -1) return false;
        return true;
    }

    private int search(char c) {
        int n = Arrays.binarySearch(chars, c);
        return n;
    }

    // 基数
    public int R() {
        return chars.length;

    }

    public int lgR() {
        //
        return 0;
    }

    public int[] toIndices(String s) {
//        return  Integer.
        return new int[1];
    }

    public String toChars(int[] indices) {
        return "";
    }
}
