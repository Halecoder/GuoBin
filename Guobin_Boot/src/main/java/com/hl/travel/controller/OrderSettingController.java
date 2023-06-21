package com.hl.travel.controller;



import com.hl.travel.constant.MessageConstant;

import com.hl.travel.model.pojo.OrderSetting;
import com.hl.travel.model.vo.Result;
import com.hl.travel.service.OrderMobileService;
import com.hl.travel.service.OrderSettingService;
import com.hl.travel.utils.POIUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/orderSetting")
public class OrderSettingController {

    @Autowired
    private OrderSettingService orderSettingService;

    @Autowired
    private OrderMobileService orderMobileService;

    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile) {

        //1.使用POI解析文件 得到List<String[]> list

        List<String[]> excels;

        try {
            excels = POIUtils.readExcel(excelFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //2.把List<String[]> list转成 List<OrderSetting> list
        List<OrderSetting> OrderSettings = new ArrayList<>();

        for (String[] excel : excels) {
            OrderSetting OrderSetting = new OrderSetting();
            OrderSetting.setOrderDate(new Date(excel[0]));
            OrderSetting.setNumber(Integer.parseInt(excel[1]));
            OrderSettings.add(OrderSetting);
        }


        //3.调用业务 进行保存

        orderSettingService.add(OrderSettings);

        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);


    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date) throws ParseException {//参数格式为：2022-12

        List<Map> maps = orderSettingService.getOrderSettingByMonth(date);
        return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,maps);


    }

    /**
     * 根据指定日期修改可预约人数
     * @param orderSetting
     * @return
     */
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){

            orderSettingService.editNumberByDate(orderSetting);
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
    }



}
