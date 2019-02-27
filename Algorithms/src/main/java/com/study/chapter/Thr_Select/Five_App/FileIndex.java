package com.study.chapter.Thr_Select.Five_App;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;

import java.io.File;

public class FileIndex {
    public static void main(String[] args) {
        ST<String, SET<File>> st = new ST<>();
        for (String fileName : args){
            File file = new File(fileName);
            In in = new In(file);
            while (!in.isEmpty()){
                String word = in.readString();
                if(!st.contains(word))st.put(word,new SET<File>());
                SET<File> set = st.get(word);
                set.add(file);
            }
        }
    }
}
