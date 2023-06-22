package com.hl.travel.dao;
import com.hl.travel.entity.Permission;

import java.util.Set;

public interface PermissionDao {
    Set<Permission> findPermissionsByRoleId(Integer roleId);
}
