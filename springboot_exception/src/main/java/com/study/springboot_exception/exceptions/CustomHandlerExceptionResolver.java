package com.study.springboot_exception.exceptions;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
/**
 *  @Description
 *
 *
 *  ---UPDATE LOG---
 *  @Author qyz
 *  @Date 15:02 2019-03-28
 *  @Description 全局异常处理
 *  ------------ 级别最低，基本不用，太过于抽象
 **/
@Component
@Logger
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        System.out.println("----------------------"+httpServletRequest.getMethod());
        Method method = o != null && o instanceof HandlerMethod ? ((HandlerMethod) o).getMethod() : null;
        System.out.println("method is:" + method);
        System.out.println("exception is:" + e);
        return new ModelAndView();
    }
}
