package com.hl.travel.controller;

import com.hl.travel.Service.OrderMobileService;
import com.hl.travel.constant.MessageConstant;
import com.hl.travel.constant.RedisMessageConstant;
import com.hl.travel.entity.Order;
import com.hl.travel.util.SMSUtils;
import com.hl.travel.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderMobileController {
    private final OrderMobileService orderMobileService;

    private final JedisPool jedisPool;

    /**
      * 旅游预约
      * @param map
      * @return
      */
    @RequestMapping("/submit")
    public Result submitOrder(@RequestBody Map map) {
        //获取用户输入的验证码
        String validateCode = (String) map.get("validateCode");
        //获取用户输入的手机号
        String telephone = (String) map.get("telephone");
        //从redis中获取缓存的验证码
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);

        //比对验证码
        if (codeInRedis == null || !codeInRedis.equals(validateCode)) {
            //验证码错误
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);

        }

        //进行微信预约，调用服务完成预约业务处理
        Result result = null;
        try {
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            result = orderMobileService.order(map);
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }

        //发送预约成功通知短信
        if (result.isFlag()) {
            String orderDate = (String) map.get("orderDate");
            try {
                SMSUtils.sendShortMessage(telephone, orderDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return result;


    }

    @RequestMapping("/findById")
    public Result findById(Integer id) throws Exception {
        Map map = null;

        map = orderMobileService.findByIdForDetail(id);
        return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,map);

    }






}
