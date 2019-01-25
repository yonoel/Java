package com.study.springdata.springdatastudy.dao;


import com.study.springdata.springdatastudy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRepository extends JpaRepository<User, Long> {
}
