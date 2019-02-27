package com.study.chapter.Thr_Select.Four_HashTable;

public class LinearProbingHashST<Key, Value> {
    private int N;
    private int M = 16;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public LinearProbingHashST(int m) {
        M = m;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    private void resize(int size) {
      /*  Key[] keys1 = (Key[]) new Object[size];
        Value[] values1 = (Value[]) new Object[size];
        for (int i = 0; i < M; i++) {
            keys1[i] = keys[i];
            values1[i] = values[i];
        }
        keys = keys1;
        values = values1;*/
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(size);
        for (int i = 0; i < M; i++) {
            if(keys[i] != null)temp.put(keys[i],values[i]);
        }
        keys = temp.keys;
        values = temp.values;
        M = temp.M;
    }

    public void put(Key key, Value value) {
        if (N >= M / 2) resize(2 * M);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }

        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) return values[i];
        }
        return null;
    }

    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valueToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            put(keyToRedo, valueToRedo);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M / 8) resize(M / 2);
    }

    private boolean contains(Key key) {
        return keys[hash(key)] != null;
    }

    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>(30);
        st.put("S", 1);
//        st.put("E", 1);
//        st.put("A", 1);
//        st.put("R", 1);
//        st.put("C", 1);
//        st.put("H", 1);
//        st.put("E", 1);
//        st.put("X", 1);
//        st.put("A", 1);
//        st.put("M", 1);
//        st.put("P", 1);
//        st.put("L", 1);
//        st.put("E", 1);
//        st.delete("C");
    }
}
