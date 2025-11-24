package com.semi.cm.model.vo;
/**
 * T_ITEM_OUTPUT
 */

import java.time.LocalDate;
import java.util.Objects;

public class ItemOutputVo {

	private int itemOutputNo; //출고 번호
	private String itemCode; // 물품 코드
	private LocalDate outputDate; // 출고일
	private int outputCnt; // 출고 갯수
	private int reqNo; // 요청번호
	public ItemOutputVo() {
		super();
	}
	public ItemOutputVo(int itemOutputNo, String itemCode, LocalDate outputDate, int outputCnt,int reqNo) {
		super();
		this.itemOutputNo = itemOutputNo;
		this.itemCode = itemCode;
		this.outputDate = outputDate;
		this.outputCnt = outputCnt;
		this.reqNo = reqNo;
	}
	public int getReqNo() {
		return reqNo;
	}
	public void setReqNo(int reqNo) {
		this.reqNo = reqNo;
	}
	public int getItemOutputNo() {
		return itemOutputNo;
	}
	public void setItemOutputNo(int itemOutputNo) {
		this.itemOutputNo = itemOutputNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public LocalDate getOutputDate() {
		return outputDate;
	}
	public void setOutputDate(LocalDate outputDate) {
		this.outputDate = outputDate;
	}
	public int getOutputCnt() {
		return outputCnt;
	}
	public void setOutputCnt(int outputCnt) {
		this.outputCnt = outputCnt;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(itemOutputNo);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof ItemOutputVo) {
			ItemOutputVo i =(ItemOutputVo)obj;
			return this.itemOutputNo == i.itemOutputNo;
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "ItemOutputVo [itemOutputNo=" + itemOutputNo + ", itemCode=" + itemCode + ", outputDate=" + outputDate
				+ ", outputCnt=" + outputCnt + ", reqNo "+reqNo+" ]";
	}
	
	
	
}
