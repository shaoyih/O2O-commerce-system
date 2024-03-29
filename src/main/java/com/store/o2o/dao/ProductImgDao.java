package com.store.o2o.dao;

import com.store.o2o.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {
    List<ProductImg> queryProductImgList(long productId);
    int batchInsertProductImg(List<ProductImg> productImgList);
    int deleteProductImgByProductId(long productId);

}
