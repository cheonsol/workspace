package com.kh.Insurance.controller;

import java.lang.reflect.Member;
import java.util.List;

import com.kh.Insurance.model.CompanyVo;
import com.kh.Insurance.service.CompanyService;

public class CompanyController {
	private CompanyService ms = new CompanyService();

	public CompanyController() {
		super();
	}
	public void insertCompany(String name, String phone, int fee) {
		CompanyVo c = new CompanyVo();
		c.setCompanyName(name);
		c.setCompanyPhone(phone);
		c.setDeductionFee(fee);
		
		ms.insertCompany(c);
		
	}
	public void selectCompanyList() {
		List<CompanyVo> list = ms.selectCompanyList();
		if(list.isEmpty()) {
			System.out.println("회사 목록이 존재하지 않습니다.");
		}else {
			System.out.println(list);
		}
	}
	public int searchCompanyIdByName(String name) {
		CompanyVo c = new CompanyVo();
		c.setCompanyName(name);
		CompanyVo co = ms.searchCompanyIdByName(c);
		
		return co.getCompanyId();
	}

}
