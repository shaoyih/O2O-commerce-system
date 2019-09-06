package com.store.o2o.exceptions;

public class ProductOperationException extends RuntimeException {

    private static final long serialVersionUID = -8454680099429697481L;

    public ProductOperationException(String msg) {
        super(msg);
    }
}
