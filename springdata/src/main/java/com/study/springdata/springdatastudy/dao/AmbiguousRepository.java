package com.study.springdata.springdatastudy.dao;

import com.miaoder.springdatastudy.domain.User;
import org.springframework.data.repository.Repository;

public interface AmbiguousRepository extends Repository<User,Long> {
}
