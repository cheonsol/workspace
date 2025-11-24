package com.kh.kiosk.model.vo;

import java.time.LocalDateTime;

public class PointHistory {
	private long phId;
	private long userNo;
	private Long orderId; 
	private long pointDiff;
	private LocalDateTime regDate;
	private String memo;

	public PointHistory() {
	}

	public PointHistory(long phId, long userNo, Long orderId, long pointDiff, LocalDateTime regDate, String memo) {
		this.phId = phId;
		this.userNo = userNo;
		this.orderId = orderId;
		this.pointDiff = pointDiff;
		this.regDate = regDate;
		this.memo = memo;
	}

	public PointHistory(long userNo, Long orderId, long pointDiff, String memo) {
		this.userNo = userNo;
		this.orderId = orderId;
		this.pointDiff = pointDiff;
		this.memo = memo;
		this.regDate = LocalDateTime.now();
	}

	public long getPhId() {
		return phId;
	}

	public void setPhId(long phId) {
		this.phId = phId;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public long getPointDiff() {
		return pointDiff;
	}

	public void setPointDiff(long pointDiff) {
		this.pointDiff = pointDiff;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "PointHistory{phId=" + phId + ", userNo=" + userNo + ", orderId=" + orderId + ", pointDiff=" + pointDiff
				+ ", regDate=" + regDate + ", memo='" + memo + '\'' + '}';
	}
}
