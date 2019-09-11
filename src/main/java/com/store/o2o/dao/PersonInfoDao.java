package com.store.o2o.dao;

import com.store.o2o.entity.PersonInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonInfoDao {
    List<PersonInfo> queryPersonInfoList(@Param("personInfoCondition")PersonInfo personInfoCondition, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);


    int queryPersonInfoCount(@Param("personInfoCondition")PersonInfo personInfoCondition);


    PersonInfo queryPersonInfoById(long userId);


    int insertPersonInfo(PersonInfo personInfo);


    int updatePersonInfo(PersonInfo personInfo);
}
