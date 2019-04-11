package com.study.springboot_exception.controllers;

import com.study.springboot_exception.exceptions.ExceptionTest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GlobalController {
    @GetMapping("testGlobal")
    public ModelAndView testGlobal() {
        int i = 1 / 0;
        return new ModelAndView();
    }

    @GetMapping("test")
    public void test() throws ExceptionTest {
        throw new ExceptionTest("test");
    }

    /**
     * @Description ---UPDATE LOG---
     * @Author qyz
     * @Date 14:52 2019-03-28
     * @Description
     * 1.这个注解的异常处理在单独的controller里重写
     * 2.捕获本类的异常，优先级别最高
     * 3.一个类仅允许一个@注解存在
     * 假设后面的异常处理类相同，如果相同，报这个java.lang.IllegalStateException: Ambiguous @ExceptionHandler method
     * 如果不同，可以有2个
     * ------------
     **/
    @ExceptionHandler(ExceptionTest.class)
    public String exception(ExceptionTest e) {
        e.printStackTrace();
        return "test ExceptionHandler";
    }

  /*  @ExceptionHandler(Exception.class)
    public String exception2(Exception e) {
        return "test 5";
    }
*/

}
