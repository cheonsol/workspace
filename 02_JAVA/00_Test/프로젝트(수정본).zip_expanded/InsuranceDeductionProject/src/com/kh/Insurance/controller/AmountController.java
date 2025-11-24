package com.kh.Insurance.controller;

import com.kh.Insurance.model.Amount;
import com.kh.Insurance.model.MemberVo;
import com.kh.Insurance.service.AmountService;
import com.kh.Insurance.view.MainMenu;

public class AmountController {
	private AmountService as = new AmountService();

	public AmountController() {
		super();
	}
	public void insertAmount(int userId,int payFee, int inputFee,int restFee, int count, String yn) {
		Amount a = new Amount();
		a.setUserId(userId);
		a.setPayFee(payFee);
		a.setInputFee(inputFee);
		a.setRestFee(restFee);
		a.setFailCount(count);
		a.setStatus(yn);
		int result =as.insertAmount(a);
		if(result>0) {
			new MainMenu().successDisplay("보험료 조회가");
		}else {
			new MainMenu().failureDisplay("보험료 조회가");

		}
		
		
	}
	public int inputFee(MemberVo m, int fee) {
		Amount a = new Amount();
		a.setUserId(m.getUserId());
		a.setInputFee(fee);
		int result =as.inputFee(a);
		if(result>0) {
			new MainMenu().successDisplay("입금이");
		}else {
			new MainMenu().failureDisplay("입금이");

		}
		return result;
	}
	public void insertCount(MemberVo m, int count) {
		Amount a = new Amount();
		a.setUserId(m.getUserId());
		a.setFailCount(count);
		int result =as.insertCount(a);
		if(result>0) {
			new MainMenu().successDisplay("실패횟수 증가가");
		}else {
			new MainMenu().failureDisplay("실패횟수 증가가");

		}
	}
	public void feeCalculation(MemberVo m) {
		Amount a = new Amount();
		a.setUserId(m.getUserId());
		as.feeCalculation(a);

	}
	public int searchRestFee(MemberVo m) {
		Amount a = new Amount();
		a.setUserId(m.getUserId());
		int fee = as.searchRestFee(a);
		return fee;
	}
	public int selectCount(MemberVo m) {
		Amount a = new Amount();
		a.setUserId(m.getUserId());
		int count = as.selectCount(a);
		return count;
	}
	public void updateStatus(MemberVo m) {
		Amount a = new Amount();
		a.setUserId(m.getUserId());
		int result = as.updateStatus(a);
		if(result>0) {
			new MainMenu().successDisplay("납입 상태 갱신이");
		}else {
			new MainMenu().failureDisplay("납입 상태 갱신이");

		}
	}
	public boolean existAmount(int id) {
		Amount a = new Amount();
		a.setUserId(id);
		boolean e = as.existAmount(a);
		return e;
	}
	public void fixAmount(MemberVo m, int fixFee) {
		Amount a = new Amount();
		a.setUserId(m.getUserId());
		a.setInputFee(fixFee);
		as.fixAmount(a);
	}
	public int selectFee(MemberVo m) {
		Amount a = new Amount();
		a.setUserId(m.getUserId());
		int fee = as.selectFee(a);
		return fee;
	}

}
