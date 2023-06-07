package com.hl.travel.controller;

import com.hl.travel.Service.TravelItemService;
import com.hl.travel.constant.MessageConstant;
import com.hl.travel.entity.TravelItem;
import com.hl.travel.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin //跨域
@RestController
@RequestMapping(value = "/travelItem")
public class TravelItemController {

    @Autowired
    private TravelItemService travelItemService;

    //新增
    @RequestMapping("/add")
    public Result add(@RequestBody TravelItem travelItem) {
        try {
            travelItemService.add(travelItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_TRAVELITEM_SUCCESS);
    }
}


