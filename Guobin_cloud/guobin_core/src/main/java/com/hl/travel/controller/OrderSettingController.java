package com.hl.travel.controller;


import com.hl.travel.constant.MessageConstant;
import com.hl.travel.model.pojo.OrderSetting;
import com.hl.travel.model.vo.Result;
import com.hl.travel.service.OrderSettingService;
import com.hl.travel.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 后台预约设置
 */
@RestController

@RequestMapping("/orderSetting")
//@Tag(name = "后台预约设置相关接口")
public class OrderSettingController {

    @Autowired
    private OrderSettingService orderSettingService;



    /**
     * 根据月份查询预约数据
     * @param date 格式为：2022-12
     * @return
     * @throws ParseException
     */
    @GetMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date) throws ParseException {//参数格式为：2022-12

        List<Map> maps = orderSettingService.getOrderSettingByMonth(date);
        return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,maps);


    }

    /**
     * 根据指定日期修改可预约人数
     * @param orderSetting
     * @return
     */
    @PostMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){

            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
    }


    //添加Feign接口

    /**
     * 根据日期查询预约设置信息
     * @param orderDate 日期
     * @return
     */
    @PostMapping("/api/findByOrderDate")
    public OrderSetting findByOrderDate(Date orderDate){

        return orderSettingService.findByOrderDate(orderDate);
    }


    @RequestMapping("/api/editReservationsByOrderDate")
    public void editReservationsByOrderDate(Date orderDate){
        orderSettingService.editReservationsByOrderDate(orderDate);
    }



}
