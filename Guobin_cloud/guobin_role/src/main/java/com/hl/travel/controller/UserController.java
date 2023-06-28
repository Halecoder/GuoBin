package com.hl.travel.controller;


import com.hl.travel.constant.MessageConstant;
import com.hl.travel.model.pojo.User;
import com.hl.travel.model.vo.Result;
import com.hl.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取当前登录用户的用户名
     * @return 用户名
     */
    @GetMapping("/getUsername")
    public Result getUsername() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object myUser = (auth != null) ? auth.getPrincipal() : null;

        if (myUser instanceof User) {
            User user = (User) myUser;
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, user.getUsername());
        } else {
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }
    }


    // FeignClient
    @RequestMapping("/api/findUserByUserName")
    public User findUserByUserName(String username) {
        return userService.findUserByUserName(username);
    }

}
