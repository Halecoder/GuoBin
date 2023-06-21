package com.hl.travel.model.dao;


import com.github.pagehelper.Page;
import com.hl.travel.model.pojo.TravelGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TravelGroupDao {
    Page<TravelGroup> findPage(@Param("queryString") String queryString);


    void setTravelGroupAndTravelItem(Map<String, Integer> map);

    void add(TravelGroup travelGroup);

    TravelGroup findById(Integer id);

    List<Integer> findTravelItemIdByTravelGroupId(Integer id);

    void edit(TravelGroup travelGroup);

    void deleteTravelGroupAndTravelItem(Integer id);

    void deleteById(Integer id);

    List<TravelGroup> findAll();

    long findCountByTravelGroupGroupId(Integer id);

    /**
     * 根据跟团游Id查套餐游Id
     * @param id
     * @return
     */
    List<TravelGroup> findTravelGroupListById(Integer id);

}
