package com.study.springboot_exception.exceptions;

public class CustomException extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
