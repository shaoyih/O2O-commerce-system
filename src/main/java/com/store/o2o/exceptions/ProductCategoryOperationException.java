package com.store.o2o.exceptions;

public class ProductCategoryOperationException extends RuntimeException{
    private static final long serialVersionUID= -1934119441072770627L;
    public ProductCategoryOperationException(String msg) {
        super(msg);
    }
}
