package com.hl.travel.dao;

import com.hl.travel.entity.Member;

import java.util.List;
import java.util.Map;

public interface MemberDao {

    Member findByTelephone(String telephone);

    void add(Member member);

    Integer findMemberCountBeforeDate(String regTime);

//    List<Map<String, Object>> findSetmealCount();
    int getTodayNewMember(String date);
    int getTotalMember();
    int getThisWeekAndMonthNewMember(String date);
}
