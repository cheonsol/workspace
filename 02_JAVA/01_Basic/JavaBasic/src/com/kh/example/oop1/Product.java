package com.kh.example.oop1;

public class Product {
	private String pName;
	private int price;
	private String brand;
	
	public Product() {
		super();
	}
	
	public void information() {
		System.out.printf("상품명 : %s / 가격 : %d / 브랜드 : %s\n", pName, price, brand);
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
