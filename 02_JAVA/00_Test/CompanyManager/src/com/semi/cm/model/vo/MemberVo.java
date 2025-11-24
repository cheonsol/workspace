package com.semi.cm.model.vo;

import java.time.LocalDate;
import java.util.Objects;

/**
 * T_EMP
 * T_DEL_EMP
 * 
 */
public class MemberVo {

	private int empNo; // 사원 번호
	private String empId; // 사원 아이디
	private String empName; // 사원명
	private String empDept; // 부서
	private String empJob; // 직급
	private LocalDate birtDate; // 생일
	public MemberVo() {
		super();
	}
	public MemberVo(int empNo, String empId,String empName, String empDept, String empJob, LocalDate birtDate) {
		super();
		this.empNo = empNo;
		this.empId = empId;
		this.empName = empName;
		this.empDept = empDept;
		this.empJob = empJob;
		this.birtDate = birtDate;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpDept() {
		return empDept;
	}
	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}
	public String getEmpJob() {
		return empJob;
	}
	public void setEmpJob(String empJob) {
		this.empJob = empJob;
	}

	public LocalDate getBirtDate() {
		return birtDate;
	}
	public void setBirtDate(LocalDate birtDate) {
		this.birtDate = birtDate;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(empNo,empId);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof MemberVo) {
			MemberVo e = (MemberVo)obj;
			return this.empNo == e.empNo && this.empId.equals(e.empId);
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "MemberVo [empNo=" + empNo + ", empId=" + empId + ", empName=" + empName + ", empDept=" + empDept
				+ ", empJob=" + empJob + ", birtDate=" + birtDate;
	}


	
	
	
	
}
