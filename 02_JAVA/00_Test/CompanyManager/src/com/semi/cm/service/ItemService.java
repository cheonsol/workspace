package com.semi.cm.service;


import static com.semi.cm.common.JdbcConnecntion.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.semi.cm.model.dao.ItemDao;
import com.semi.cm.model.vo.ItemInputVo;
import com.semi.cm.model.vo.ItemOrderVo;
import com.semi.cm.model.vo.ItemOutputVo;
import com.semi.cm.model.vo.ItemUseVo;
import com.semi.cm.model.vo.ItemVo;

public class ItemService {

	private ItemDao id;

	public ItemService() {
		super();
		this.id = new ItemDao();
	}
	
	/**
	 * 비품 조회
	 * @param i
	 * @return
	 */
	public List<ItemVo> selectItem (ItemVo i) {
		Connection conn = getConnection();
		List<ItemVo> result = new ArrayList<>();
		
		result = id.selectItem(i,conn);
		
		if (result.size() > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	/**
	 * 비품 정보 등록
	 * @param i
	 * @return
	 */
	public int insertItem (ItemVo i) {
		Connection conn = getConnection();
		int result = 0;
		
		result = id.insertItem(i,conn);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	/**
	 * 발주 요청
	 * @param i
	 * @param io
	 * @return
	 */
	public int orderReq(ItemVo i, ItemOrderVo io) {
		Connection conn = getConnection();
		int result = 0;
		
		List<ItemVo> itemList = id.selectItem(i, conn);
		
		String itemCode = itemList.get(0).getItemCode();
		io.setItemCode(itemCode);
		
		result = id.orderReq(io,conn);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	/**
	 * 발주 요청 처리
	 * @param io
	 * @return
	 */
	public int orderReqRespone(ItemOrderVo io) {
		Connection conn = getConnection();
		int result = 0;
		
		result = id.orderReqRespone(io,conn);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	/**
	 * 물품 입고
	 * @param ii
	 * @param i
	 * @return
	 */
	public int inputItem(ItemInputVo ii, ItemVo i) {
		Connection conn = getConnection();
		int result = 0;
		List<ItemVo> itemList = id.selectItem(i, conn);
		
		String itemCode = itemList.get(0).getItemCode();
		ii.setItemCode(itemCode);
		
		result = id.inputItem(ii,conn);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	/**
	 * 비품 사용 신청
	 * @param iu
	 * @param i
	 * @return
	 */
	public int useReq(ItemUseVo iu, ItemVo i) {
		Connection conn = getConnection();
		int result = 0;
		List<ItemVo> itemList = id.selectItem(i, conn);
		
		String itemCode = itemList.get(0).getItemCode();
		iu.setItemCode(itemCode);
		
		result = id.useReq(iu,conn);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
		
	}
	
	/**
	 * 비품 사용 요청 처리
	 * @param iu
	 * @return
	 */
	public int useReqRespone(ItemUseVo iu) {
		Connection conn = getConnection();
		int result = 0;
		
		result = id.useReqRespone(iu,conn);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	/**
	 * 비품 출고
	 * @param io
	 * @param i
	 * @return
	 */
	public int outputItem(ItemOutputVo io, ItemVo i) {
		Connection conn = getConnection();
		int result = 0;
		List<ItemVo> itemList = id.selectItem(i, conn);
		
		String itemCode = itemList.get(0).getItemCode();
		io.setItemCode(itemCode);
		
		result = id.outputItem(io,conn);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	/**
	 * 발주 요청 조회
	 * @param io
	 * @param i
	 * @return
	 */
	public List<ItemOrderVo> selectOrderList(ItemOrderVo io, ItemVo i) {
		Connection conn = getConnection();
		List<ItemOrderVo> list = new ArrayList<>();
		if (i.getItemName().length() > 0) {
			List<ItemVo> itemList = id.selectItem(i, conn);
			
			String itemCode = itemList.get(0).getItemCode();
			io.setItemCode(itemCode);
		}
		
		list = id.selectOrderList(io,conn);
		close(conn);
		
		return list;
	}
	
	/**
	 * 사용 요청 조회
	 * @param iu
	 * @param i
	 * @return
	 */
	public List<ItemUseVo> selectUseList(ItemUseVo iu, ItemVo i) {
		Connection conn = getConnection();
		List<ItemUseVo> list= new ArrayList<>();
		if (i.getItemName().length() > 0) {
			List<ItemVo> itemList = id.selectItem(i, conn);
			
			String itemCode = itemList.get(0).getItemCode();
			iu.setItemCode(itemCode);
		}
		
		list = id.selectUseList(iu,conn);
		
		close(conn);
		return list;
	}
	
}
