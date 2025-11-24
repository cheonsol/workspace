package com.kh.kiosk.controller;

import com.kh.kiosk.model.vo.Member;
import com.kh.kiosk.service.MemberService;

public class MemberController {
    private MemberService memberService = new MemberService();
    
    public Member findByPhone(String phone) {
        return memberService.selectByPhone(phone);
    }
    
    public Member register(String name, String phone) {
        return memberService.insertMember(name, phone);
    }
}