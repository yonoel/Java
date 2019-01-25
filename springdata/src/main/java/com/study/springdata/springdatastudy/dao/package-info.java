/**
 * 正确的packgeinfo.java
 */
@NonNullApi
package com.study.springdata.springdatastudy.dao;

import org.springframework.lang.NonNullApi;

class PackageInfo{
    public void say(){
        System.out.println("hello");
    }

}
class PackageInfoGeneric<T extends Throwable>{
    private T obj;

    public void setObj(T obj) {
        this.obj = obj;
    }
    public void say(){
        System.out.println("hello "+obj);
    }
}

interface PackageInfoInterface {
    void test();
}

class PackageInfoConst {
    public static final String MESSAGE = "Annotation Study";
}