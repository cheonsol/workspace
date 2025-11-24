package com.kh.kiosk.model.vo;

import java.time.LocalDateTime;

public class Payment {
	private long paymentId;
	private long orderId;
	private long amount;
	private String status; // 'APPROVED' | 'FAILED' | 'REFUNDED'
	private String approvalCode;
	private LocalDateTime payAt;
	private LocalDateTime refundAt;

	public Payment() {
	}

	public Payment(long paymentId, long orderId, long amount, String status, String approvalCode, LocalDateTime payAt,
			LocalDateTime refundAt) {
		this.paymentId = paymentId;
		this.orderId = orderId;
		this.amount = amount;
		this.status = status;
		this.approvalCode = approvalCode;
		this.payAt = payAt;
		this.refundAt = refundAt;
	}

	public Payment(long orderId, long amount, String status, String approvalCode) {
		this.orderId = orderId;
		this.amount = amount;
		this.status = status;
		this.approvalCode = approvalCode;
		this.payAt = LocalDateTime.now();
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public LocalDateTime getPayAt() {
		return payAt;
	}

	public void setPayAt(LocalDateTime payAt) {
		this.payAt = payAt;
	}

	public LocalDateTime getRefundAt() {
		return refundAt;
	}

	public void setRefundAt(LocalDateTime refundAt) {
		this.refundAt = refundAt;
	}

	@Override
	public String toString() {
		return "Payment{paymentId=" + paymentId + ", orderId=" + orderId + ", amount=" + amount + ", status='" + status
				+ '\'' + ", approvalCode='" + approvalCode + '\'' + ", payAt=" + payAt + ", refundAt=" + refundAt + '}';
	}
}
