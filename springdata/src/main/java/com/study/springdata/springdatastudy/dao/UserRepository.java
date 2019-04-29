package com.study.springdata.springdatastudy.dao;


import com.study.springdata.springdatastudy.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;




public interface UserRepository extends MyBaseRepository<User,Long>{
    Page<User> getDistinctByIdIn(Long[] ids, Pageable pageable);
    User getDistinctById(Long id);
    @Async
    Slice<User> getAllByIdIn(Long[] ids, Sort sort);

}
