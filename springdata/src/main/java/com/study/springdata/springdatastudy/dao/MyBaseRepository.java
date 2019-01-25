package com.study.springdata.springdatastudy.dao;


import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.Optional;

/**
 *  这是一个不用声明实体的。。repository，同时继续自spring的内置repository
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface MyBaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
    Optional<T> findById(ID id);

    <S extends T> S save(S entity);
}
