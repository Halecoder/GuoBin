package com.hl.travel.controller;


import com.hl.travel.constant.MessageConstant;
import com.hl.travel.constant.RedisMessageConstant;

import com.hl.travel.model.pojo.Order;
import com.hl.travel.model.vo.Result;
import com.hl.travel.service.OrderMobileService;
import com.hl.travel.utils.SMSUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

import java.util.Map;


/**
 * 移动端预约
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
@Tag(name = "移动端预约相关接口")
public class OrderMobileController {

    @Autowired
    private OrderMobileService orderMobileService;

    @Autowired
    private JedisPool jedisPool;



    /**
      * 旅游预约
      * @param map
      * @return
      */
    @PostMapping("/submit")
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

    /**
     * 根据id查询预约信息，包括旅游人信息、旅游套餐信息
     * @param id 订单id
     * @return
     * @throws Exception
     */
    @PostMapping("/findById")
    public Result findById(Integer id) throws Exception {
        Map map = null;

        map = orderMobileService.findByIdForDetail(id);
        return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,map);

    }






}
