package com.semi.cm.model.vo;

import java.time.LocalDate;

/**
 * T_DEL_EMP
 */
public class DelEmpVo extends MemberVo {

	private LocalDate quitDate; //퇴사일

	public DelEmpVo() {
		super();
	}



	public DelEmpVo(int empNo, String empId, String empName, String empDept, String empJob, LocalDate birtDate,
			LocalDate quitDate) {
		super(empNo, empId, empName, empDept, empJob, birtDate);
		this.quitDate = quitDate;
	}



	public LocalDate getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(LocalDate quitDate) {
		this.quitDate = quitDate;
	}

	@Override
	public String toString() {
		return super.toString()+", quitDate=" + quitDate+"]";
	}
	
	
	
}	
