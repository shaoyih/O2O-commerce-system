package com.store.o2o.service;

import com.store.o2o.dto.ProductCategoryExecution;
import com.store.o2o.entity.ProductCategory;
import com.store.o2o.exceptions.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {
    public List<ProductCategory> getProductCategoryList(long shopId);
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;
    ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws ProductCategoryOperationException;
}
