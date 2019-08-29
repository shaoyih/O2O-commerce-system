package com.store.o2o.service.impl;

import com.store.o2o.dao.ShopDao;
import com.store.o2o.dto.ShopExecution;
import com.store.o2o.entity.Shop;
import com.store.o2o.enums.ShopStateEnum;
import com.store.o2o.exceptions.ShopOperationException;
import com.store.o2o.service.ShopService;
import com.store.o2o.util.ImageUtil;
import com.store.o2o.util.PageCalculator;
import com.store.o2o.util.PathUTil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;


//    //public ShopServiceImpl(ShopDao shopDao) {
//        this.shopDao = shopDao;
//    }

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String filename) {
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
                throw  new ShopOperationException("店铺创建失败");
            }
            else{
                if(shopImgInputStream!=null){
                    try {
                        addShopImg(shop,shopImgInputStream,filename);
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImg error"+e.getMessage());
                    }
                    //更新店铺的图片地址
                    effectedNum=shopDao.updateShop(shop);
                    if(effectedNum<=0){
                        throw new ShopOperationException("update image failed");
                    }
                }
            }

        }catch (Exception e){
            throw new ShopOperationException("addShop error: "+e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    private void addShopImg(Shop shop, InputStream shopImgInputStream, String filename){
        String dest= PathUTil.getShopImgPath(shop.getShopId());
        String shopImgAddr= ImageUtil.generateThumbnail(shopImgInputStream,dest,filename);
        shop.setShopImg(shopImgAddr);
    }

    @Override
    public Shop getByShopId(long id) {
        return shopDao.queryByShopId(id);
    }


    @Override
    public ShopExecution modifyShop(Shop shop, InputStream inputStream, String filename) throws ShopOperationException {
        if(shop==null || shop.getShopId()==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }else{
            //update shop img
            if(inputStream!=null && filename!=null && !"".equals(filename)){
                Shop tempShop= shopDao.queryByShopId(shop.getShopId());
                if(tempShop.getShopImg()!=null){
                    ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                }
                addShopImg(shop,inputStream,filename);
            }
            //update shop info
            shop.setLastEditTime(new Date());
            int effectedNum=shopDao.updateShop(shop);
            if(effectedNum<=0){
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            }
            else{
                shop=shopDao.queryByShopId(shop.getShopId());
                return new ShopExecution(ShopStateEnum.SUCCESS,shop);
            }

        }
    }

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex= PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Shop> shopList=shopDao.queryShopList(shopCondition,rowIndex,pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se=new ShopExecution();
        if(shopList!=null){
            se.setShopList(shopList);
            se.setCount(count);
        }else{
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return se;

    }
}
