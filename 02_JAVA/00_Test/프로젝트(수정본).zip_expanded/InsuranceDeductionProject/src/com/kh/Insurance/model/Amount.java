package com.kh.Insurance.model;

public class Amount {
	private int amountId;
	private int userId;
	private int payFee;
	private int inputFee;
	private int restFee;
	private int failCount;
	private String status;
	
	public Amount() {
		super();
	}

	public Amount(int amountId, int userId, int payFee,int inputFee, int restFee, int failCount, String status) {
		super();
		this.amountId = amountId;
		this.userId = userId;
		this.payFee = payFee;
		this.inputFee = inputFee;
		this.restFee = restFee;
		this.failCount = failCount;
		this.status = status;
	}

	public Amount(int payFee,int inputFee, int restFee, int failCount, String status) {
		super();
		this.payFee = payFee;
		this.inputFee = inputFee;
		this.restFee = restFee;
		this.failCount = failCount;
		this.status = status;
	}

	public int getAmountId() {
		return amountId;
	}

	public void setAmountId(int amountId) {
		this.amountId = amountId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPayFee() {
		return payFee;
	}

	public void setPayFee(int payFee) {
		this.payFee = payFee;
	}
	public int getInputFee() {
		return inputFee;
	}

	public void setInputFee(int inputFee) {
		this.inputFee = inputFee;
	}


	public int getRestFee() {
		return restFee;
	}

	public void setRestFee(int restFee) {
		this.restFee = restFee;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Amount [amountId=" + amountId + ", userId=" + userId + ", payFee=" + payFee + ", inputFee=" + inputFee +", restFee=" + restFee
				+ ", failCount=" + failCount + ", status=" + status + "]";
	}
	

}
