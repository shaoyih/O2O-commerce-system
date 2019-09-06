package com.store.o2o.service;

import com.store.o2o.dto.ImageHolder;
import com.store.o2o.dto.ShopExecution;
import com.store.o2o.entity.Area;
import com.store.o2o.entity.PersonInfo;
import com.store.o2o.entity.Shop;
import com.store.o2o.entity.ShopCategory;
import com.store.o2o.enums.ShopStateEnum;
import com.store.o2o.exceptions.ShopOperationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopServiceTest {

    @Autowired
    private ShopService shopService;
    @Test
    public void addShop() throws FileNotFoundException {
        Shop shop=new Shop();
        PersonInfo owner = new PersonInfo();
        Area area=new Area();
        ShopCategory shopCategory=new ShopCategory();

        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopName("测试的店铺3");
        shop.setShopAddr("test1");
        shop.setPhone("test1");
        shop.setShopDesc("test1");
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setCreateTime(new Date());
        shop.setAdvice("审核中");

        File shopImg=new File("C:\\Users\\95155\\Desktop\\image\\vets.png");
        InputStream is= new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder("vet.jpg", is);
        ShopExecution se=shopService.addShop(shop,imageHolder);
        assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
    }
    @Test
    public void modifyShop() throws ShopOperationException,FileNotFoundException{
        Shop shop=new Shop();
        shop.setShopId(52L);
        shop.setShopName("两家小店");
        File shopImg=new File("C:\\Users\\95155\\Desktop\\image\\wp2552767-wallpaper-naruto-hd.jpg");
        InputStream is= new FileInputStream(shopImg);
        ImageHolder imageHolder= new ImageHolder("mingren.jpg",is);
        ShopExecution shopExecution =shopService.modifyShop(shop,imageHolder);
        System.out.println("new image name"+shopExecution.getShop().getShopImg());
    }
    @Test
    public void queryShopList() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shop.setOwner(owner);
        ShopExecution se=shopService.getShopList(shop,0,5);
        System.out.println(se.getShopList().size());
        System.out.println(se.getCount());
    }
}