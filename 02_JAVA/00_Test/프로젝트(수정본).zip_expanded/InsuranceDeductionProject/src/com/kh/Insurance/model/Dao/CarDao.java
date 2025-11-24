package com.kh.Insurance.model.Dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.Insurance.model.Car;


public class CarDao {
	private Properties prop = new Properties();

	public CarDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int insertCar(Car c ,Connection conn) {
		int re = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertCar");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getCarId());
			pstmt.setString(2, c.getCarName());

			
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

}
