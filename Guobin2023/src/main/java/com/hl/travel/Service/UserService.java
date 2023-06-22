package com.hl.travel.Service;

import com.hl.travel.entity.User;

public interface UserService {
    User findUserByUserName(String username);
}
