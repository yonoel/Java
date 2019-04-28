package com.study.springboot_exception.controllers;

import com.study.springboot_exception.pojo.PrototypeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingleController {
    @Autowired
    PrototypeObject prototypeObject;
    @Autowired
    ApplicationContext context;
    @GetMapping("/testSingleObject")
    public void testSingleObject(){
        for (int i = 0; i < 2; i++) {
            new Thread(prototypeObject).start();
        }
    }
    @GetMapping("/testPrototype")
    public void testPrototype() {
//        new Thread(prototypeObject).start();
        for (int i = 0; i < 2; i++) {
            PrototypeObject prototypeObject = (PrototypeObject) context.getBean("prototypeObject");
            new Thread(prototypeObject).start();
        }
    }
}
