package com.store.o2o.dao;

import com.store.o2o.entity.Product;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ProductDao {
    int insertProduct(Product product);
    Product queryProductById(long productId);
    List<Product> queryProductList(@Param("productCondition") Product productCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
    int queryProductCount(@Param("productCondition") Product productCondition);
    int updateProduct(Product product);
    int deleteProduct(@Param("productId") long productId, @Param("shopId") long shopId);
    int updateProductCategoryToNull(long productCategoryId);

}
