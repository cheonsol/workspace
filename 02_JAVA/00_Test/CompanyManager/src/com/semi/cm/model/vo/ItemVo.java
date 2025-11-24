package com.semi.cm.model.vo;

import java.util.Objects;

/**
 * T_ITEM
 */
public class ItemVo {

	private int itemNo; // 물품 번호
	private String itemCode; // 물품 코드
	private String itemName; // 물품명
	private int itemPrice; // 물품 가격
	private int itemCnt; // 물품 잔여 갯수
	private String itemReqId; // 요청자
	public ItemVo() {
		super();
	}
	public ItemVo(int itemNo, String itemCode, String itemName, int itemPrice, int itemCnt, String itemReqId) {
		super();
		this.itemNo = itemNo;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemCnt = itemCnt;
		this.itemReqId = itemReqId;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getItemCnt() {
		return itemCnt;
	}
	public void setItemCnt(int itemCnt) {
		this.itemCnt = itemCnt;
	}
	public String getItemReqId() {
		return itemReqId;
	}
	public void setItemReqId(String itemReqId) {
		this.itemReqId = itemReqId;
	}
	@Override
	public String toString() {
		return "ItemVo [itemNo=" + itemNo + ", itemCode=" + itemCode + ", itemName=" + itemName + ", itemPrice="
				+ itemPrice + ", itemCnt=" + itemCnt + ", itemReqId=" + itemReqId + "]";
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(itemNo,itemCode);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof ItemVo) {
			ItemVo i = (ItemVo)obj;
			return this.itemNo == i.itemNo && this.itemCode.equals(i.itemCode);
		}
		return super.equals(obj);
	}
	
	
	
	
	
	
}
