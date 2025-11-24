package com.limbus.sin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.limbus.sin.model.vo.Identity;

import static com.limbus.sin.common.JDBCTemplate.*;

public class SinDao {
	public ArrayList<Identity> selectSinList(Connection conn){
		ResultSet rset = null;
		ArrayList<Identity> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = "SELECT * FROM SIN_TABLE ORDER BY IDEN_NO ASC";
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Identity i = new Identity();
				i.setIdenNo(rset.getInt("IDEN_NO"));
				i.setCharNo(rset.getInt("CHAR_NO"));
				i.setIdenName(rset.getString("IDEN_NAME"));
				i.setSeason(rset.getInt("SEASON"));
				i.setRarity(rset.getInt("RARITY"));
				
				i.setHp(rset.getInt("HP"));
				i.setSpeedMin(rset.getInt("SPEED_MIN"));
				i.setSpeedMax(rset.getInt("SPEED_MAX"));
				i.setHitLevel(rset.getInt("HIT_LEVEL"));
				i.setHitSlash(rset.getInt("HIT_SLASH"));
				i.setHitPene(rset.getInt("HIT_PENE"));
				i.setHitBlow(rset.getInt("HIT_BLOW"));
				
				i.setFirName(rset.getString("FIR_NAME"));
				i.setFirSin(rset.getInt("FIR_SIN"));
				i.setFirType(rset.getInt("FIR_TYPE"));
				i.setFirLevel(rset.getInt("FIR_LEVEL"));
				i.setFirCoinMany(rset.getInt("FIR_COIN_MANY"));
				i.setFirBase(rset.getInt("FIR_BASE"));
				i.setFirCoin(rset.getInt("FIR_COIN"));
				
				i.setSecName(rset.getString("SEC_NAME"));
				i.setSecSin(rset.getInt("SEC_SIN"));
				i.setSecType(rset.getInt("SEC_TYPE"));
				i.setSecLevel(rset.getInt("SEC_LEVEL"));
				i.setSecCoinMany(rset.getInt("SEC_COIN_MANY"));
				i.setSecBase(rset.getInt("SEC_BASE"));
				i.setSecCoin(rset.getInt("SEC_COIN"));
				
				i.setThiName(rset.getString("THI_NAME"));
				i.setThiSin(rset.getInt("THI_SIN"));
				i.setThiType(rset.getInt("THI_TYPE"));
				i.setThiLevel(rset.getInt("THI_LEVEL"));
				i.setThiCoinMany(rset.getInt("THI_COIN_MANY"));
				i.setThiBase(rset.getInt("THI_BASE"));
				i.setThiCoin(rset.getInt("THI_COIN"));
				
				i.setDefName(rset.getString("DEF_NAME"));
				i.setDefSin(rset.getInt("DEF_SIN"));
				i.setDefType(rset.getInt("DEF_TYPE"));
				i.setDefLevel(rset.getInt("DEF_LEVEL"));
				i.setDefCoinMany(rset.getInt("DEF_COIN_MANY"));
				i.setDefBase(rset.getInt("DEF_BASE"));
				i.setDefCoin(rset.getInt("DEF_COIN"));
				
				i.setPasName(rset.getString("PAS_NAME"));
				i.setPasKind(rset.getInt("PAS_KIND"));
				i.setPasSin(rset.getInt("PAS_SIN"));
				i.setPasSinSec(rset.getInt("PAS_SIN_SEC"));
				i.setPasType(rset.getInt("PAS_TYPE"));
				i.setPasMany(rset.getInt("PAS_MANY"));
				i.setPasManySec(rset.getInt("PAS_MANY_SEC"));
				
				i.setSupName(rset.getString("SUP_NAME"));
				i.setSupKind(rset.getInt("SUP_KIND"));
				i.setSupSin(rset.getInt("SUP_SIN"));
				i.setSupSinSec(rset.getInt("SUP_SIN_SEC"));
				i.setSupType(rset.getInt("SUP_TYPE"));
				i.setSupMany(rset.getInt("SUP_MANY"));
				i.setSupManySec(rset.getInt("SUP_MANY_SEC"));
				
				i.setRelation(rset.getString("RELATION"));
				i.setKeyword(rset.getString("KEYWORD"));
				
				list.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				close(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	}
	
	public ArrayList<Identity> identityView(int idenNo, Connection conn){
		ResultSet rset = null;
		ArrayList<Identity> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = "SELECT * FROM SIN_TABLE WHERE IDEN_NO =" + idenNo + "ORDER BY IDEN_NO ASC";
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Identity i = new Identity();
				i.setIdenNo(rset.getInt("IDEN_NO"));
				i.setCharNo(rset.getInt("CHAR_NO"));
				i.setIdenName(rset.getString("IDEN_NAME"));
				i.setSeason(rset.getInt("SEASON"));
				i.setRarity(rset.getInt("RARITY"));
				
				i.setHp(rset.getInt("HP"));
				i.setSpeedMin(rset.getInt("SPEED_MIN"));
				i.setSpeedMax(rset.getInt("SPEED_MAX"));
				i.setHitLevel(rset.getInt("HIT_LEVEL"));
				i.setHitSlash(rset.getInt("HIT_SLASH"));
				i.setHitPene(rset.getInt("HIT_PENE"));
				i.setHitBlow(rset.getInt("HIT_BLOW"));
				
				i.setFirName(rset.getString("FIR_NAME"));
				i.setFirSin(rset.getInt("FIR_SIN"));
				i.setFirType(rset.getInt("FIR_TYPE"));
				i.setFirLevel(rset.getInt("FIR_LEVEL"));
				i.setFirCoinMany(rset.getInt("FIR_COIN_MANY"));
				i.setFirBase(rset.getInt("FIR_BASE"));
				i.setFirCoin(rset.getInt("FIR_COIN"));
				
				i.setSecName(rset.getString("SEC_NAME"));
				i.setSecSin(rset.getInt("SEC_SIN"));
				i.setSecType(rset.getInt("SEC_TYPE"));
				i.setSecLevel(rset.getInt("SEC_LEVEL"));
				i.setSecCoinMany(rset.getInt("SEC_COIN_MANY"));
				i.setSecBase(rset.getInt("SEC_BASE"));
				i.setSecCoin(rset.getInt("SEC_COIN"));
				
				i.setThiName(rset.getString("THI_NAME"));
				i.setThiSin(rset.getInt("THI_SIN"));
				i.setThiType(rset.getInt("THI_TYPE"));
				i.setThiLevel(rset.getInt("THI_LEVEL"));
				i.setThiCoinMany(rset.getInt("THI_COIN_MANY"));
				i.setThiBase(rset.getInt("THI_BASE"));
				i.setThiCoin(rset.getInt("THI_COIN"));
				
				i.setDefName(rset.getString("DEF_NAME"));
				i.setDefSin(rset.getInt("DEF_SIN"));
				i.setDefType(rset.getInt("DEF_TYPE"));
				i.setDefLevel(rset.getInt("DEF_LEVEL"));
				i.setDefCoinMany(rset.getInt("DEF_COIN_MANY"));
				i.setDefBase(rset.getInt("DEF_BASE"));
				i.setDefCoin(rset.getInt("DEF_COIN"));
				
				i.setPasName(rset.getString("PAS_NAME"));
				i.setPasKind(rset.getInt("PAS_KIND"));
				i.setPasSin(rset.getInt("PAS_SIN"));
				i.setPasSinSec(rset.getInt("PAS_SIN_SEC"));
				i.setPasType(rset.getInt("PAS_TYPE"));
				i.setPasMany(rset.getInt("PAS_MANY"));
				i.setPasManySec(rset.getInt("PAS_MANY_SEC"));
				
				i.setSupName(rset.getString("SUP_NAME"));
				i.setSupKind(rset.getInt("SUP_KIND"));
				i.setSupSin(rset.getInt("SUP_SIN"));
				i.setSupSinSec(rset.getInt("SUP_SIN_SEC"));
				i.setSupType(rset.getInt("SUP_TYPE"));
				i.setSupMany(rset.getInt("SUP_MANY"));
				i.setSupManySec(rset.getInt("SUP_MANY_SEC"));
				
				i.setRelation(rset.getString("RELATION"));
				i.setKeyword(rset.getString("KEYWORD"));
				
				list.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				close(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	}
	
	public ArrayList<Identity> selectDeckList(Connection conn){
		ResultSet rset = null;
		ArrayList<Identity> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = "SELECT * FROM SIN_TABLE JOIN DECK USING (IDEN_NO) ORDER BY IDEN_NO ASC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Identity i = new Identity();
				i.setIdenNo(rset.getInt("IDEN_NO"));
				i.setCharNo(rset.getInt("CHAR_NO"));
				i.setIdenName(rset.getString("IDEN_NAME"));
				i.setSeason(rset.getInt("SEASON"));
				i.setRarity(rset.getInt("RARITY"));
				
				i.setHp(rset.getInt("HP"));
				i.setSpeedMin(rset.getInt("SPEED_MIN"));
				i.setSpeedMax(rset.getInt("SPEED_MAX"));
				i.setHitLevel(rset.getInt("HIT_LEVEL"));
				i.setHitSlash(rset.getInt("HIT_SLASH"));
				i.setHitPene(rset.getInt("HIT_PENE"));
				i.setHitBlow(rset.getInt("HIT_BLOW"));
				
				i.setFirName(rset.getString("FIR_NAME"));
				i.setFirSin(rset.getInt("FIR_SIN"));
				i.setFirType(rset.getInt("FIR_TYPE"));
				i.setFirLevel(rset.getInt("FIR_LEVEL"));
				i.setFirCoinMany(rset.getInt("FIR_COIN_MANY"));
				i.setFirBase(rset.getInt("FIR_BASE"));
				i.setFirCoin(rset.getInt("FIR_COIN"));
				
				i.setSecName(rset.getString("SEC_NAME"));
				i.setSecSin(rset.getInt("SEC_SIN"));
				i.setSecType(rset.getInt("SEC_TYPE"));
				i.setSecLevel(rset.getInt("SEC_LEVEL"));
				i.setSecCoinMany(rset.getInt("SEC_COIN_MANY"));
				i.setSecBase(rset.getInt("SEC_BASE"));
				i.setSecCoin(rset.getInt("SEC_COIN"));
				
				i.setThiName(rset.getString("THI_NAME"));
				i.setThiSin(rset.getInt("THI_SIN"));
				i.setThiType(rset.getInt("THI_TYPE"));
				i.setThiLevel(rset.getInt("THI_LEVEL"));
				i.setThiCoinMany(rset.getInt("THI_COIN_MANY"));
				i.setThiBase(rset.getInt("THI_BASE"));
				i.setThiCoin(rset.getInt("THI_COIN"));
				
				i.setDefName(rset.getString("DEF_NAME"));
				i.setDefSin(rset.getInt("DEF_SIN"));
				i.setDefType(rset.getInt("DEF_TYPE"));
				i.setDefLevel(rset.getInt("DEF_LEVEL"));
				i.setDefCoinMany(rset.getInt("DEF_COIN_MANY"));
				i.setDefBase(rset.getInt("DEF_BASE"));
				i.setDefCoin(rset.getInt("DEF_COIN"));
				
				i.setPasName(rset.getString("PAS_NAME"));
				i.setPasKind(rset.getInt("PAS_KIND"));
				i.setPasSin(rset.getInt("PAS_SIN"));
				i.setPasSinSec(rset.getInt("PAS_SIN_SEC"));
				i.setPasType(rset.getInt("PAS_TYPE"));
				i.setPasMany(rset.getInt("PAS_MANY"));
				i.setPasManySec(rset.getInt("PAS_MANY_SEC"));
				
				i.setSupName(rset.getString("SUP_NAME"));
				i.setSupKind(rset.getInt("SUP_KIND"));
				i.setSupSin(rset.getInt("SUP_SIN"));
				i.setSupSinSec(rset.getInt("SUP_SIN_SEC"));
				i.setSupType(rset.getInt("SUP_TYPE"));
				i.setSupMany(rset.getInt("SUP_MANY"));
				i.setSupManySec(rset.getInt("SUP_MANY_SEC"));
				
				i.setRelation(rset.getString("RELATION"));
				i.setKeyword(rset.getString("KEYWORD"));
				
				list.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				close(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	}
	
	public int charCount(int n, Connection conn) {
		
		ResultSet rset = null;
		ArrayList<Identity> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		int count = 0;
		
		String sql = "SELECT COUNT(*) FROM SIN_TABLE GROUP BY CHAR_NO HAVING CHAR_NO=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, n);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				close(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return count+1;
		}
	}
	
	public ArrayList<Identity> charList(int n, Connection conn) {
		ResultSet rset = null;
		ArrayList<Identity> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = "SELECT * FROM SIN_TABLE WHERE CHAR_NO = ? ORDER BY IDEN_NO ASC";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, n);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Identity i = new Identity();
				i.setIdenNo(rset.getInt("IDEN_NO"));
				i.setCharNo(rset.getInt("CHAR_NO"));
				i.setIdenName(rset.getString("IDEN_NAME"));
				i.setSeason(rset.getInt("SEASON"));
				i.setRarity(rset.getInt("RARITY"));
				
				i.setHp(rset.getInt("HP"));
				i.setSpeedMin(rset.getInt("SPEED_MIN"));
				i.setSpeedMax(rset.getInt("SPEED_MAX"));
				i.setHitLevel(rset.getInt("HIT_LEVEL"));
				i.setHitSlash(rset.getInt("HIT_SLASH"));
				i.setHitPene(rset.getInt("HIT_PENE"));
				i.setHitBlow(rset.getInt("HIT_BLOW"));
				
				i.setFirName(rset.getString("FIR_NAME"));
				i.setFirSin(rset.getInt("FIR_SIN"));
				i.setFirType(rset.getInt("FIR_TYPE"));
				i.setFirLevel(rset.getInt("FIR_LEVEL"));
				i.setFirCoinMany(rset.getInt("FIR_COIN_MANY"));
				i.setFirBase(rset.getInt("FIR_BASE"));
				i.setFirCoin(rset.getInt("FIR_COIN"));
				
				i.setSecName(rset.getString("SEC_NAME"));
				i.setSecSin(rset.getInt("SEC_SIN"));
				i.setSecType(rset.getInt("SEC_TYPE"));
				i.setSecLevel(rset.getInt("SEC_LEVEL"));
				i.setSecCoinMany(rset.getInt("SEC_COIN_MANY"));
				i.setSecBase(rset.getInt("SEC_BASE"));
				i.setSecCoin(rset.getInt("SEC_COIN"));
				
				i.setThiName(rset.getString("THI_NAME"));
				i.setThiSin(rset.getInt("THI_SIN"));
				i.setThiType(rset.getInt("THI_TYPE"));
				i.setThiLevel(rset.getInt("THI_LEVEL"));
				i.setThiCoinMany(rset.getInt("THI_COIN_MANY"));
				i.setThiBase(rset.getInt("THI_BASE"));
				i.setThiCoin(rset.getInt("THI_COIN"));
				
				i.setDefName(rset.getString("DEF_NAME"));
				i.setDefSin(rset.getInt("DEF_SIN"));
				i.setDefType(rset.getInt("DEF_TYPE"));
				i.setDefLevel(rset.getInt("DEF_LEVEL"));
				i.setDefCoinMany(rset.getInt("DEF_COIN_MANY"));
				i.setDefBase(rset.getInt("DEF_BASE"));
				i.setDefCoin(rset.getInt("DEF_COIN"));
				
				i.setPasName(rset.getString("PAS_NAME"));
				i.setPasKind(rset.getInt("PAS_KIND"));
				i.setPasSin(rset.getInt("PAS_SIN"));
				i.setPasSinSec(rset.getInt("PAS_SIN_SEC"));
				i.setPasType(rset.getInt("PAS_TYPE"));
				i.setPasMany(rset.getInt("PAS_MANY"));
				i.setPasManySec(rset.getInt("PAS_MANY_SEC"));
				
				i.setSupName(rset.getString("SUP_NAME"));
				i.setSupKind(rset.getInt("SUP_KIND"));
				i.setSupSin(rset.getInt("SUP_SIN"));
				i.setSupSinSec(rset.getInt("SUP_SIN_SEC"));
				i.setSupType(rset.getInt("SUP_TYPE"));
				i.setSupMany(rset.getInt("SUP_MANY"));
				i.setSupManySec(rset.getInt("SUP_MANY_SEC"));
				
				i.setRelation(rset.getString("RELATION"));
				i.setKeyword(rset.getString("KEYWORD"));
				
				list.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				close(pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
	}
	
	public int identityChange(int charNo, int idenNo, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE DECK SET IDEN_NO = " + idenNo + " WHERE CHAR_NO = " + charNo;
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
			if(result > 0 ) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			return result;
		}
	}
	
	public int identityNameEdit(int idenNo, String idenName, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE SIN_TABLE SET IDEN_NAME = '" + idenName + "' WHERE IDEN_NO = " + idenNo;
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
			if(result > 0 ) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			return result;
		}
	}
	
