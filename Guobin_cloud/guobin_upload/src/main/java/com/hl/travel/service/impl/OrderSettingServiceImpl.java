package com.hl.travel.service.impl;

import com.hl.travel.model.dao.OrderSettingDao;
import com.hl.travel.model.pojo.OrderSetting;
import com.hl.travel.service.OrderSettingService;
import com.hl.travel.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class OrderSettingServiceImpl implements OrderSettingService {


    @Autowired
    private OrderSettingDao orderSettingDao;



    @Override
    public void add(List<OrderSetting> orderSettings) {

        for (OrderSetting orderSetting : orderSettings) {
            // 判断当前的日期之前是否已经被设置过预约日期，使用当前时间作为条件查询数量
            long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
            // 如果设置过预约日期，更新number数量
            if (count>0){
                orderSettingDao.editNumberByOrderDate(orderSetting);
            }else {
                // 如果没有设置过预约日期，执行保存
                orderSettingDao.add(orderSetting);
            }

        }

    }

}
