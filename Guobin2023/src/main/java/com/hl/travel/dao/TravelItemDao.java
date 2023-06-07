package com.hl.travel.dao;

import com.hl.travel.entity.TravelItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


public interface TravelItemDao {
    void add(TravelItem travelItem);
}
