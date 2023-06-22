package com.hl.travel.Service.impl;

import com.hl.travel.Service.UserService;
import com.hl.travel.dao.UserDao;
import com.hl.travel.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {



    private final UserDao userDao;

    @Override
    public User findUserByUserName(String username) {
        return userDao.findUserByusername(username);
    }
}
