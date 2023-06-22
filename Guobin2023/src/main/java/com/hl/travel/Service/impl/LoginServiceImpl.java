package com.hl.travel.Service.impl;

import com.hl.travel.Service.LoginService;
import com.hl.travel.constant.MessageConstant;
import com.hl.travel.dao.MemberDao;
import com.hl.travel.entity.Member;
import com.hl.travel.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberDao memberDao;

    @Override
    public Boolean check(String telephone) {
        Member memberTemp = new Member();
      memberTemp =  memberDao.findByTelephone(telephone);
      //检查会员表是否存在
      if (memberTemp == null){
          Member member = new Member();
          //用户名
          member.setName("用户"+telephone);
          member.setPhoneNumber(telephone);
          member.setRegTime(new Date()); // 注册时间，当前时间
          memberDao.add(member);
          return false;
      }
      else {
          //存在直接过
          return true;
      }

    }

}
