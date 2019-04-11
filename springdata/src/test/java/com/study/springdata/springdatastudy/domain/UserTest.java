package com.study.springdata.springdatastudy.domain;

import com.study.springdata.SpringdataApplication;
import com.study.springdata.springdatastudy.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringdataApplication.class)
public class UserTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void test1() {
        User user = userRepository.getDistinctById(1L);
    }
}