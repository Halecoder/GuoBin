package com.hl.travel.dao;

import com.github.pagehelper.Page;
import com.hl.travel.entity.TravelItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TravelItemDao {
    void add(TravelItem travelItem);

    Page<TravelItem> findPage(@Param("queryString") String queryString);

    long findCountByTravelItemItemId(Integer id);

    void deleteById(Integer id);

    void edit(TravelItem travelItem);

    TravelItem findById(Integer id);

    List<TravelItem> findAllItem();

    /**
     * 根据套餐游Id查自由行id
     * @param id
     * @return
     */
    List<TravelItem>  findTravelItemListById(Integer id);
}
