package com.hl.travel.dao;

import com.github.pagehelper.Page;
import com.hl.travel.entity.TravelItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface TravelItemDao {
    void add(TravelItem travelItem);

    Page<TravelItem> findPage(@Param("queryString") String queryString);
}
