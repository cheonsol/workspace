package com.kh.Insurance.service;
import static com.kh.Insurance.common.Template.close;
import static com.kh.Insurance.common.Template.commit;
import static com.kh.Insurance.common.Template.getConnection;
import static com.kh.Insurance.common.Template.rollback;

import java.sql.Connection;

import com.kh.Insurance.controller.AmountController;
import com.kh.Insurance.model.Amount;
import com.kh.Insurance.model.Dao.AmountDao;

public class AmountService {
	private AmountDao ad = new AmountDao();


	public AmountService() {
		super();
	}
	
	public int insertAmount(Amount a) {
		Connection conn = getConnection();
		int result = ad.insertAmount(a, conn);
			
			if(result>0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			close(conn);
			return result;
	}
	public int searchRestFee(Amount a) {
		Connection conn = getConnection();
		
		int re = ad.searchRestFee(a,conn);
		close(conn);
		return re;
	}
	public int inputFee(Amount a) {
		Connection conn = getConnection();
		int result = ad.inputFee(a, conn);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int feeCalculation(Amount a) {
		Connection conn = getConnection();
		int pay = ad.selectPayFee(a, conn); // 내야할 금액
		int input = ad.selectInputFee(a, conn); // 총 낸 금액
		
		int caPay = pay-input;
		if(caPay<0) { 
			caPay=0;
		}
		int re = ad.updateRestFee(a,caPay, conn);
		if(re>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return caPay;
	}
	public int insertCount(Amount a) {
		Connection conn = getConnection();
		int result = ad.insertCount(a, conn);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int selectCount(Amount a) {
		Connection conn = getConnection();
		
		int re = ad.selectCount(a,conn);
		close(conn);
		return re;
	}
	public int updateStatus(Amount a) {
		Connection conn = getConnection();
		
		int re = ad.updateStatus(a,conn);

		if(re>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return re;

	}
	public boolean existAmount(Amount a) {
		Connection conn = getConnection();
		
		boolean e = ad.existAmount(a,conn);
		close(conn);
		return e;
	}
	public void fixAmount(Amount a) {
		Connection conn = getConnection();
		int re = ad.fixAmount(a,conn);

		if(re>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
	}
	public int selectFee(Amount a) {
        Connection conn = getConnection();
		
		int re = ad.selectInputFee(a, conn);
		close(conn);
		return re;
	}
	
	
}

