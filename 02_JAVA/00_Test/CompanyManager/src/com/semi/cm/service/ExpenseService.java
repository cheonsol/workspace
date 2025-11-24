package com.semi.cm.service;


import static com.semi.cm.common.JdbcConnecntion.*;

import java.sql.Connection;

import com.semi.cm.model.dao.ExpenseDao;
import com.semi.cm.model.vo.BuyExpManVo;

public class ExpenseService {

	private ExpenseDao ed;

	public ExpenseService() {
		super();
		this.ed = new ExpenseDao();
	}
	
	/**
	 * 자금 조회
	 * @return
	 */
	public int selectExp() {
		Connection conn = getConnection();
		int result = ed.selectExp(conn);
		
		close(conn);
		
		return result;
	}
	
	public int insertExp(BuyExpManVo b) {
		Connection conn = getConnection();
		int result = ed.insertExp(b,conn);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
}
