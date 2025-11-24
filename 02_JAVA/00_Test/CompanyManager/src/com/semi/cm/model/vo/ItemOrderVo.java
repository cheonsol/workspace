package com.semi.cm.model.vo;
/**
 * T_ITEM_ORDER
 */

import java.time.LocalDate;
import java.util.Objects;

public class ItemOrderVo {

	private int orderNo; // 요청번호
	private String itemCode; // 물품 코드
	private String itemName; //물품명
	private int orderCnt; // 요청 갯수
	private String orderState; // 요청 상태 R: 요청 / O: 승인 / C: 완료 / D: 반려
	private LocalDate reqDate; // 요청일
	private String orderCancelReason; // 반려 사유
	private String orderReqId; // 요청자
	private String orderResId; // 승인자
	public ItemOrderVo() {
		super();
	}
	public ItemOrderVo(int orderNo, String itemCode, int orderCnt, String orderState, LocalDate reqDate,
			String orderCancelReason, String orderReqId, String orderResId,String itemName) {
		super();
		this.orderNo = orderNo;
		this.itemCode = itemCode;
		this.orderCnt = orderCnt;
		this.orderState = orderState;
		this.reqDate = reqDate;
		this.orderCancelReason = orderCancelReason;
		this.orderReqId = orderReqId;
		this.orderResId = orderResId;
		this.itemName = itemName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public int getOrderCnt() {
		return orderCnt;
	}
	public void setOrderCnt(int orderCnt) {
		this.orderCnt = orderCnt;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public LocalDate getReqDate() {
		return reqDate;
	}
	public void setReqDate(LocalDate reqDate) {
		this.reqDate = reqDate;
	}
	public String getOrderCancelReason() {
		return orderCancelReason;
	}
	public void setOrderCancelReason(String orderCancelReason) {
		this.orderCancelReason = orderCancelReason;
	}
	public String getOrderReqId() {
		return orderReqId;
	}
	public void setOrderReqId(String orderReqId) {
		this.orderReqId = orderReqId;
	}
	public String getOrderResId() {
		return orderResId;
	}
	public void setOrderResId(String orderResId) {
		this.orderResId = orderResId;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(orderNo);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof ItemOrderVo) {
			ItemOrderVo i = (ItemOrderVo) obj;
			return this.orderNo == i.orderNo;
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "ItemOrderVo [orderNo=" + orderNo + ", itemCode=" + itemCode + ", itemName="+ itemName +", orderCnt=" + orderCnt + ", orderState="
				+ orderState + ", reqDate=" + reqDate + ", orderCancelReason=" + orderCancelReason + ", orderReqId="
				+ orderReqId + ", orderResId=" + orderResId + "]";
	}
	
	
	
	
	
}
