package com.hl.travel.dao;

import com.hl.travel.entity.Member;

public interface MemberDao {

    Member findByTelephone(String telephone);

    void add(Member member);
}
