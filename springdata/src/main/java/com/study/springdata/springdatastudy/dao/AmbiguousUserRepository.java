package com.study.springdata.springdatastudy.dao;


import com.study.springdata.springdatastudy.domain.User;

public interface AmbiguousUserRepository extends MyBaseRepository<User,Long> {

}