	public int identityRelationEdit(int idenNo, String relation, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE SIN_TABLE SET RELATION = '" + relation + "' WHERE IDEN_NO = " + idenNo;
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
			if(result > 0 ) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			return result;
		}
	}
	
	public int identityKeywordEdit(int idenNo, String keyword, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE SIN_TABLE SET KEYWORD = '" + keyword + "' WHERE IDEN_NO = " + idenNo;
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
			if(result > 0 ) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			return result;
		}
	}
	
	public int identityStateEdit(int idenNo, int hp, int speedMin, int speedMax, int hitLevel,
			int hitSlash, int hitPene, int hitBlow, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE SIN_TABLE SET HP = " + hp
					+ ", SPEED_MIN = " + speedMin
					+ ", SPEED_MAX = " + speedMax
					+ ", HIT_LEVEL = " + hitLevel
					+ ", HIT_SLASH = " + hitSlash
					+ ", HIT_PENE = " + hitPene
					+ ", HIT_BLOW = " + hitBlow
					+ "WHERE IDEN_NO = " + idenNo;
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
			if(result > 0 ) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			return result;
		}
	}
	
	public int identityFirstEdit(int idenNo, String firName, int firSin, int firType, int firLevel,
			int firCoinMany, int firBase, int firCoin, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE SIN_TABLE SET FIR_NAME = '" + firName
					+ "', FIR_SIN = " + firSin
					+ ", FIR_TYPE = " + firType
					+ ", FIR_LEVEL = " + firLevel
					+ ", FIR_COIN_MANY = " + firCoinMany
					+ ", FIR_BASE = " + firBase
					+ ", FIR_COIN = " + firCoin
					+ "WHERE IDEN_NO = " + idenNo;
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
			if(result > 0 ) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			return result;
		}
	}

	public int identitySecondEdit(int idenNo, String secName, int secSin, int secType, int secLevel,
			int secCoinMany, int secBase, int secCoin, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE SIN_TABLE SET SEC_NAME = '" + secName
					+ "', SEC_SIN = " + secSin
					+ ", SEC_TYPE = " + secType
					+ ", SEC_LEVEL = " + secLevel
					+ ", SEC_COIN_MANY = " + secCoinMany
					+ ", SEC_BASE = " + secBase
					+ ", SEC_COIN = " + secCoin
					+ "WHERE IDEN_NO = " + idenNo;
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
			if(result > 0 ) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			return result;
		}
	}
	
	public int identityThirdEdit(int idenNo, String thiName, int thiSin, int thiType, int thiLevel,
			int thiCoinMany, int thiBase, int thiCoin, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE SIN_TABLE SET THI_NAME = '" + thiName
					+ "', THI_SIN = " + thiSin
					+ ", THI_TYPE = " + thiType
					+ ", THI_LEVEL = " + thiLevel
					+ ", THI_COIN_MANY = " + thiCoinMany
					+ ", THI_BASE = " + thiBase
					+ ", THI_COIN = " + thiCoin
					+ "WHERE IDEN_NO = " + idenNo;
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
			if(result > 0 ) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			return result;
		}
	}
	
	public int identityDefenceEdit(int idenNo, String defName, int defSin, int defType, int defLevel,
			int defCoinMany, int defBase, int defCoin, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE SIN_TABLE SET DEF_NAME = '" + defName
					+ "', DEF_SIN = " + defSin
					+ ", DEF_TYPE = " + defType
					+ ", DEF_LEVEL = " + defLevel
					+ ", DEF_COIN_MANY = " + defCoinMany
					+ ", DEF_BASE = " + defBase
					+ ", DEF_COIN = " + defCoin
					+ "WHERE IDEN_NO = " + idenNo;
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
			if(result > 0 ) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			return result;
		}
	}
	
	public int identityPassiveEdit(int idenNo, String pasName, int pasKind, int pasSin, int pasSinSec,
			int pasType, int pasMany, int pasManySec,
			String supName, int supKind, int supSin, int supSinSec,
			int supType, int supMany, int supManySec, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE SIN_TABLE SET PAS_NAME = '" + pasName
					+ "', PAS_KIND = " + pasKind
					+ ", PAS_SIN = " + pasSin;
		if(pasKind==2) {sql = sql + ", PAS_SIN_SEC = " + pasSinSec;}
		else {sql = sql + ", PAS_SIN_SEC = NULL";}
		sql = sql + ", PAS_TYPE = " + pasType
					+ ", PAS_MANY = " + pasMany;
		if(pasKind==2) {sql = sql + ", PAS_MANY_SEC = " + pasManySec;}
		else {sql = sql + ", PAS_MANY_SEC = NULL";}
		sql = sql + ", SUP_NAME = '" + supName
					+ "', SUP_KIND = " + supKind
					+ ", SUP_SIN = " + supSin;
		if(supKind==2) {sql = sql + ", SUP_SIN_SEC = " + supSinSec;}
		else {sql = sql + ", SUP_SIN_SEC = NULL";}
		sql = sql	+ ", SUP_TYPE = " + supType
					+ ", SUP_MANY = " + supMany;
		if(supKind==2) {sql = sql + ", SUP_MANY_SEC = " + supManySec;}
		else {sql = sql + ", SUP_MANY_SEC = NULL";}
		
		sql = sql + " WHERE IDEN_NO = " + idenNo;
		
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
			if(result > 0 ) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			return result;
		}
	}
	
	public int identityAdd(Identity i, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO SIN_TABLE VALUES("
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
					+ "?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, i.getIdenNo());
			pstmt.setInt(2, i.getCharNo());
			pstmt.setString(3, i.getIdenName());
			pstmt.setInt(4, i.getSeason());
			pstmt.setInt(5, i.getRarity());
			pstmt.setInt(6, i.getHp());
			pstmt.setInt(7, i.getSpeedMin());
			pstmt.setInt(8, i.getSpeedMax());
			pstmt.setInt(9, i.getHitLevel());
			pstmt.setInt(10, i.getHitSlash());
			pstmt.setInt(11, i.getHitPene());
			pstmt.setInt(12, i.getHitBlow());
			pstmt.setString(13, i.getFirName());
			pstmt.setInt(14, i.getFirSin());
			pstmt.setInt(15, i.getFirType());
			pstmt.setInt(16, i.getFirLevel());
			pstmt.setInt(17, i.getFirCoinMany());
			pstmt.setInt(18, i.getFirBase());
			pstmt.setInt(19, i.getFirCoin());
			pstmt.setString(20, i.getSecName());
			pstmt.setInt(21, i.getSecSin());
			pstmt.setInt(22, i.getSecType());
			pstmt.setInt(23, i.getSecLevel());
			pstmt.setInt(24, i.getSecCoinMany());
			pstmt.setInt(25, i.getSecBase());
			pstmt.setInt(26, i.getSecCoin());
			pstmt.setString(27, i.getThiName());
			pstmt.setInt(28, i.getThiSin());
			pstmt.setInt(29, i.getThiType());
			pstmt.setInt(30, i.getThiLevel());
			pstmt.setInt(31, i.getThiCoinMany());
			pstmt.setInt(32, i.getThiBase());
			pstmt.setInt(33, i.getThiCoin());
			pstmt.setString(34, i.getDefName());
			pstmt.setInt(35, i.getDefSin());
			pstmt.setInt(36, i.getDefType());
			pstmt.setInt(37, i.getDefLevel());
			pstmt.setInt(38, i.getDefCoinMany());
			pstmt.setInt(39, i.getDefBase());
			pstmt.setInt(40, i.getDefCoin());
			
			pstmt.setString(41, i.getPasName());
			pstmt.setInt(42, i.getPasKind());
			pstmt.setInt(43, i.getPasSin());
			pstmt.setInt(45, i.getPasType());
			pstmt.setInt(46, i.getPasMany());
			if(i.getPasKind()==2) {
				pstmt.setInt(44, i.getPasSinSec());
				pstmt.setInt(47, i.getPasManySec());
			} else {
				pstmt.setNull(44, java.sql.Types.INTEGER);
				pstmt.setNull(47, java.sql.Types.INTEGER);
			}
			
			pstmt.setString(48, i.getSupName());
			pstmt.setInt(49, i.getSupKind());
			pstmt.setInt(50, i.getSupSin());
			pstmt.setInt(52, i.getSupType());
			pstmt.setInt(53, i.getSupMany());
			if(i.getSupKind()==2) {
				pstmt.setInt(51, i.getSupSinSec());
				pstmt.setInt(54, i.getSupManySec());
			} else {
				pstmt.setNull(51, java.sql.Types.INTEGER);
				pstmt.setNull(54, java.sql.Types.INTEGER);
			}
			
			pstmt.setString(55, i.getRelation());
			pstmt.setString(56, i.getKeyword());
			
			result = pstmt.executeUpdate();
			
			if(result > 0 ) {
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			return result;
		}
	}
}
