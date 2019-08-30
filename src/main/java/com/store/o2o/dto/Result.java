package com.store.o2o.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T> {

    private boolean success;

    private T data;

    private String errorMsg;

    private int errorCode;

    public Result() {
    }


    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }


    public Result(boolean success, int errorCode, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }


}
