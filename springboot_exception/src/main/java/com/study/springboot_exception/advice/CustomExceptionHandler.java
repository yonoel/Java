package com.study.springboot_exception.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
/**
 *  @Description
 *
 *
 *  ---UPDATE LOG---
 *  @Author qyz
 *  @Date 15:19 2019-03-28
 *  @Description  切面处理，免除了单个controller里配置handler的麻烦
 *  ------------
 **/
@ControllerAdvice
public class CustomExceptionHandler {
//    @ExceptionHandler(ArithmeticException.class)
//    public ModelAndView customArithmeticException(ArithmeticException e) {
//        System.out.println("---------------that is a "+e);
//        return new ModelAndView();
//    }
}
