package com.hl.travel.model.dao;


import com.hl.travel.model.pojo.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    Member findByTelephone(String telephone);

    void add(Member member);

    Integer findMemberCountBeforeDate(String regTime);

//    List<Map<String, Object>> findSetmealCount();
    int getTodayNewMember(String date);
    int getTotalMember();
    int getThisWeekAndMonthNewMember(String date);
}
