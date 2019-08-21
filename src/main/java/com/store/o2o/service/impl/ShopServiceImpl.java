package com.store.o2o.service.impl;

import com.store.o2o.dao.ShopDao;
import com.store.o2o.dto.ShopExecution;
import com.store.o2o.entity.Shop;
import com.store.o2o.enums.ShopStateEnum;
import com.store.o2o.exceptions.shopOperationException;
import com.store.o2o.service.ShopService;
import com.store.o2o.util.ImageUtil;
import com.store.o2o.util.PathUTil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {

    private ShopDao shopDao;


    public ShopServiceImpl(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg) {
        if(shop==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try{
            //assign values for new shop
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum=shopDao.insertShop(shop);
            if(effectedNum<=0){
                throw  new shopOperationException("店铺创建失败");
            }
            else{
                if(shopImg!=null){
                    try {
                        addShopImg(shop,shopImg);
                    }catch (Exception e){
                        throw new shopOperationException("addShopImg error"+e.getMessage());
                    }
                    //更新店铺的图片地址
                    effectedNum=shopDao.updateShop(shop);
                    if(effectedNum<=0){
                        throw new shopOperationException("update image failed");
                    }
                }
            }

        }catch (Exception e){
            throw new shopOperationException("addShop error: "+e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, File shopImg){
        String dest= PathUTil.getShopImgPath(shop.getShopId());
        String shopImgAddr= ImageUtil.generateThumbnail(shopImg,dest);
        shop.setShopImg(shopImgAddr);
    }
}
