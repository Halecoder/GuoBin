package com.hl.travel.dao;



import com.hl.travel.entity.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> findRolesByUserId(Integer userId);
}
