package com.semi.cm.model.vo;
/**
 * T_ITEM_USE
 */

import java.time.LocalDate;
import java.util.Objects;

public class ItemUseVo {

	private int reqNo; //요청 번호
	private String itemCode; // 물품 코드
	private String itemName;
	private int reqCnt; // 요청 갯수
	private String reqState; // 요청 상태 R: 요청 / O: 승인 / C: 완료 / D: 반려
	private LocalDate reqDate; // 요청일 
	private String reqCancelReason; // 반려 사유
	private String reqId; // 요청자
	private String resId; // 승인자
	public ItemUseVo() {
		super();
	}
	public ItemUseVo(int reqNo, String itemCode, int reqCnt, String reqState, LocalDate reqDate, String reqCancelReason,
			String reqId, String resId,String itemName) {
		super();
		this.reqNo = reqNo;
		this.itemCode = itemCode;
		this.reqCnt = reqCnt;
		this.reqState = reqState;
		this.reqDate = reqDate;
		this.reqCancelReason = reqCancelReason;
		this.reqId = reqId;
		this.resId = resId;
		this.itemName = itemName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getReqNo() {
		return reqNo;
	}
	public void setReqNo(int reqNo) {
		this.reqNo = reqNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public int getReqCnt() {
		return reqCnt;
	}
	public void setReqCnt(int reqCnt) {
		this.reqCnt = reqCnt;
	}
	public String getReqState() {
		return reqState;
	}
	public void setReqState(String reqState) {
		this.reqState = reqState;
	}
	public LocalDate getReqDate() {
		return reqDate;
	}
	public void setReqDate(LocalDate reqDate) {
		this.reqDate = reqDate;
	}
	public String getReqCancelReason() {
		return reqCancelReason;
	}
	public void setReqCancelReason(String reqCancelReason) {
		this.reqCancelReason = reqCancelReason;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(reqNo);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof ItemUseVo) {
			ItemUseVo i = (ItemUseVo)obj;
			return this.reqNo == i.reqNo;
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "ItemUse [reqNo=" + reqNo + ", itemCode=" + itemCode + ", itemName ="+itemName+", reqCnt=" + reqCnt + ", reqState=" + reqState
				+ ", reqDate=" + reqDate + ", reqCancelReason=" + reqCancelReason + ", reqId=" + reqId + ", resId="
				+ resId + "]";
	}
	
	
	
	
	
}
