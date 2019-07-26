package com.study.springboot_exception.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.TimeUnit;

@Controller
public class HtmlController {
    @GetMapping("index")
    public ModelAndView getIndex() {

        return new ModelAndView("index");
    }
}
