package com.hl.travel.service;

import java.util.List;

public interface MemberService {
    List<Integer> findMemberCountByMonth(List<String> months);
}
