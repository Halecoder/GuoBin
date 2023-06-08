package com.hl.travel.Service;

import com.hl.travel.entity.TravelGroup;
import com.hl.travel.entity.TravelItem;
import com.hl.travel.vo.PageResult;

import java.util.List;

public interface TravelGroupService {

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void add(TravelGroup travelGroup,Integer[] travelItemIds);

    TravelGroup findById(Integer id);

    List<Integer> findTravelItemIdByTravelGroupId(Integer id);

    void edit(Integer[] travelItemIds, TravelGroup travelGroup);

    void deleteById(Integer id);
}
