package com.kh.jdbc.model.vo;

import java.time.LocalDateTime;

public class Customer {
	private Long custNo;
	private String custId;
	private String custPw;
	private String custName;
	private String custPhone;
	private String custAddress;
	private LocalDateTime custJoinDate;

	public Customer() {
		super();
	}

	public Customer(Long custNo, String custId, String custPw, String custName, String custPhone, String custAddress,
			LocalDateTime custJoinDate) {
		super();
		this.custNo = custNo;
		this.custId = custId;
		this.custPw = custPw;
		this.custName = custName;
		this.custPhone = custPhone;
		this.custAddress = custAddress;
		this.custJoinDate = custJoinDate;
	}

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustPw() {
		return custPw;
	}

	public void setCustPw(String custPw) {
		this.custPw = custPw;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public LocalDateTime getCustJoinDate() {
		return custJoinDate;
	}

	public void setCustJoinDate(LocalDateTime custJoinDate) {
		this.custJoinDate = custJoinDate;
	}

	@Override
	public String toString() {
		return "Customer [custNo=" + custNo + ", custId=" + custId + ", custPw=" + custPw + ", custName=" + custName
				+ ", custPhone=" + custPhone + ", custAddress=" + custAddress + ", custJoinDate=" + custJoinDate + "]";
	}

}