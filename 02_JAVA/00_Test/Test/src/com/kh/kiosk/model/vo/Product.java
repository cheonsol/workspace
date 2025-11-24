package com.kh.kiosk.model.vo;

import java.time.LocalDateTime;

public class Product {
	private long prodId;
	private String name;
	private String category;
	private long price;
	private int stockQty;
	private String isPerishable; // 'Y' or 'N'
	private LocalDateTime expiryDate; // 날짜만 의미해도 LocalDateTime으로 통일
	private String onSaleYn; // 'Y' or 'N'

	public Product() {
	}

	public Product(long prodId, String name, String category, long price, int stockQty,
			String isPerishable, LocalDateTime expiryDate, String onSaleYn) {
		this.prodId = prodId;
		this.name = name;
		this.category = category;
		this.price = price;
		this.stockQty = stockQty;
		this.isPerishable = isPerishable;
		this.expiryDate = expiryDate;
		this.onSaleYn = onSaleYn;
	}

	public long getProdId() {
		return prodId;
	}

	public void setProdId(long prodId) {
		this.prodId = prodId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getStockQty() {
		return stockQty;
	}

	public void setStockQty(int stockQty) {
		this.stockQty = stockQty;
	}

	public String getIsPerishable() {
		return isPerishable;
	}

	public void setIsPerishable(String isPerishable) {
		this.isPerishable = isPerishable;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getOnSaleYn() {
		return onSaleYn;
	}

	public void setOnSaleYn(String onSaleYn) {
		this.onSaleYn = onSaleYn;
	}

	@Override
	public String toString() {
		return "Product{prodId=" + prodId + ", name='" + name + '\'' + ", category='"
				+ category + '\'' + ", price=" + price + ", stockQty=" + stockQty + ", isPerishable='" + isPerishable
				+ '\'' + ", expiryDate=" + expiryDate + ", onSaleYn='" + onSaleYn + '\'' + '}';
	}
}
