package com.hl.travel.controller;


import com.hl.travel.constant.MessageConstant;
import com.hl.travel.model.dao.RedisDao;
import com.hl.travel.model.pojo.User;
import com.hl.travel.model.vo.Result;
import com.hl.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    RedisDao redisDao;

    /**
     * 获取当前登录用户的用户名
     * @return 用户名
     */
    @GetMapping("/getUsername")
    public Result getUsername(HttpServletRequest request) {

        String sessionId = request.getSession().getId();  // 假设从请求中获取到session ID

        String sessionKey = "spring:session:sessions:" + sessionId;

        // 准备从Redis中获取当前登录用户的用户名

        Map<Object, Object> sessionData = redisDao.getHash(sessionKey);


        SecurityContext securityContext = (SecurityContext) sessionData.get("sessionAttr:SPRING_SECURITY_CONTEXT");
        Authentication auth = securityContext.getAuthentication();



        Object myUser = (auth != null) ? auth.getPrincipal() : null;

        if (myUser instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) myUser;
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, user.getUsername());
        } else {
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }
    }


    // FeignClient
    @RequestMapping("/api/findUserByUserName")
    public User findUserByUserName(@RequestParam("username") String username) {
        return userService.findUserByUserName(username);
    }

}
