package com.kh.jdbc.controller;

import java.util.List;

import com.kh.jdbc.model.vo.Customer;
import com.kh.jdbc.service.CustomerService;
import com.kh.jdbc.view.MainMenu;

public class CustomerController {
	private CustomerService customerService = new CustomerService();
	
	public void selectAllCustomer() {
		List<Customer> list = customerService.selectAllCustomerList();
		
		new MainMenu().printAlllCustomerList(list);
	}
}  