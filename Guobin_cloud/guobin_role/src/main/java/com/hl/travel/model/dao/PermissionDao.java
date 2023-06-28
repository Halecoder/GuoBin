package com.hl.travel.model.dao;

import com.hl.travel.model.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface PermissionDao {
    Set<Permission> findPermissionsByRoleId(Integer roleId);
}
