package com.semi.cm.controller;

import java.util.List;

import com.semi.cm.model.vo.ItemInputVo;
import com.semi.cm.model.vo.ItemOrderVo;
import com.semi.cm.model.vo.ItemOutputVo;
import com.semi.cm.model.vo.ItemUseVo;
import com.semi.cm.model.vo.ItemVo;
import com.semi.cm.service.ItemService;
import com.semi.cm.view.CompanyMenu;

public class ItemController {

	private ItemService is = new ItemService();

	public ItemController() {
		super();
	}
	
	
	/**
	 * 비품 조회
	 * @param i
	 */
	public void selectItem(ItemVo i) {
		
		List<ItemVo> result = is.selectItem(i);
		
		if (result.size() > 0) {
			new CompanyMenu().displaySuccess("");
			for (int j=0; j<result.size(); j++) {
				System.out.println(result.get(j));
			}
		} else {
			new CompanyMenu().displayFail("회원 추가 실패");
		}
		
	}
	
	/**
	 * 비품 정보 등록
	 * @param i
	 */
	public void insertItem(ItemVo i) {
		
		int result = is.insertItem(i);
			
		if (result > 0) {
			new CompanyMenu().displaySuccess("비품 정보가 등록되었습니다.");
		} else {
			new CompanyMenu().displayFail("비품 정보 등록에 실패하였습니다.");
		}
 		
	}
	
	/**
	 * 발주 요청
	 * @param i
	 * @param io
	 */
	public void orderReq(ItemVo i,ItemOrderVo io) {
		
		int result = is.orderReq(i,io);
		
		if (result > 0) {
			new CompanyMenu().displaySuccess("발주 요청이 완료되었습니다.");
		} else {
			new CompanyMenu().displayFail("발주 요청에 실패하였습니다.");
		}
		
	}
	
	/**
	 * 발주 요청 처리
	 * @param io
	 */
	public void orderReqRespone(ItemOrderVo io) {
		
		int result = is.orderReqRespone(io);
		
		if (result > 0) {
			new CompanyMenu().displaySuccess("요청처리가 완료되었습니다.");
		} else {
			new CompanyMenu().displayFail("요청처리가 실패하였습니다.");
		}
	}
	
	/**
	 * 물품 입고
	 * @param ii
	 * @param i
	 */
	public void inputItem(ItemInputVo ii, ItemVo i) {
		
		int result = is.inputItem(ii, i);
		
		if (result > 0) {
			new CompanyMenu().displaySuccess("입고처리가 완료되었습니다.");
		} else {
			new CompanyMenu().displayFail("입고처리가 실패하였습니다.");
		}
		
	}
	/**
	 * 비품 사용 신청
	 * @param iu
	 * @param i
	 */
	public void useReq(ItemUseVo iu, ItemVo i) {
		
		int result = is.useReq(iu,i);
		
		if(result > 0) {
			new CompanyMenu().displaySuccess("신청처리가 완료되었습니다.");
		} else {
			new CompanyMenu().displayFail("신청처리가 실패하였습니다.");
		}
		
	}
	
	/**
	 * 비품 사용 요청 처리
	 * @param iu
	 */
	public void useReqRespone(ItemUseVo iu) {
		
		int result = is.useReqRespone(iu);
		
		if(result > 0) {
			new CompanyMenu().displaySuccess("요청처리가 완료되었습니다.");
		} else {
			new CompanyMenu().displayFail("요청처리가 실패하였습니다.");
		}
		
	}
	
	/**
	 * 비품출고
	 * @param io
	 * @param i
	 */
	public void outputItem(ItemOutputVo io,ItemVo i) {
		
		int result = is.outputItem(io,i);
		
		if(result > 0) {
			new CompanyMenu().displaySuccess("출고처리가 완료되었습니다.");
		} else {
			new CompanyMenu().displayFail("출고처리가 실패하였습니다.");
		}
		
	}
	
	/**
	 * 발주 요청 조회
	 * @param io
	 * @param i
	 */
	public void selectOrderList(ItemOrderVo io, ItemVo i) {
		
		List<ItemOrderVo> result = is.selectOrderList(io,i);
		
		if(result.size() > 0) {
			new CompanyMenu().displaySuccess("");
			for (int j=0; j<result.size(); j++) {
				System.out.println(result.get(j));
			}
		} else {
			new CompanyMenu().displayFail("데이터가 없습니다.");
		}
		
	}
	
	/**
	 * 사용 요청 조회
	 * @param iu
	 * @param i
	 */
	public void selectUseList(ItemUseVo iu, ItemVo i) {
		List<ItemUseVo> result = is.selectUseList(iu,i);
		
		if(result.size() > 0) {
			new CompanyMenu().displaySuccess("");
			for (int j=0; j<result.size(); j++) {
				System.out.println(result.get(j));
			}
		} else {
			new CompanyMenu().displayFail("데이터가 없습니다.");
		}
		
	}
}
