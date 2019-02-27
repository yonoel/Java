package com.study.chapter.Six_Context;

public class Page<Key> {
    public Page(boolean bottom) {
    }

    public void close() {
    }

    public void add(Key key) {
    }

    public void add(Page page) {
    }
    public boolean isExternal(){
        return false;
    }
    public boolean contains(Key key){return false;}
    public Page next(Key key){return new Page(false);}
    public boolean isFull(){return true;}
    public  Page split(){return new Page(false);}

    public void put(Page rightHalf) {
    }

    public <Key extends Comparable<Key>> void put(Key key) {
    }
//    public Iterable<Key> keys(){}
}

