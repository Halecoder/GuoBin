package com.hl.travel.Service;

import java.util.List;

public interface MemberService {
    List<Integer> findMemberCountByMonth(List<String> months);
}
