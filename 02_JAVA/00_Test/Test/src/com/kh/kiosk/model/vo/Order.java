package com.kh.kiosk.model.vo;

import java.time.LocalDateTime;

public class Order {
	private long orderId;
	private long userNo;
	private LocalDateTime orderDate;
	private String status; // 'PLACED' | 'CANCELED'
	private long totalAmount;

	public Order() {
	}

	public Order(long orderId, long userNo, LocalDateTime orderDate, String status, long totalAmount) {
		this.orderId = orderId;
		this.userNo = userNo;
		this.orderDate = orderDate;
		this.status = status;
		this.totalAmount = totalAmount;
	}

	public Order(long userNo, long totalAmount) {
		this.userNo = userNo;
		this.totalAmount = totalAmount;
		this.status = "PLACED";
		this.orderDate = LocalDateTime.now();
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "Order{orderId=" + orderId + ", userNo=" + userNo + ", orderDate=" + orderDate + ", status='" + status
				+ '\'' + ", totalAmount=" + totalAmount + '}';
	}
}
