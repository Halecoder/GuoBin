package com.hl.travel.dao;

import com.hl.travel.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderMobileDao {

    List<Order> findByCondition(Order order);

    void add(Order order);

    Map findByIdForDetail(Integer id);
}
