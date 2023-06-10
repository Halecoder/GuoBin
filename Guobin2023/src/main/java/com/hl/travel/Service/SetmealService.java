package com.hl.travel.Service;

import com.hl.travel.entity.Setmeal;
import com.hl.travel.vo.PageResult;

import java.util.List;

public interface SetmealService {

        void upload();

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void add(Setmeal setmeal, Integer[] travelgroupIds);

    Setmeal findById(Integer id);

    List<Integer> findTravelGroupIdsBySetmealId(Integer id);

    void edit(Setmeal setmeal, Integer[] travelgroupIds);

    void deleteById(Integer id);
}
