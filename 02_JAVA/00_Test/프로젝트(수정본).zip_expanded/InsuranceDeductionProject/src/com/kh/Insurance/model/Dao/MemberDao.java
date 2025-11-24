package com.kh.Insurance.model.Dao;

import static com.kh.Insurance.common.Template.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.Insurance.model.MemberVo;

public class MemberDao {
	private Properties prop = new Properties();

	public MemberDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int insertMember(MemberVo m ,Connection conn) {
		int re = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPass());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPersonalInfo());
			pstmt.setString(5, m.getAddress());
			pstmt.setString(6,m.getPhone());
			pstmt.setInt(7, m.getDriveCareer());
			pstmt.setInt(8, m.getUserCar());
			pstmt.setInt(9,m.getCompanyId());
			pstmt.setInt(10,m.getAccidentCount());
			
			re = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return re;

	}
	
	public ArrayList<MemberVo> selectMemberList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rest = null;
		ArrayList<MemberVo> list = new ArrayList<>();
		String sql = prop.getProperty("selectMemberList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rest = pstmt.executeQuery();
			while(rest.next()) {
				MemberVo m = new MemberVo();
				m.setUserId(rest.getInt("USER_ID"));
				m.setId(rest.getString("ID"));
				m.setPass(rest.getString("USER_PASS"));
				m.setUserName(rest.getString("USER_NAME"));
				m.setPersonalInfo(rest.getString("PERSONAL_INFO"));
				m.setAddress(rest.getString("ADDRESS"));
				m.setPhone(rest.getString("PHONE"));
				m.setDriveCareer(rest.getInt("DRIVE_CAREER"));
				m.setUserCar(rest.getInt("USER_CAR"));
				m.setEnrollDate(rest.getTimestamp("ENROLL_DATE").toLocalDateTime());
				m.setAccidentCount(rest.getInt("ACCIDENT_COUNT"));
				
				
				list.add(m);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rest);
			close(pstmt);
			
	}
		return list;
	}
	
	
	public int updateJoinMemebr(MemberVo m, Connection conn) {
		
		int re = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateJoinMember");
		
		try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, m.getId());
	         pstmt.setString(2,m.getPass());
	         pstmt.setString(3,m.getAddress());
	         pstmt.setString(4, m.getPhone());
	         pstmt.setInt(5, m.getDriveCareer());
	         pstmt.setInt(6, m.getUserCar());
	         pstmt.setInt(7, m.getAccidentCount());
	         pstmt.setInt(8, m.getUserId());

	
	         
	         re = pstmt.executeUpdate();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	        close(pstmt);
	      }
	      return re;
		
		
	}
	public int deleteJoinMember(MemberVo m, Connection conn) {
		int re = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteJoinMember");
		
		try {
	    	 pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, m.getId());
	         pstmt.setString(2,m.getPass());
	         
	         
	         re = pstmt.executeUpdate();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {
	         close(pstmt);
	      }
	      return re;
	}
	public MemberVo selectMemberById(MemberVo m,Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectMemberById");
		try {
			//완성된 sql
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPass());
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				m.setUserId(rset.getInt("USER_ID"));
				m.setId(rset.getString("ID"));
				m.setPass(rset.getString("USER_PASS"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setPersonalInfo(rset.getString("PERSONAL_INFO"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setPhone(rset.getString("PHONE"));
				m.setDriveCareer(rset.getInt("DRIVE_CAREER"));
				m.setUserCar(rset.getInt("USER_CAR"));
				m.setCompanyId(rset.getInt("COMPANY_ID"));
				m.setEnrollDate(rset.getTimestamp("ENROLL_DATE").toLocalDateTime());
				m.setAccidentCount(rset.getInt("ACCIDENT_COUNT"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return m;
	}
	public MemberVo selectLogMember(MemberVo m, Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		MemberVo re = null;
		String sql = prop.getProperty("selectLogMember");
		try {
			//완성된 sql
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPass());
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				re = new MemberVo();
				re.setUserId(rset.getInt("USER_ID"));
				re.setId(rset.getString("ID"));
				re.setPass(rset.getString("USER_PASS"));
				re.setUserName(rset.getString("USER_NAME"));
				re.setPersonalInfo(rset.getString("PERSONAL_INFO"));
				re.setAddress(rset.getString("ADDRESS"));
				re.setPhone(rset.getString("PHONE"));
				re.setDriveCareer(rset.getInt("DRIVE_CAREER"));
				re.setUserCar(rset.getInt("USER_CAR"));
				re.setCompanyId(rset.getInt("COMPANY_ID"));
				re.setEnrollDate(rset.getTimestamp("ENROLL_DATE").toLocalDateTime());
				re.setAccidentCount(rset.getInt("ACCIDENT_COUNT"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return re;
	}

	
	
	

}
