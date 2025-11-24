package com.kh.jdbc.service;

import static com.kh.jdbc.common.JDBCTemplate.commit;
import static com.kh.jdbc.common.JDBCTemplate.getConnection;
import static com.kh.jdbc.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.jdbc.model.dao.RentalDao;
import com.kh.jdbc.model.vo.Rental;

public class RentalService {
	public int rentDevice(Rental r) {
		Connection conn = getConnection();
		int result = new RentalDao().rentDevice(r, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}
	
	public int returnDevice(Rental r) {
		Connection conn = getConnection();
		int result = new RentalDao().returnDevice(r, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}
}
