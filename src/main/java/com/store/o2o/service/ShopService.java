package com.store.o2o.service;

import com.store.o2o.dto.ShopExecution;
import com.store.o2o.entity.Shop;
import com.store.o2o.entity.ShopCategory;
import com.store.o2o.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

public interface ShopService {
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream,String filename) throws ShopOperationException;
    public Shop getByShopId(long id);
    public ShopExecution modifyShop(Shop shop, InputStream inputStream,String filename) throws ShopOperationException;
}
