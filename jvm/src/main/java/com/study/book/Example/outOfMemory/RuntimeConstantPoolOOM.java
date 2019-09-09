package com.study.book.Example.outOfMemory;

import java.util.ArrayList;
import java.util.List;

/**
 * jdk8中木有持久代了，合并到native堆中了；
 * -XX:MetaspaceSize=5M -XX:MaxMetaspaceSize=7M
 * VM ARGS: -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        /*List<String> list = new ArrayList<>();
        int i = 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }*/
        String sb1 = new StringBuilder("ja").append("va").toString();
        System.out.println(sb1.intern() == sb1);

        String sb2 = new StringBuilder("Py").append("thon").toString();
        System.out.println(sb2.intern() == sb2);
    }
}
