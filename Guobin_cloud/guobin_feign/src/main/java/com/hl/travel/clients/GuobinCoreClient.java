package com.hl.travel.clients;

import com.hl.travel.model.pojo.OrderSetting;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient("guobin-core")
public interface GuobinCoreClient {
    @RequestMapping("/api/findByOrderDate")
    OrderSetting findByOrderDate(String orderDate);

    @RequestMapping("/api/editReservationsByOrderDate")
    public void editReservationsByOrderDate(OrderSetting orderSetting);

    @RequestMapping("/api/findSetmealCount")
    List<Map<String, Object>> findSetmealCount();

}
