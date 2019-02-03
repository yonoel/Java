package com.study.springdata.springdatastudy.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class UserCopy {

    private Long id;

    private Integer name;
    private Long password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserCopy{" +
                "id=" + id +
                ", name=" + name +
                ", password=" + password +
                '}';
    }
}
