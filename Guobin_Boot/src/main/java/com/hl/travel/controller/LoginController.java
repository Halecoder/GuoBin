package com.hl.travel.controller;



import com.hl.travel.constant.MessageConstant;
import com.hl.travel.constant.RedisMessageConstant;

import com.hl.travel.model.vo.Result;
import com.hl.travel.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {


    @Autowired(required = false)
    private JedisPool jedisPool;

    @Autowired
    private LoginService loginService;

    @RequestMapping("/check")
    public Result login(HttpServletResponse response, @RequestBody Map map)  {

        //获取用户输入的验证码
        String validateCode = (String) map.get("validateCode");
        //获取用户输入的手机号
        String telephone = (String) map.get("telephone");
        //从redis中获取缓存的验证码
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_LOGIN);

        //比对验证码
        if (codeInRedis == null || !codeInRedis.equals(validateCode)) {
            //验证码错误
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);

        }

        //3：:登录成功
        //写入Cookie，跟踪用户
        Cookie cookie = new Cookie("login_member_telephone",telephone);
        cookie.setPath("/");//路径
        cookie.setMaxAge(60*60*24*30);//有效期30天（单位秒）
        response.addCookie(cookie);

       Boolean isLogin =  loginService.check(telephone);

        if(isLogin){
            return new Result(true, MessageConstant.LOGIN_SUCCESS);
        }
        else {
            return new Result(true, MessageConstant.REGIS_SUCCESS);
        }


    }

}
