package com.store.o2o.dto;

import com.store.o2o.entity.HeadLine;
import com.store.o2o.enums.HeadLineStateEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class HeadLineExecution {
    private int state;

    // 状态标识
    private String stateInfo;

    // 店铺数量
    private int count;

    // 操作的award（增删改商品的时候用）
    private HeadLine headLine;


    private List<HeadLine> headLineList;

    public HeadLineExecution() {
    }


    public HeadLineExecution(HeadLineStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }


    public HeadLineExecution(HeadLineStateEnum stateEnum, HeadLine headLine) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.headLine = headLine;
    }


    public HeadLineExecution(HeadLineStateEnum stateEnum, List<HeadLine> headLineList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.headLineList = headLineList;
    }
}
