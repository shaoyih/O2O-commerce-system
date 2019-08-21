package com.store.o2o.exceptions;

public class shopOperationException extends  RuntimeException {
    private static final long serialVersionUID= 4495334852120567515L;
    public shopOperationException(String message) {
        super(message);
    }
}
