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
import com.kh.Insurance.model.DriveWeight;

public class DriveWeightDao {
	private Properties prop = new Properties();

	public DriveWeightDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int insertDriveWeight(DriveWeight d ,Connection conn) {
		int re = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertDriveWeight");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, d.getDwId());
			pstmt.setInt(2, d.getCompanyId());
			pstmt.setInt(3, d.getCarId());
			pstmt.setInt(4, d.getExperienceMin());
			pstmt.setInt(5, d.getExperienceMax());
			pstmt.setDouble(6, d.getDwWeightScore());
			
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
	
	
	public double selectDriveWeight(int companyId, int userCar, int driveCareer, Connection conn) {
		DriveWeight d = new DriveWeight();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectDriveWeight");
		
		try {
			//완성된 sql
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, companyId);
			pstmt.setInt(2, userCar);
			pstmt.setInt(3, driveCareer);
			pstmt.setInt(4, driveCareer);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				d.setDwWeightScore(rset.getDouble("DW_WEIGHT_SCORE"));
	
				
				
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return d.getDwWeightScore();
	}
	
	

}
