package com.hl.travel.Service.impl;

import com.hl.travel.dao.TravelItemDao;
import com.hl.travel.Service.TravelItemService;
import com.hl.travel.entity.TravelItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TravelItemServiceImpl implements TravelItemService {

    @Autowired
    private TravelItemDao travelItemDao;
    @Override
    public void add(TravelItem travelItem) {
        travelItemDao.add(travelItem);
    }
}
