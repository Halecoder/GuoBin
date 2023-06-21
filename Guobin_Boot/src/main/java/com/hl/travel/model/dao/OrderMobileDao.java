package com.hl.travel.model.dao;



import com.hl.travel.model.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMobileDao {

    List<Order> findByCondition(Order order);

    void add(Order order);

    Map findByIdForDetail(Integer id);

    int getTodayOrderNumber(String date);
    int getTodayVisitsNumber(String date);
    int getThisWeekAndMonthOrderNumber(Map<String, Object> map);
    int getThisWeekAndMonthVisitsNumber(Map<String, Object> map);
    List<Map<String,Object>> findHotSetmeal();
}
