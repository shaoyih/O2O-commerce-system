package com.store.o2o.service.impl;

import com.store.o2o.dao.AreaDao;
import com.store.o2o.entity.Area;
import com.store.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    private AreaDao areaDao;

    public AreaServiceImpl(AreaDao areaDao) {
        this.areaDao = areaDao;
    }

    @Override
    public List<Area> getAreaList(){
        return areaDao.queryArea();
    }
}
