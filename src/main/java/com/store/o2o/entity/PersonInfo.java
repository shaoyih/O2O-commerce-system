package com.store.o2o.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PersonInfo {
    private Long userId;
    private String name;
    private String profileImg;
    private String email;
    private String gender;
    //0.disabled 1.enabled
    private Integer enableStatus;
    //1. customer 2. seller 3.administrator
    private Integer userType;
    private Date createTime;
    private Date lastEditTime;
}
