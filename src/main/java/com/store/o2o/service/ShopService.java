package com.store.o2o.service;

import com.store.o2o.dto.ShopExecution;
import com.store.o2o.entity.Shop;

import java.io.File;

public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg);
}
