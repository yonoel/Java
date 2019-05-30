package com.study.springboot_exception.service;

import org.springframework.stereotype.Service;

@Service
public class StringServiceImplA implements StringService {
    @Override
    public String doTask(int num) {
        return String.format("i'm A services --------%d", num);
    }
}
