package com.hl.travel.controller;


import com.hl.travel.Service.UserService;
import com.hl.travel.constant.MessageConstant;
import com.hl.travel.vo.Result;
import jdk.nashorn.internal.ir.annotations.Reference;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping("/getUsername")
    public Result getUsername() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, user.getUsername());
        } catch (Exception e) {
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }
    }
}
