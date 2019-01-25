package com.miaoder.springdatastudy.dao;


import com.study.springdata.springdatastudy.SpringdatastudyApplication;
import com.study.springdata.springdatastudy.dao.UserRepository;
import com.study.springdata.springdatastudy.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringdatastudyApplication.class})
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void getDistinctByIdIn() {
        Long id = 1L;
       // User user =  userRepository.getDistinctById(id);
        // assert  null != user:"通知";
        // System.out.println(user.getName());
        Long[] ids = {1L,2L};
        Page<User> pages = userRepository.getDistinctByIdIn(ids,PageRequest.of(0,5));
        pages.getTotalElements();
    }

    @Test
    public void getByEmailAddress() {
    }

    @Test
    public void findByEmailAddress() {
    }

    @Test
    public void findOptionalByEmailAddress() {
    }
}