package com.hl.travel.controller;


import com.hl.travel.constant.MessageConstant;
import com.hl.travel.constant.RedisMessageConstant;

import com.hl.travel.model.vo.Result;
import com.hl.travel.utils.SMSUtils;
import com.hl.travel.utils.ValidateCodeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController

@RequestMapping("/validateCode")
@CrossOrigin
public class ValidateCodeController {

    @Autowired(required = false)
    private JedisPool jedisPool;

    //预约时发送手机验证码
    @RequestMapping("/sendForOrder")
    public Result send4Order(String telephone)  {

        //生成验证码
        String code = ValidateCodeUtils.generateValidateCode(4).toString();

        //发送验证码
        try {
            SMSUtils.sendShortMessage(telephone, code);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }

        System.out.println("发送的手机验证码为：" + code);
        //将生成的验证码缓存到redis
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER, 5 * 60, code.toString());
        //验证码发送成功
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);


    }



    @RequestMapping("/sendForLogin")
    public Result send4Login(String telephone)  {

        //生成验证码
        String code = ValidateCodeUtils.generateValidateCode(4).toString();

        //发送验证码
        try {
            SMSUtils.sendShortMessage(telephone, code);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }

        System.out.println("发送的手机验证码为：" + code);
        //将生成的验证码缓存到redis
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_LOGIN, 5 * 60, code.toString());
        //验证码发送成功
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS);


    }

}
