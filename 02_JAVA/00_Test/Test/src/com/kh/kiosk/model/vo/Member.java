package com.kh.kiosk.model.vo;

import java.time.LocalDateTime;

public class Member {
	private long userNo;
	private String name;
	private String phone;
	private long pointBalance;
	private LocalDateTime joinDate;

	public Member() {
	}

	public Member(long userNo, String name, String phone, long pointBalance, LocalDateTime joinDate) {
		this.userNo = userNo;
		this.name = name;
		this.phone = phone;
		this.pointBalance = pointBalance;
		this.joinDate = joinDate;
	}

	public Member(String name, String phone) { // 가입용
		this.name = name;
		this.phone = phone;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getPointBalance() {
		return pointBalance;
	}

	public void setPointBalance(long pointBalance) {
		this.pointBalance = pointBalance;
	}

	public LocalDateTime getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(LocalDateTime joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "Member{userNo=" + userNo + ", name='" + name + '\'' + ", phone='" + phone + '\'' + ", pointBalance="
				+ pointBalance + ", joinDate=" + joinDate + '}';
	}
}
