package com.hl.travel.model.dao;

import com.github.pagehelper.Page;
import com.hl.travel.model.pojo.Setmeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SetmealDao {

    Page<Setmeal> findPage(@Param("value") String queryString);

    void add(Setmeal setmeal);

    void setSetmealAndTravelGroup(Map<String, Integer> map);

    Setmeal findById(Integer id);

    List<Integer> findTravelGroupIdsBySetmealId(Integer id);

    void edit(Setmeal setmeal);

    void deleteSetmealAndTravelGroup(Integer id);

    void deleteById(Integer id);

    List<Setmeal> findAll();

    Setmeal findDescById(Integer id);

    List<Map<String, Object>> findSetmealCount();
}
