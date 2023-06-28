package com.hl.travel.clients;


import com.hl.travel.model.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "guobin-role")
public interface GuobinRoleClient {
    @RequestMapping("/user/api/findUserByUserName")
    User findUserByUserName(String username);
}
