package com.store.o2o.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Area {
    //ID
    private Integer areaId;

    private  String areaName;

    private  Integer priority;

    private Date createTime;

    private Date lastEditTime;
}
