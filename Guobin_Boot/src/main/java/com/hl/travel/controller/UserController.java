package com.hl.travel.controller;


import com.hl.travel.constant.MessageConstant;
import com.hl.travel.model.vo.Result;
import com.hl.travel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUsername")
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

}
