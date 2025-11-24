package com.kh.jdbc.controller;

import java.util.List;

import com.kh.jdbc.model.vo.Member;
import com.kh.jdbc.service.MemberService;
import com.kh.jdbc.view.BookMenu;

public class MemberController {
	private MemberService memberService = new MemberService();
	
	public void selectAllMembers() {
		List<Member> list = memberService.selectAllMemberList();
		
		new BookMenu().printAllMemberList(list);
	}
}
