package com.project.lifegame.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static com.project.lifegame.common.LifegameTemplate.*;

import com.project.lifegame.model.vo.User;

public class UserDao {
	private Properties prop = new Properties();

	public UserDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean loginUser(User u, Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		boolean result=false;
		String sql = prop.getProperty("loginUser");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUserId());
			pstmt.setString(2, u.getUserPw());
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}
	
	public int signUpUser(User u, Connection conn) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("signUpUser");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getUserId());
			pstmt.setString(2, u.getUserPw());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(conn);
		}
		return result;
	}
	
}
