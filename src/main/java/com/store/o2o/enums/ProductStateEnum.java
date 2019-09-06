package com.store.o2o.enums;

import lombok.Getter;

@Getter
public enum  ProductStateEnum {

    OFFLINE(-1, "invalid product"), DOWN(0, "下架"), SUCCESS(1, "success"), INNER_ERROR(-1001, "operation failed"), EMPTY(-1002, "product is empty");

    private int state;
    private String stateInfo;

    private ProductStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public static ProductStateEnum stateOf(int index) {
        for (ProductStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
