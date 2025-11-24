package com.kh.jdbc.model.vo;

import java.time.LocalDateTime;

public class Device {
	private Long devNo;
	private String category;
	private String devName;
	private int price;
	private LocalDateTime arrivalDate;

	public Device() {
		super();
	}

	public Device(Long devNo, String category, String devName, int price, LocalDateTime arrivalDate) {
		super();
		this.devNo = devNo;
		this.category = category;
		this.devName = devName;
		this.price = price;
		this.arrivalDate = arrivalDate;
	}

	public Device(String category, String devName, int price) {
		super();
		this.category = category;
		this.devName = devName;
		this.price = price;
	}

	public Long getDevNo() {
		return devNo;
	}

	public void setDevNo(Long devNo) {
		this.devNo = devNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	@Override
	public String toString() {
		return "Device [devNo=" + devNo + ", category=" + category + ", devName=" + devName + ", price=" + price
				+ ", arrivalDate=" + arrivalDate + "]";
	}

}
