package com.kh.jdbc.model.vo;

import java.time.LocalDateTime;

public class Rental {
	private Long renNo;
	private Long devNo;
	private Long custNo;
	private LocalDateTime renDate;
	private String status;

	public Rental() {
		super();
	}

	public Rental(Long renNo, Long devNo, Long custNo, LocalDateTime renDate, String status) {
		super();
		this.renNo = renNo;
		this.devNo = devNo;
		this.custNo = custNo;
		this.renDate = renDate;
		this.status = status;
	}

	public Long getRenNo() {
		return renNo;
	}

	public void setRenNo(Long renNo) {
		this.renNo = renNo;
	}

	public Long getDevNo() {
		return devNo;
	}

	public void setDevNo(Long devNo) {
		this.devNo = devNo;
	}

	public Long getCustNo() {
		return custNo;
	}

	public void setCustNo(Long custNo) {
		this.custNo = custNo;
	}

	public LocalDateTime getRenDate() {
		return renDate;
	}

	public void setRenDate(LocalDateTime renDate) {
		this.renDate = renDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Rental [renNo=" + renNo + ", devNo=" + devNo + ", custNo=" + custNo + ", renDate=" + renDate
				+ ", status=" + status + "]";
	}

}