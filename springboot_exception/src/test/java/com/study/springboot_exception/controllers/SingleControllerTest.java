package com.study.springboot_exception.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.study.springboot_exception.SpringbootExceptionApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootExceptionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SingleControllerTest {
    //    @MockBean
//    MockMvc mockMvc;
    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void testPrototype() {
        try {
            mockMvc.perform(get("/testPrototype"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testSingle() {
        try {
            mockMvc.perform(get("/testSingleObject"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}