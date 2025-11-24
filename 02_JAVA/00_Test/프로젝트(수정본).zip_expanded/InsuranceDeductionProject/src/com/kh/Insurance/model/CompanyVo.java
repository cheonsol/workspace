package com.kh.Insurance.model;

public class CompanyVo {
	private int companyId;
	private String companyName;
	private String companyPhone;
	private int deductionFee;
	
	public CompanyVo() {
		super();
	}
	public CompanyVo(int companyId, String companyName, String companyPhone, int deductionFee) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyPhone = companyPhone;
		this.deductionFee = deductionFee;
	}
	public CompanyVo(String companyName, String companyPhone, int deductionFee) {
		super();
		this.companyName = companyName;
		this.companyPhone = companyPhone;
		this.deductionFee = deductionFee;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public int getDeductionFee() {
		return deductionFee;
	}
	public void setDeductionFee(int deductionFee) {
		this.deductionFee = deductionFee;
	}
	@Override
	public String toString() {
		return "회사명 : " + companyName +
		           ", 회사 전화번호 : " + companyPhone +
		           ", 회사별 기본 공제료 : " + deductionFee + "원";

	}
	

}
