package com.semi.cm.common;
/**
 * DB connection
 */

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JdbcConnecntion {

	/**
	 * connection 생성
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("userName"),prop.getProperty("password"));
			
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return conn;
	}
	/**
	 * conn 커밋
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * conn 롤백
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * conn 반납
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * stmt 반납
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * rset 반납
	 * @param rset
	 */
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
}
