package com.hl.travel.service;


import com.hl.travel.model.pojo.User;

public interface UserService {
    User findUserByUserName(String username);
}
