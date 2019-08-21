package com.store.o2o.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Shop {
    private Long shopId;
    private String shopName;
    private String shopDesc;
    private String shopAddr;
    private String phone;
    private String shopImg;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    //-1 disabled, 0 checking, 1 enabled
    private Integer enableStatus;
    //advice from administrator to owner
    private String advice;
    private Area area;
    private PersonInfo owner;
    private ShopCategory shopCategory;

}
