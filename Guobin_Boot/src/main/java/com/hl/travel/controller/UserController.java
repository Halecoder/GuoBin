//package com.hl.travel.controller;
//
//
//import com.hl.travel.constant.MessageConstant;
//import com.hl.travel.model.vo.Result;
//import com.hl.travel.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/user")
//@CrossOrigin
//
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @RequestMapping("/getUsername")
//    public Result getUsername() {
//        try {
//            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS, user.getUsername());
//        } catch (Exception e) {
//            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
//        }
//    }
//}
