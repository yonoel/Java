package com.study.springboot_exception.advice;

import com.study.springboot_exception.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Register {
    @Autowired
    List<StringService> stringServices;
    private StringService service;

    private void chooseService(int num) {
        if (num > 10) service = stringServices.get(1);
        else service = stringServices.get(0);

    }

    public String doTask(int num) {
        chooseService(num);
        return service.doTask(num);
    }
}
