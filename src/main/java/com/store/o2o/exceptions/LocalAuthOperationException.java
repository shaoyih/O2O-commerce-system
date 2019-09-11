package com.store.o2o.exceptions;

public class LocalAuthOperationException extends RuntimeException{
    private static final long serialVersionUID= 5822712676360819633L;
    public LocalAuthOperationException(String msg) {
        super(msg);
    }
}
