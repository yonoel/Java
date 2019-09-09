package com.study.book.Example.outOfMemory;

import java.util.ArrayList;
import java.util.List;

/**
 * VM ARGS:-Xms20m -Xmx20m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) list.add(new OOMObject());
    }
}
