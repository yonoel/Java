package com.study.springdata.springdatastudy.dao;

import com.study.springdata.springdatastudy.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@DataJpaTest
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    org.springframework.transaction.PlatformTransactionManager platformTransactionManager;

    public void saveOne() {
        User user = new User();
        user.setName("NO.1");
        userRepository.save(user);
        System.out.println(UUID.randomUUID());
        System.out.println("你好" + this.platformTransactionManager);
    }

    @Test
    public void test() {
        saveOne();
        new Thread(new Runnable() {
            @Override
            public void run() {
                saveOne();
            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}