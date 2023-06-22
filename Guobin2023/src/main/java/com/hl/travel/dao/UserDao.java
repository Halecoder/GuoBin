package com.hl.travel.dao;


import com.hl.travel.entity.User;

public interface UserDao {
    User findUserByusername(String username);
}
