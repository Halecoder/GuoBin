package com.hl.travel.model.dao;


import com.hl.travel.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.MappedJdbcTypes;

@Mapper
public interface UserDao {
    User findUserByusername(String username);
}
