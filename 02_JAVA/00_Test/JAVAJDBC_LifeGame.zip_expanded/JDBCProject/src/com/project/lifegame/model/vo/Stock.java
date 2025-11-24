package com.project.lifegame.model.vo;

public class Stock {
	private int price;
	private int charNo;
	
	public Stock() {
		super();
	}

	public Stock(int price, int charNo) {
		super();
		this.price = price;
		this.charNo = charNo;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCharNo() {
		return charNo;
	}

	public void setCharNo(int charNo) {
		this.charNo = charNo;
	}
}
