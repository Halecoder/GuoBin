package com.hl.travel.model.dao;





import com.hl.travel.model.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface RoleDao {
    Set<Role> findRolesByUserId(Integer userId);
}
