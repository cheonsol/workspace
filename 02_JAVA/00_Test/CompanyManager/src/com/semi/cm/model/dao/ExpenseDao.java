package com.semi.cm.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.semi.cm.common.JdbcConnecntion;
import com.semi.cm.model.vo.BuyExpManVo;

public class ExpenseDao {

	private Properties prop = new Properties();

	public ExpenseDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/expense.xml"));		
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	/**
	 * 자금 조회
	 * @return
	 */
	public int selectExp(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectExp");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			rset.next();
			
			result = rset.getInt("EXPENSE");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(rset);
			JdbcConnecntion.close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 입금
	 * @param b
	 * @param conn
	 * @return
	 */
	public int insertExp(BuyExpManVo b, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertExp");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getDeposit());
			pstmt.setInt(2, b.getDeposit());
			result =pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		
		return result;
	}
	
}
