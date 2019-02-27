package com.study.springdata.springdatastudy.controller;

import com.study.springdata.springdatastudy.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    UserRepository userRepository;
    @RequestMapping("index")
    String goToIndex(){

        return "";
    }
    @GetMapping("page")
    HttpEntity  PagedResources(Pageable pageable){
        userRepository.getDistinctById(1L);
        return new ResponseEntity(1, HttpStatus.OK);
    }
}
