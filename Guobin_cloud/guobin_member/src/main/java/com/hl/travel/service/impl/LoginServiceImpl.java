package com.hl.travel.service.impl;


import com.hl.travel.model.dao.MemberDao;
import com.hl.travel.model.pojo.Member;
import com.hl.travel.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private MemberDao memberDao;

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
