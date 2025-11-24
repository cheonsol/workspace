package com.kh.jdbc.service;

import java.sql.Connection;

import static com.kh.jdbc.common.JDBCTemplate.*;
import com.kh.jdbc.model.dao.RentalDao;
import com.kh.jdbc.model.vo.Rental;

public class RentalService {

	public int rentBook(Rental r) {
		Connection conn = getConnection();
		int result = new RentalDao().rentBook(r, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}
	
	public int returnBook(Rental r) {
		Connection conn = getConnection();
		int result = new RentalDao().returnBook(r, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}
}
