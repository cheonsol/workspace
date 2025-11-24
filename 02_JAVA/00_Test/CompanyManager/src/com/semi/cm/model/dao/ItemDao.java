package com.semi.cm.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.semi.cm.common.JdbcConnecntion;
import com.semi.cm.model.vo.ItemInputVo;
import com.semi.cm.model.vo.ItemOrderVo;
import com.semi.cm.model.vo.ItemOutputVo;
import com.semi.cm.model.vo.ItemUseVo;
import com.semi.cm.model.vo.ItemVo;

public class ItemDao {

	private Properties prop = new Properties();

	public ItemDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/item.xml"));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	/**
	 * 비품 조회
	 * @param i
	 * @param conn
	 * @return
	 */
	public List<ItemVo> selectItem(ItemVo i , Connection conn) {
		List<ItemVo> result = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectItem");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, i.getItemName());
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ItemVo iv = new ItemVo();
				iv.setItemCode(rset.getString("ITEM_CODE"));
				iv.setItemName(rset.getString("ITEM_NAME"));
				iv.setItemPrice(rset.getInt("ITEM_PRICE"));
				iv.setItemCnt(rset.getInt("ITEM_CNT"));
				iv.setItemReqId(rset.getString("ITEM_REQ_ID"));
				
				result.add(iv);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
			JdbcConnecntion.close(rset);
		}
		
		return result;
	}
	
	/**
	 * 비품 정보 등록
	 * @param i
	 * @param conn
	 * @return
	 */
	public int insertItem(ItemVo i,Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertItem");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, i.getItemCode());
			pstmt.setString(2, i.getItemName());
			pstmt.setInt(3, i.getItemPrice());
			pstmt.setString(4, i.getItemReqId());
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		
		return result;
	}
	
	/**
	 * 발주 요청
	 * @param io
	 * @param conn
	 * @return
	 */
	public int orderReq(ItemOrderVo io, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("orderReq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, io.getItemCode());
			pstmt.setInt(2, io.getOrderCnt());
			pstmt.setString(3, io.getOrderReqId());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		
		return result;
	}
	
	/**
	 * 발주 요청 처리
	 * @param io
	 * @param conn
	 * @return
	 */
	public int orderReqRespone(ItemOrderVo io, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("orderReqRespone");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, io.getOrderState());
			pstmt.setString(2, io.getOrderCancelReason());
			pstmt.setString(3, io.getOrderResId());
			pstmt.setInt(4, io.getOrderNo());
			
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		
		return result;
	}
	
	/**
	 * 물품 입고
	 * @param ii
	 * @param conn
	 * @return
	 */
	public int inputItem(ItemInputVo ii, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("inputItem");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ii.getItemCode());
			pstmt.setInt(2, ii.getInputCnt());
			pstmt.setInt(3, ii.getOrderNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 비품 사용 신청
	 * @param iu
	 * @param conn
	 * @return
	 */
	public int useReq(ItemUseVo iu, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("useReq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, iu.getItemCode());
			pstmt.setInt(2, iu.getReqCnt());
			pstmt.setString(3, iu.getReqId());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		
		return result;
	}
	
	/**
	 * 비품 사용 요청 처리
	 * @param iu
	 * @param conn
	 * @return
	 */
	public int useReqRespone(ItemUseVo iu,Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("useReqRespone");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iu.getReqState());
			pstmt.setString(2, iu.getReqCancelReason());
			pstmt.setString(3, iu.getResId());
			pstmt.setInt(4, iu.getReqNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		
		return result;
	}
	
	/**
	 * 비품 출고
	 * @param io
	 * @param conn
	 * @return
	 */
	public int outputItem(ItemOutputVo io, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("outputItem");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, io.getItemCode());
			pstmt.setInt(2, io.getOutputCnt());
			pstmt.setInt(3, io.getReqNo());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		
		
		return result;
	}
	
	/**
	 * 발주 요청 조회
	 * @param io
	 * @param conn
	 * @return
	 */
	public List<ItemOrderVo> selectOrderList (ItemOrderVo io, Connection conn) {
		List<ItemOrderVo> result = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOrderList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, io.getItemCode());
			pstmt.setString(2, io.getOrderState());
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ItemOrderVo iv = new ItemOrderVo();
				iv.setItemCode(rset.getString("ITEM_CODE"));
				iv.setOrderCancelReason(rset.getString("ORDER_CANCEL_REASON"));
				iv.setOrderCnt(rset.getInt("ORDER_CNT"));
				iv.setOrderNo(rset.getInt("ORDER_NO"));
				iv.setOrderReqId(rset.getString("ORDER_REQ_ID"));
				iv.setOrderResId(rset.getString("ORDER_RES_ID"));
				iv.setOrderState(rset.getString("ORDER_STATE"));
				iv.setReqDate(rset.getTimestamp("REQ_DATE").toLocalDateTime().toLocalDate());
				iv.setItemName(rset.getString("ITEM_NAME"));
				
				result.add(iv);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 사용 요청 조회
	 * @param iu
	 * @param conn
	 * @return
	 */
	public List<ItemUseVo> selectUseList(ItemUseVo iu, Connection conn) {
		List<ItemUseVo> result = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectUseList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iu.getItemCode());
			pstmt.setString(2, iu.getReqState());
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ItemUseVo iv = new ItemUseVo();
				iv.setItemCode(rset.getString("ITEM_CODE"));
				iv.setReqCancelReason(rset.getString("REQ_CANCEL_REASON"));
				iv.setReqCnt(rset.getInt("REQ_CNT"));
				iv.setReqNo(rset.getInt("REQ_NO"));
				iv.setReqId(rset.getString("REQ_ID"));
				iv.setResId(rset.getString("RES_ID"));
				iv.setReqState(rset.getString("REQ_STATE"));
				iv.setReqDate(rset.getTimestamp("REQ_DATE").toLocalDateTime().toLocalDate());
				iv.setItemName(rset.getString("ITEM_NAME"));
				
				result.add(iv);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			JdbcConnecntion.close(pstmt);
		}
		
		return result;
	}
}
