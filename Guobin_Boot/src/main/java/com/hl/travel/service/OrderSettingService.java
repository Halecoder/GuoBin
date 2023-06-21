package com.hl.travel.service;



import com.hl.travel.model.pojo.OrderSetting;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


public interface OrderSettingService {

    void add(List<OrderSetting> orderSettings);

    List<Map> getOrderSettingByMonth(String date) throws ParseException;

    void editNumberByDate(OrderSetting orderSetting);
}
