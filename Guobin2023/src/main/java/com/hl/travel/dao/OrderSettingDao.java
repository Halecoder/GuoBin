package com.hl.travel.dao;

import com.hl.travel.entity.OrderSetting;
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

    List<Map<String, String>> getOrderSettingByMonth(@Param("dateBegin") String dateBegin, @Param("dateEnd") String dateEnd);

    OrderSetting findByOrderDate(String orderDate);

    void editReservationsByOrderDate(OrderSetting orderSetting);
}
