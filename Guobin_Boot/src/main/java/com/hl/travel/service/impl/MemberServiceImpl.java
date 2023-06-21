package com.hl.travel.service.impl;


import com.hl.travel.model.dao.MemberDao;
import com.hl.travel.service.MemberService;
import com.hl.travel.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public List<Integer> findMemberCountByMonth(List<String> months) {
        List<Integer> memeberCountList = new ArrayList<Integer>();
        if(months!=null && months.size()>0){
            for (String month : months) {
                //String regTime = months+"-31";
                // 获取指定月份的最后一天
                String regTime =  DateUtils.getLastDayOfMonth(month);
                //  迭代过去12个月，每个月注册会员的数量，根据注册日期查询
                Integer memeberCount = memberDao.findMemberCountBeforeDate(regTime);
                memeberCountList.add(memeberCount);
            }
        }
        return memeberCountList;
    }
}
