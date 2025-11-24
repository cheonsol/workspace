package com.kh.inherit;

public class Product {
	private String pName;
	private int price;
	private String brand;
	
	public Product() {
		super();
		System.out.println("Product기본생성자 호출");
	}
	public Product(String pName, int price, String brand) {
		super();
		this.pName = pName;
		this.price = price;
		this.brand = brand;
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
	
	public String inform() {
		return "상품명 : "+pName+" / 가격 : "+price+" / 브랜드 : " + brand;
	}
	
}
