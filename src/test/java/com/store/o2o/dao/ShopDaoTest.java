package com.store.o2o.dao;

import com.store.o2o.entity.Area;
import com.store.o2o.entity.PersonInfo;
import com.store.o2o.entity.Shop;
import com.store.o2o.entity.ShopCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopDaoTest {
    @Autowired
    private  ShopDao shopDao;



    @Test
    public void insertShop() {
        Shop shop=new Shop();
        PersonInfo owner = new PersonInfo();
        Area area=new Area();
        ShopCategory shopCategory=new ShopCategory();

        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopName("测试的店铺");
        shop.setShopAddr("test");
        shop.setPhone("123");
        shop.setShopDesc("test");
        shop.setShopImg("test");
        shop.setEnableStatus(1);
        shop.setCreateTime(new Date());
        shop.setAdvice("审核中");
        int effectedNum=shopDao.insertShop(shop);
        assertEquals(1,effectedNum);

    }

    @Test
    public void updateShop() {
        Shop shop=new Shop();
        shop.setShopId(36L);

        shop.setShopName("店铺");
        shop.setShopAddr("测试");
        shop.setLastEditTime(new Date());

        int effectedNum=shopDao.updateShop(shop);
        assertEquals(1,effectedNum);

    }

    @Test
    public void queryByShopId() {
        long shopId=52L;
        Shop shop=shopDao.queryByShopId(shopId);
        System.out.println("shopName"+shop.getShopName());
        //System.out.println("areaName"+shop.getArea().getAreaName());
    }

    @Test
    public void queryShopList() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shop.setOwner(owner);
        List<Shop> shopList=shopDao.queryShopList(shop,0,5);
        int count =shopDao.queryShopCount(shop);
        System.out.println("shop count"+count);
        System.out.println("shop nums "+shopList.size());
    }
}