package com.hl.travel.controller;


import com.hl.travel.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/api/findMemberCountByMonth")
    public List<Integer> findMemberCountByMonth(List<String> months){
        return memberService.findMemberCountByMonth(months);
    }

}
