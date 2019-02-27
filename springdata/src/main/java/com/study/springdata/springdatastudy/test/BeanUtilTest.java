package com.study.springdata.springdatastudy.test;

import com.study.springdata.springdatastudy.domain.User;
import com.study.springdata.springdatastudy.domain.UserCopy;
import org.springframework.beans.BeanUtils;

public class BeanUtilTest {
    public static void main(String[] args) {
        User user = new User();
        user.setName(String.valueOf(111));
        user.setId(123L);
        UserCopy userCopy = new UserCopy();
        BeanUtils.copyProperties(user,userCopy,UserCopy.class);
        System.out.println(userCopy);
    }
}
