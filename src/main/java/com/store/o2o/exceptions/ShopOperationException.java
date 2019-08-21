package com.store.o2o.exceptions;

public class ShopOperationException extends  RuntimeException {
    private static final long serialVersionUID= 4495334852120567515L;
    public ShopOperationException(String message) {
        super(message);
    }
}
