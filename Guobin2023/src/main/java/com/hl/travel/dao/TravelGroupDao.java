package com.hl.travel.dao;

import com.github.pagehelper.Page;
import com.hl.travel.entity.TravelGroup;
import com.hl.travel.entity.TravelItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TravelGroupDao {
    Page<TravelGroup> findPage(@Param("queryString") String queryString);


    void setTravelGroupAndTravelItem(Map<String, Integer> map);

    void add(TravelGroup travelGroup);

    TravelGroup findById(Integer id);

    List<Integer> findTravelItemIdByTravelGroupId(Integer id);

    void edit(TravelGroup travelGroup);

    void deleteTravelGroupAndTravelItem(Integer id);

    void deleteById(Integer id);
}
