package com.store.o2o.dao;

import com.store.o2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HeadLineDao {

    List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
    HeadLine queryHeadLineById(long lineId);
    List<HeadLine> queryHeadLineByIds(List<Long> lineIdList);
    int insertHeadLine(HeadLine headLine);
    int updateHeadLine(HeadLine headLine);
    int deleteHeadLine(long headLineId);
    int batchDeleteHeadLine(List<Long> lineIdList);
}
