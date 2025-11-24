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

import com.kh.Insurance.model.CompanyVo;
import com.kh.Insurance.model.MemberVo;


public class CompanyDao {
	private Properties prop = new Properties();

	public CompanyDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int insertCompany(CompanyVo c ,Connection conn) {
		int re = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertCompany");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getCompanyName());
			pstmt.setString(2, c.getCompanyPhone());
			pstmt.setInt(3, c.getDeductionFee());
			
			re = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return re;

	}
	public ArrayList<CompanyVo> selectCompanyList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rest = null;
		ArrayList<CompanyVo> list = new ArrayList<>();
		String sql = prop.getProperty("selectCompanyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rest = pstmt.executeQuery();
			while(rest.next()) {
				CompanyVo c = new CompanyVo();
				c.setCompanyId(rest.getInt("COMPANY_ID"));
				c.setCompanyName(rest.getString("COMPANY_NAME"));
				c.setCompanyPhone(rest.getString("COMPANY_PHONE"));
				c.setDeductionFee(rest.getInt("DEDUCTION_FEE"));
				
				
				
				list.add(c);	
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
	
	
	public CompanyVo selectCompanyById(int companyId, Connection conn) {
		CompanyVo c = new CompanyVo();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectCompanyById");
		
		try {
			//완성된 sql
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, companyId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				c.setCompanyId(rset.getInt("COMPANY_ID"));
				c.setCompanyName(rset.getString("COMPANY_NAME"));
				c.setCompanyPhone(rset.getString("COMPANY_PHONE"));
				c.setDeductionFee(rset.getInt("DEDUCTION_FEE"));
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return c;
	}
	public CompanyVo searchCompanyIdByName(CompanyVo c, Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectCompanyIdByName");
		try {
			//완성된 sql
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getCompanyName());
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				c.setCompanyId(rset.getInt("COMPANY_ID"));
				c.setCompanyName(rset.getString("COMPANY_NAME"));
				c.setCompanyPhone(rset.getString("COMPANY_PHONE"));
				c.setDeductionFee(rset.getInt("DEDUCTION_FEE"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return c;
	}
}
