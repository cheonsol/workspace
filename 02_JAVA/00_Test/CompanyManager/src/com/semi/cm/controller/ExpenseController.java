package com.semi.cm.controller;

import com.semi.cm.model.vo.BuyExpManVo;
import com.semi.cm.service.ExpenseService;
import com.semi.cm.view.CompanyMenu;

public class ExpenseController {

	private ExpenseService es = new ExpenseService();

	public ExpenseController() {
		super();
	}
	
	/**
	 * 자금 조회
	 */
	public void selectExp() {
		int result = es.selectExp();
		
		if (result > 0) {
			new CompanyMenu().displaySuccess(result +"원 보유중");
		} else {
			new CompanyMenu().displayFail("자금이 없습니다.");
		}
		
	}
	
	/**
	 * 입금
	 * @param b
	 */
	public void insertExp(BuyExpManVo b) {
		int result = es.insertExp(b);
		
		
		if (result > 0) {
			new CompanyMenu().displaySuccess("입금하였습니다.");
		} else {
			new CompanyMenu().displayFail("입금에 실패하셨습니다.");
		}
		
		
	}
	
}
