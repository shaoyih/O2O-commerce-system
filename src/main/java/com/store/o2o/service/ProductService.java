package com.store.o2o.service;

import com.store.o2o.dto.ImageHolder;
import com.store.o2o.dto.ProductExecution;
import com.store.o2o.entity.Product;
import com.store.o2o.exceptions.ProductOperationException;

import java.util.List;

public interface ProductService {
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
            throws ProductOperationException;

    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
            throws ProductOperationException;

    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    Product getProductById(long productId);

}
