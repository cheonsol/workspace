package com.kh.kiosk.model.vo;

public class OrderItem {
	private long orderItemId;
	private long orderId;
	private long prodId;
	private int qty;
	private long unitPrice;

	public OrderItem() {
	}

	public OrderItem(long orderItemId, long orderId, long prodId, int qty, long unitPrice) {
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.prodId = prodId;
		this.qty = qty;
		this.unitPrice = unitPrice;
	}

	public OrderItem(long orderId, long prodId, int qty, long unitPrice) {
		this.orderId = orderId;
		this.prodId = prodId;
		this.qty = qty;
		this.unitPrice = unitPrice;
	}

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getProdId() {
		return prodId;
	}

	public void setProdId(long prodId) {
		this.prodId = prodId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(long unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "OrderItem{orderItemId=" + orderItemId + ", orderId=" + orderId + ", prodId=" + prodId + ", qty=" + qty
				+ ", unitPrice=" + unitPrice + '}';
	}
}
