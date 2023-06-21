package com.hl.travel.service.impl;


import com.hl.travel.model.dao.OrderSettingDao;
import com.hl.travel.model.pojo.OrderSetting;
import com.hl.travel.service.OrderSettingService;
import com.hl.travel.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Override
    public List<Map> getOrderSettingByMonth(String date) throws ParseException {
        String dateBegin = date + "-1";
        String dateEnd = DateUtils.getLastDayOfMonth(date);

        List<Map<String,String>> list =  orderSettingDao.getOrderSettingByMonth(dateBegin,dateEnd);

        // 3.将List<OrderSetting>，组织成List<Map>
        List<Map> mapList = new ArrayList<>();

        for (Map<String, String> orderSetting : list) {

            Map<String,String> map = new HashMap<>();
            SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("dd");
            //字符串转日期,只需要日
            String orderDate = SimpleDateFormat.format(orderSetting.get("orderDate"));
            map.put("date", orderDate);
            map.put("number",orderSetting.get("number"));
            map.put("reservations",orderSetting.get("reservations"));
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if(count > 0){
            //当前日期已经进行了预约设置，需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else{
            //当前日期没有进行预约设置，进行添加操作
            orderSettingDao.add(orderSetting);
        }
    }
}
