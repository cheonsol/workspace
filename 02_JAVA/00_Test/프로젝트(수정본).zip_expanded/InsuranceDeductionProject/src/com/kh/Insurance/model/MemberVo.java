package com.kh.Insurance.model;

import java.time.LocalDateTime;

public class MemberVo {
	private int userId;
	private String id;
	private String pass;
	private String userName;
	private String personalInfo;
	private String address;
	private String phone;
	private int driveCareer;
	private int userCar;
	private int companyId;
	private LocalDateTime enrollDate;
	private int accidentCount;

	
	public MemberVo() {
		super();
	}

	public MemberVo(int userId, String id, String pass, String userName, String personalInfo, String address, String phone, int driveCareer,
			int userCar, int companyId,LocalDateTime enrollDate, int accidentCount) {
		super();
		this.userId = userId;
		this.id = id;
		this.pass = pass;
		this.userName = userName;
		this.personalInfo = personalInfo;
		this.address = address;
		this.phone = phone;
		this.driveCareer = driveCareer;
		this.userCar = userCar;
		this.enrollDate = enrollDate;
		this.accidentCount = accidentCount;

	}

	public MemberVo(String id, String pass, String userName, String personalInfo, String address, String phone, int driveCareer, int userCar,int companyId,
			int accidentCount) {
		super();
		this.id = id;
		this.pass = pass;
		this.userName = userName;
		this.personalInfo = personalInfo;
		this.address = address;
		this.phone = phone;
		this.driveCareer = driveCareer;
		this.userCar = userCar;
		this.companyId=companyId;
		this.accidentCount = accidentCount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(String personalInfo) {
		this.personalInfo = personalInfo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getDriveCareer() {
		return driveCareer;
	}

	public void setDriveCareer(int driveCareer) {
		this.driveCareer = driveCareer;
	}

	public int getUserCar() {
		return userCar;
	}

	public void setUserCar(int userCar) {
		this.userCar = userCar;
	}
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public LocalDateTime getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(LocalDateTime enrollDate) {
		this.enrollDate = enrollDate;
	}

	public int getAccidentCount() {
		return accidentCount;
	}

	public void setAccidentCount(int accidentCount) {
		this.accidentCount = accidentCount;
	}



	@Override
	public String toString() {
		return "가입자명 : " + userName + ", 주민등록번호 : " + personalInfo + ", 주소 : "
				+ address + ", 전화번호 : " + phone + ", 운전경력 " + driveCareer + "년"+", 보유 차량 : " + userCar
				+ ", 가입날짜 : " + enrollDate + ", 사고 횟수 : " + accidentCount + 
				 "번";
	
	
	}

}
