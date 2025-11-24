package com.semi.cm.model.vo;

import java.util.Objects;

/**
 * T_DEPT
 */
public class DeptVo {

	private int deptNo; //부서번호
	private String deptCode; // 부서코드
	private String deptName; // 부서명
	private int deptEmpCnt; // 부서내 사원수
	public DeptVo() {
		super();
	}
	public DeptVo(int deptNo, String deptCode, String deptName, int deptEmpCnt) {
		super();
		this.deptNo = deptNo;
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.deptEmpCnt = deptEmpCnt;
	}
	public int getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getDeptEmpCnt() {
		return deptEmpCnt;
	}
	public void setDeptEmpCnt(int deptEmpCnt) {
		this.deptEmpCnt = deptEmpCnt;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(deptNo,deptCode,deptName);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof DeptVo) {
			DeptVo d = (DeptVo) obj;
			return this.deptNo == d.deptNo && this.deptCode.equals(d.deptCode) &&this.deptName.equals(d.deptName);
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "DeptVo [deptNo=" + deptNo + ", deptCode=" + deptCode + ", deptName=" + deptName + ", deptEmpCnt="
				+ deptEmpCnt + "]";
	}
	
	
	
	
	
}
