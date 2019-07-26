package com.study.springboot_exception.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class ConcurrencyController {
    @PostMapping("concurrency")
    public HttpEntity doConcurrency(){
        try {
            TimeUnit.SECONDS.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HttpEntity("concurrency");
    }
}
