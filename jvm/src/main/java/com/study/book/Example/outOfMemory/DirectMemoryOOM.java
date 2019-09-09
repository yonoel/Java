package com.study.book.Example.outOfMemory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM ARGS:-XX:MaxDirectMemorySize=size
 */
public class DirectMemoryOOM {
    private static int size = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while (true) unsafe.allocateMemory(size);

    }
}
