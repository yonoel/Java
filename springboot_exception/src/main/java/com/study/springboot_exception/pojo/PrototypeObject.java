package com.study.springboot_exception.pojo;

import com.study.springboot_exception.SpringbootExceptionApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class PrototypeObject implements Runnable {
    public String name;
    public String password;
    @Autowired
    SingletonObject singletonObject;

    @Override
    public void run() {
        System.out.println("prototype's " + this);
        System.out.println("single's " + singletonObject);
    }
}
