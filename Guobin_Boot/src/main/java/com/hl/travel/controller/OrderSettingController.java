package com.hl.travel.controller;



import com.hl.travel.constant.MessageConstant;

import com.hl.travel.model.pojo.OrderSetting;
import com.hl.travel.model.vo.Result;
import com.hl.travel.service.OrderMobileService;
import com.hl.travel.service.OrderSettingService;
import com.hl.travel.utils.POIUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@CrossOrigin
@RequestMapping("/orderSetting")
@Tag(name = "后台预约设置相关接口")
public class OrderSettingController {

    @Autowired
    private OrderSettingService orderSettingService;

    @Autowired
    private OrderMobileService orderMobileService;

    /**
     * 批量导入预约设置
     * @param excelFile 上传的文件
     * @return
     */
    @GetMapping("/upload")
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



}
