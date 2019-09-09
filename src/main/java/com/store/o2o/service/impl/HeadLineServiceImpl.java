package com.store.o2o.service.impl;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.store.o2o.dao.HeadLineDao;
import com.store.o2o.entity.HeadLine;
import com.store.o2o.service.HeadLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;


    private static Logger logger = LoggerFactory.getLogger(HeadLineServiceImpl.class);

    @Override
    @Transactional
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {

        return headLineDao.queryHeadLine(headLineCondition);
    }
}

