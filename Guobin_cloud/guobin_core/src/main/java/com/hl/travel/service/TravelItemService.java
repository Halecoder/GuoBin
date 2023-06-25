package com.hl.travel.service;



import com.hl.travel.model.pojo.TravelItem;
import com.hl.travel.model.vo.PageResult;

import java.util.List;

public interface TravelItemService {
    void add(TravelItem travelItem);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void deleteById(Integer id);

    void edit(TravelItem travelItem);

    TravelItem findById(Integer id);

    List<TravelItem> findAllItem();
}
