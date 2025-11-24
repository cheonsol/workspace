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
import com.kh.jdbc.model.vo.Customer;

public class CustomerDao {
	private Properties prop = new Properties();

	public CustomerDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/customer-query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Customer> selectAllCustomers(Connection conn){
		//select -> ResultSet(여러개) -> List<Member>
		List<Customer> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
	
		String sql = prop.getProperty("selectAllCustomers");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Customer customer = new Customer();
				customer.setCustNo(rset.getLong("CUST_NO"));
				customer.setCustId(rset.getString("CUST_ID"));
				customer.setCustPw(rset.getString("CUST_PW"));
				customer.setCustName(rset.getString("CUST_NAME"));
				customer.setCustPhone(rset.getString("CUST_PHONE"));
				customer.setCustAddress(rset.getString("CUST_ADDRESS"));
				customer.setCustJoinDate(rset.getTimestamp("CUST_JOINDATE").toLocalDateTime());
				list.add(customer);
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
