package com.store.o2o.dao;

import com.store.o2o.entity.Shop;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopDao {
    int insertShop(Shop shop);
    int updateShop(Shop shop);
    Shop queryByShopId(long shopId);
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,@Param("pageSize") int pageSize);
    int queryShopCount(@org.apache.ibatis.annotations.Param("shopCondition") Shop shopCondition);
}
