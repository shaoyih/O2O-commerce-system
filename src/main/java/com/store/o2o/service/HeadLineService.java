package com.store.o2o.service;

import com.store.o2o.dto.HeadLineExecution;
import com.store.o2o.dto.ImageHolder;
import com.store.o2o.entity.HeadLine;

import java.util.List;

public interface HeadLineService {
    List<HeadLine> getHeadLineList(HeadLine headLineCondition);
//    HeadLineExecution addHeadLine(HeadLine headLine, ImageHolder thumbnail);
//    HeadLineExecution modifyHeadLine(HeadLine headLine, ImageHolder thumbnail);
//    HeadLineExecution removeHeadLine(long headLineId);
//    HeadLineExecution removeHeadLineList(List<Long> headLineIdList);

}
