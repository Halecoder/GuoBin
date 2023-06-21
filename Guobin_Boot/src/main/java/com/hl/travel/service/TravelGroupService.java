package com.hl.travel.service;



import com.hl.travel.model.pojo.TravelGroup;
import com.hl.travel.model.vo.PageResult;

import java.util.List;

public interface TravelGroupService {

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void add(TravelGroup travelGroup, Integer[] travelItemIds);

    TravelGroup findById(Integer id);

    List<Integer> findTravelItemIdByTravelGroupId(Integer id);

    void edit(Integer[] travelItemIds, TravelGroup travelGroup);

    void deleteById(Integer id);

    List<TravelGroup> findAll();
}
