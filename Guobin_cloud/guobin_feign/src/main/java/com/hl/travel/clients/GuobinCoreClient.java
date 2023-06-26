package com.hl.travel.clients;

import com.hl.travel.model.pojo.OrderSetting;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "guobin-core")
public interface GuobinCoreClient {
    @RequestMapping("/orderSetting/api/findByOrderDate")
    OrderSetting findByOrderDate(String orderDate);

    @RequestMapping("/orderSetting/api/editReservationsByOrderDate")
    public void editReservationsByOrderDate(OrderSetting orderSetting);

    @RequestMapping("/setmeal/api/findSetmealCount")
    List<Map<String, Object>> findSetmealCount();

}
