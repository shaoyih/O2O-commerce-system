package com.store.o2o.enums;

import lombok.Getter;

@Getter
public enum ShopStateEnum {
    CHECK(0,"审核中"),OFFLINE(-1,"非法店铺"),
    SUCCESS(1,"操作成功"),PASS(2,"通过审核"),
    INNER_ERROR(-1001,"内部错误"), NULL_SHOPID(-1002,"ShopId为空"),
    NULL_SHOP(-1003,"shop定义为空");

    private int state;
    private String stateInfo;
    ShopStateEnum(int state, String stateInfo) {
        this.state=state;
        this.stateInfo=stateInfo;
    }
    public  static ShopStateEnum stateOf(int state){
        for(ShopStateEnum shopStateEnum: values()){
            if(shopStateEnum.getState()==state){
                return shopStateEnum;
            }
        }
        return null;
    }
}
