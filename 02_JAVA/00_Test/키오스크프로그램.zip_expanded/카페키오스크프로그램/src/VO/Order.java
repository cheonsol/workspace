package VO;

import java.util.Date;

public class Order {
	private int OrderId;
	private String phone; // 주문자 전화번호 (PK)
	private Date orderDate;
	private String ShowOrder;
	private double totalPrice;
	private String status;

	public Order() {

	}

	public Order(String phone, Date orderDate, String showOrder, double totalPrice, String status) {

		this.phone = phone;
		this.orderDate = orderDate;
		this.ShowOrder = showOrder;
		this.totalPrice = totalPrice;
		this.status = status;
	}

	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		this.OrderId = orderId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShowOrder() {
		return ShowOrder;
	}

	public void setShowOrder(String showOrder) {
		this.ShowOrder = showOrder;
	}
}