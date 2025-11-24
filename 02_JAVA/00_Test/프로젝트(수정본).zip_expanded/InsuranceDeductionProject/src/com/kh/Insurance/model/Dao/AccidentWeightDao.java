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

import com.kh.Insurance.model.AccidentWeight;
import com.kh.Insurance.model.DriveWeight;


public class AccidentWeightDao {
	private Properties prop = new Properties();

	public AccidentWeightDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int insertAccidentWeight(AccidentWeight w ,Connection conn) {
		int re = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAccidentWeight");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, w.getAwId());
			pstmt.setInt(2, w.getCompanyId());
			pstmt.setInt(3, w.getCarId());
			pstmt.setInt(4, w.getAccidentMin());
			pstmt.setInt(5, w.getAccidentMax());
			pstmt.setDouble(6, w.getAwWeightScore());
			
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
	
	public double selectAccidentWeight(int companyId, int userCar, int accdientCount, Connection conn) {
		AccidentWeight a = new AccidentWeight();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAccidentWeight");
		
		try {
			//완성된 sql
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, companyId);
			pstmt.setInt(2, userCar);
			pstmt.setInt(3, accdientCount);
			pstmt.setInt(4, accdientCount);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				a.setAwWeightScore(rset.getDouble("AW_WEIGHT_SCORE"));
				
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return a.getAwWeightScore();
	}


}
