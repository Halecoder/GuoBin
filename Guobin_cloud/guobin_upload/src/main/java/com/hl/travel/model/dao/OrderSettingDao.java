package com.hl.travel.model.dao;


import com.hl.travel.model.pojo.OrderSetting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderSettingDao {

    long findCountByOrderDate(Date orderDate);

    void editNumberByOrderDate(OrderSetting orderDate);

    void add(OrderSetting orderSetting);
}
