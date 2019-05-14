package com.study.springboot_exception.pojo;

import org.springframework.stereotype.Component;
@Component
public class SingletonObject {
    private String name;

    public SingletonObject(String name) {
        this.name = name;
    }

    public SingletonObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
