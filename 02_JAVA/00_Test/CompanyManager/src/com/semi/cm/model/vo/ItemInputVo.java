package com.semi.cm.model.vo;
/**
 * T_ITEM_INPUT
 */

import java.time.LocalDate;
import java.util.Objects;

public class ItemInputVo {

	private int itemInputNo; // 입고 번호
	private String itemCode; // 물품코드
	private LocalDate inputDate; // 입고일
	private int inputCnt; // 입고갯수
	private int orderNo; //요청번호
	public ItemInputVo() {
		super();
	}
	public ItemInputVo(int itemInputNo, String itemCode, LocalDate inputDate, int inputCnt,int orderNo) {
		super();
		this.itemInputNo = itemInputNo;
		this.itemCode = itemCode;
		this.inputDate = inputDate;
		this.inputCnt = inputCnt;
		this.orderNo = orderNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getItemInputNo() {
		return itemInputNo;
	}
	public void setItemInputNo(int itemInputNo) {
		this.itemInputNo = itemInputNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public LocalDate getInputDate() {
		return inputDate;
	}
	public void setInputDate(LocalDate inputDate) {
		this.inputDate = inputDate;
	}
	public int getInputCnt() {
		return inputCnt;
	}
	public void setInputCnt(int inputCnt) {
		this.inputCnt = inputCnt;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(itemInputNo);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof ItemInputVo) {
			ItemInputVo i = (ItemInputVo)obj;
			return this.itemInputNo == i.itemInputNo;
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "ItemInput [itemInputNo=" + itemInputNo + ", itemCode=" + itemCode + ", inputDate=" + inputDate
				+ ", inputCnt=" + inputCnt + ", orderNo="+orderNo+"]";
	}
	
	
	
	
}
