package com.kh.jdbc.model.vo;

public class Member {
	private Long memberId;
	private String memberName;
	private String phone;
	
	public Member() {
		super();
	}

	public Member(Long memberId, String memberName, String phone) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.phone = phone;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "[memberId=" + memberId + ", memberName=" + memberName + ", phone=" + phone + "]";
	}
}