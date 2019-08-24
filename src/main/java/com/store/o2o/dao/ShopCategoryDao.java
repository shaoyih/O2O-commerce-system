package com.store.o2o.dao;

import com.store.o2o.entity.ShopCategory;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopCategoryDao {
    //List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
    List<ShopCategory> queryShopCategory(@org.apache.ibatis.annotations.Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
