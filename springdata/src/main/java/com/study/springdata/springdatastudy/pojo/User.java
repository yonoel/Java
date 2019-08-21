package com.study.springdata.springdatastudy.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class User {
    private String id;
    private String nickName;
    private String name;
    private int level;
    private Date joinTime;
    private Date loginTime;
    private Date loginOutTime;
    private int weekFeat;
    private int totalFeat;
    private int score;
}
