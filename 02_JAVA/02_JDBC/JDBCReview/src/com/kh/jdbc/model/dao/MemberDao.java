package com.kh.jdbc.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.jdbc.common.JDBCTemplate;
import com.kh.jdbc.model.vo.Member;

public class MemberDao {
	private Properties prop = new Properties();
	
	public MemberDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/member-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Member> selectAllMembers(Connection conn){
		//select -> ResultSet(여러개) -> List<Member>
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
	
		String sql = prop.getProperty("selectAllMembers");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member member = new Member();
				member.setMemberId(rset.getLong("MEMBER_ID"));
				member.setMemberName(rset.getString("MEMBER_NAME"));
				member.setPhone(rset.getString("PHONE"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
}
