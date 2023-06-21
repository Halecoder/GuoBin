package com.hl.travel.service.impl;


import com.hl.travel.model.dao.UserDao;
import com.hl.travel.model.pojo.User;
import com.hl.travel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {




    @Autowired
    private UserDao userDao;

    @Override
    public User findUserByUserName(String username) {
        return userDao.findUserByusername(username);
    }
}
