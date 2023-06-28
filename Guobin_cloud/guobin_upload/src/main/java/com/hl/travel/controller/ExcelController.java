package com.hl.travel.controller;


import com.hl.travel.constant.MessageConstant;
import com.hl.travel.model.pojo.OrderSetting;
import com.hl.travel.model.vo.Result;
import com.hl.travel.service.OrderSettingService;
import com.hl.travel.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/excel")

public class ExcelController {


    @Autowired
    private OrderSettingService orderSettingService;

    /**
     * 批量导入预约设置
     * @param excelFile 上传的文件
     * @return
     */
    @PostMapping("/upload")
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
}
