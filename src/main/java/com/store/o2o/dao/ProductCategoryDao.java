package com.store.o2o.dao;

import com.store.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    //List<ProductCategory> queryProductCategory(long shopId);
    List<ProductCategory> queryProductCategoryList(long shopId);
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);

}
