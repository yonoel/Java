package com.study.chapter.Thr_Select.Four_HashTable;

import com.study.chapter.Thr_Select.Fir_Base.SequentialSearchST;

public class SeqarateChainingHashST<Key, Value> {
    private int N; // 键值对数
    private int M; // 散列表大小
    private SequentialSearchST<Key, Value>[] sts;

    public SeqarateChainingHashST() {
        this(997);
    }

    public SeqarateChainingHashST(int m) {
        this.M = m;
        sts = (SequentialSearchST[]) new SequentialSearchST[M];
        for (int i = 0; i < m; i++) {
            sts[i] = new SequentialSearchST();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return sts[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        sts[hash(key)].put(key, value);
    }

    public static void main(String[] args) {
        SeqarateChainingHashST<String, Integer> st = new SeqarateChainingHashST<>(5);
        st.put("123",1);
        st.put("122",1);
        st.put("121",1);
        st.put("125",1);
    }
}
