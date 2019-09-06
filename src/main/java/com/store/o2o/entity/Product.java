package com.store.o2o.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Product {
    private Long productId;
    private String productName;
    private String productDesc;
    //simple image
    private String imgAddr;
    private String normalPrice;
    private String promotionPrice;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    //-1 disabled, 0 not displayed, 1 displayed
    private Integer enableStatus;
    private Shop shop;
    private List<ProductImg> productImgList;
    private ProductCategory productCategory;
    private Integer point;
}
