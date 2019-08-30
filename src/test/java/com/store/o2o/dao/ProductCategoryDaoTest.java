package com.store.o2o.dao;

import com.store.o2o.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Test
    public void queryProductCategory() {
        long shopId=1;
        List<ProductCategory> productCategoryDaoList= productCategoryDao.queryProductCategoryList(shopId);
        System.out.println("store customized types are "+ productCategoryDaoList.size());
    }
}