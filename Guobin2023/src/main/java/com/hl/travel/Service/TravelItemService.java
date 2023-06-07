package com.hl.travel.Service;

import com.hl.travel.entity.TravelItem;
import com.hl.travel.vo.PageResult;

public interface TravelItemService {
    void add(TravelItem travelItem);

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void deleteById(Integer id);

    void edit(TravelItem travelItem);

    TravelItem findById(Integer id);
}
