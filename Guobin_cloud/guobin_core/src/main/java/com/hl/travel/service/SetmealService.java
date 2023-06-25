package com.hl.travel.service;


import com.hl.travel.model.pojo.Setmeal;
import com.hl.travel.model.vo.PageResult;

import java.util.List;
import java.util.Map;

public interface SetmealService {


    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void add(Setmeal setmeal, Integer[] travelgroupIds);

    Setmeal findById(Integer id);

    List<Integer> findTravelGroupIdsBySetmealId(Integer id);

    void edit(Setmeal setmeal, Integer[] travelgroupIds);

    void deleteById(Integer id);

    List<Setmeal> findAll();

    Setmeal findDescById(Integer id);

    List<Map<String, Object>> findSetmealCount();
}
