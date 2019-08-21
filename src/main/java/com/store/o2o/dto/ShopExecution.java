package com.store.o2o.dto;

import com.store.o2o.entity.Shop;
import com.store.o2o.enums.ShopStateEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
public class ShopExecution {
    //状态
    private  int state;
    private String stateInfo;

    //店铺
    private int count;
    private Shop shop;
    private List<Shop> shopList;

    public ShopExecution() {
    }

    /**
     * when shop operation failed we use this constructor
     * @param shopStateEnum
     */
    public ShopExecution(ShopStateEnum shopStateEnum){
        this.state=shopStateEnum.getState();
        this.stateInfo=shopStateEnum.getStateInfo();
    }

    /**
     * when shop operation successed we this constructor
     * @param shopStateEnum
     * @param shop
     */
    public ShopExecution(ShopStateEnum shopStateEnum, Shop shop){
        this.state=shopStateEnum.getState();
        this.stateInfo=shopStateEnum.getStateInfo();
        this.shop=shop;
    }

    /**
     * Same Above
     * @param shopStateEnum
     * @param shopList
     */
    public ShopExecution(ShopStateEnum shopStateEnum, List<Shop> shopList){
        this.state=shopStateEnum.getState();
        this.stateInfo=shopStateEnum.getStateInfo();
        this.shopList=shopList;
    }

}
