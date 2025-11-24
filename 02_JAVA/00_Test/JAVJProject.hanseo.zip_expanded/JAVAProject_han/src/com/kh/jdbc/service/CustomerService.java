package com.kh.jdbc.service;

import static com.kh.jdbc.common.JDBCTemplate.close;
import static com.kh.jdbc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kh.jdbc.model.dao.CustomerDao;
import com.kh.jdbc.model.vo.Customer;

public class CustomerService {

	public List<Customer> selectAllCustomerList() {
		Connection conn = getConnection();

		List<Customer> list = new CustomerDao().selectAllCustomers(conn);

		close(conn);
		return list;
	}
}
 