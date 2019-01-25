package com.study.chapter.three;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ST <Key extends Comparable<Key>,Value> implements Iterable<Key>{
    public ST() {
        st = new TreeMap<Key, Value>();
    }
    private TreeMap<Key, Value> st;

    void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("calls put() with null key");
        if (val == null) st.remove(key);
        else             st.put(key, val);

    }

    Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("calls get() with null key");
        return st.get(key);
    }

    void delete(Key key) {
        put(key,null);
    }

    boolean contains(Key key) {
        return get(key) != null;
    }

    boolean isEmpty() {
        return size() == 0;
    }

    int size() {
        return st.size();
    }
    Key min(){
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return st.firstKey();
    }
    Key max(){
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return st.lastKey();
    }
    Key floor(Key key){
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        Key k = st.floorKey(key);
        if (k == null) throw new NoSuchElementException("all keys are greater than " + key);
        return k;
    }
    Key ceiling(Key key){
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        Key k = st.ceilingKey(key);
        if (k == null) throw new NoSuchElementException("all keys are less than " + key);
        return k;
    }
    int rank(Key key){
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        // todo
        return 0;
    }
    Key select(int k){
        // todo
      return st.firstKey();
    }
    void deleteMin(){
        delete(min());
    }
    void deleteMax(){
        delete(max());
    }
    int size(Key lo,Key hi){
        if(hi.compareTo(lo) < 0 ){
            return 0;
        }
        if(contains(hi)) return rank(hi)-rank(lo)+1;
        else return rank(hi)-rank(lo);
    }
    Iterable<Key> Keys(Key lo,Key hi){ return null;}
    Iterable<Key> keys() {
        return st.keySet();
    }

    @Override
    public Iterator<Key> iterator() {
            return st.keySet().iterator();
    }
}
