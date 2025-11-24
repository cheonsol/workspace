package com.semi.cm.model.vo;

import java.time.LocalDate;

/**
 * T_EMP
 */
public class EmpVo extends MemberVo{

	private int salary; // 연봉
	private LocalDate hireDate; // 입사일
	private String password; // 비밀번호
	public EmpVo() {
		super();
	}
	public EmpVo(int empNo, String empId, String empName, String empDept, String empJob, LocalDate birtDate, int salary,
			LocalDate hireDate, String password) {
		super(empNo, empId, empName, empDept, empJob, birtDate);
		this.salary = salary;
		this.hireDate = hireDate;
		this.password = password;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public LocalDate getHireDate() {
		return hireDate;
	}
	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return super.toString()+", salary=" + salary + ", hireDate=" + hireDate + ", password=" + password + "]";
	}
	
	
	
	
	
}
