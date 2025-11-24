package com.semi.cm.model.vo;

import java.util.Objects;

/**
 * T_JOB
 */
public class JobVo {

	private int jobNo; // 직급 번호
	private String jobCode; // 직급 코드
	private String jobName; // 직급 명
	private int jobEmpCnt; // 사내 직급 인원수
	public JobVo() {
		super();
	}
	public JobVo(int jobNo, String jobCode, String jobName, int jobEmpCnt) {
		super();
		this.jobNo = jobNo;
		this.jobCode = jobCode;
		this.jobName = jobName;
		this.jobEmpCnt = jobEmpCnt;
	}
	public int getJobNo() {
		return jobNo;
	}
	public void setJobNo(int jobNo) {
		this.jobNo = jobNo;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public int getJobEmpCnt() {
		return jobEmpCnt;
	}
	public void setJobEmpCnt(int jobEmpCnt) {
		this.jobEmpCnt = jobEmpCnt;
	}
	@Override
	public String toString() {
		return "JobVo [jobNo=" + jobNo + ", jobCode=" + jobCode + ", jobName=" + jobName + ", jobEmpCnt=" + jobEmpCnt
				+ "]";
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(jobCode,jobNo);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof JobVo) {
			JobVo j = (JobVo)obj;
			return this.jobNo == j.jobNo && this.jobCode.equals(j.jobCode);
		}
		return super.equals(obj);
	}
	
	
	
}
