package com.study.springboot_exception.service;

import org.springframework.stereotype.Service;

@Service
public class StringServiceImplB implements StringService {
    @Override
    public String doTask(int num) {
        return String.format("i'm B services --------%d", num);
    }
}
