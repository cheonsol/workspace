package com.kh.jdbc.model.dao;

import static com.kh.jdbc.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.jdbc.model.vo.Rental;

public class RentalDao {
	private Properties prop = new Properties();
	
	public RentalDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/rental-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int rentBook(Rental r, Connection conn) {
		//insert -> int(처리된 행 수) -> 1/0
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertRental");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setLong(1, r.getMemberId());
			pstmt.setLong(2, r.getBookId());

			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int returnBook(Rental r, Connection conn) {
		//update -> int(처리된 행 수) -> 1/0
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateReturnDate");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setLong(1, r.getMemberId());
			pstmt.setLong(2, r.getBookId());

			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
