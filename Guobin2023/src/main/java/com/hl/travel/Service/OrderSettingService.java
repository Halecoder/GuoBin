package com.hl.travel.Service;

import com.hl.travel.entity.OrderSetting;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


public interface OrderSettingService {

    void add(List<OrderSetting> orderSettings);

    List<Map> getOrderSettingByMonth(String date) throws ParseException;

    void editNumberByDate(OrderSetting orderSetting);
}
