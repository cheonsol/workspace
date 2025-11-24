package com.kh.project.vo;

public class Member {
	private String userId;
	private String userName;
	private int userAge;
	
	public Member() {
		super();
	}
	
	public Member(String userId) {
		super();
		this.userId = userId;
	}

	public Member(String userId, String userName, int userAge) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userAge = userAge;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	@Override
	public String toString() {
		return "[아이디 : " + userId + ", 이름 : " + userName + ", 나이 : " + userAge + "]";
	}
	
	
}

	