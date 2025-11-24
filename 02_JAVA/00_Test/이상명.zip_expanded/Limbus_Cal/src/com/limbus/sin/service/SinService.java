package com.limbus.sin.service;

import java.sql.Connection;
import java.util.List;

import com.limbus.sin.dao.SinDao;
import com.limbus.sin.model.vo.Identity;

import static com.limbus.sin.common.JDBCTemplate.*;

public class SinService {
	SinDao sd = null;
	
	public SinService() {
		super();
		this.sd = new SinDao();
	}
	 
	public List<Identity> selectSinList(){
		Connection conn = getConnection();
		
		List<Identity> list = sd.selectSinList(conn);
		close(conn);
		
		return list;
	}
	
	public List<Identity> selectDeckList(){
		Connection conn = getConnection();
		
		List<Identity> list = sd.selectDeckList(conn);
		close(conn);
		
		return list;
	}
	
	public int charCount(int n) {
		Connection conn = getConnection();
		
		int count = sd.charCount(n, conn);
		close(conn);
		
		return count;
	}
	
	public List<Identity> identityView(int idenNo){
		Connection conn = getConnection();
		
		List<Identity> list = sd.identityView(idenNo, conn);
		close(conn);
		
		return list;
	}
	
	public int identityAdd(Identity i) {
		Connection conn = getConnection();
		int result = sd.identityAdd(i, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int identityChange(int charNo, int idenNo) {
		Connection conn = getConnection();
		int result = sd.identityChange(charNo, idenNo, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int identityNameEdit(int idenNo, String idenName) {
		Connection conn = getConnection();
		int result = sd.identityNameEdit(idenNo, idenName, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int identityStateEdit(int idenNo, int hp, int speedMin, int speedMax, int hitLevel,
			int hitSlash, int hitPene, int hitBlow) {
		Connection conn = getConnection();
		int result = sd.identityStateEdit(idenNo, hp, speedMin, speedMax, hitLevel, hitSlash, hitPene, hitBlow, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int identityFirstEdit(int idenNo, String firName, int firSin, int firType, int firLevel,
			int firCoinMany, int firBase, int firCoin) {
		Connection conn = getConnection();
		int result = sd.identityFirstEdit(idenNo, firName, firSin, firType, firLevel, firCoinMany, firBase, firCoin, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int identitySecondEdit(int idenNo, String secName, int secSin, int secType, int secLevel,
			int secCoinMany, int secBase, int secCoin) {
		Connection conn = getConnection();
		int result = sd.identitySecondEdit(idenNo, secName, secSin, secType, secLevel, secCoinMany, secBase, secCoin, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int identityThirdEdit(int idenNo, String thiName, int thiSin, int thiType, int thiLevel,
			int thiCoinMany, int thiBase, int thiCoin) {
		Connection conn = getConnection();
		int result = sd.identityThirdEdit(idenNo, thiName, thiSin, thiType, thiLevel, thiCoinMany, thiBase, thiCoin, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int identityDefenceEdit(int idenNo, String defName, int defSin, int defType, int defLevel,
			int defCoinMany, int defBase, int defCoin) {
		Connection conn = getConnection();
		int result = sd.identityDefenceEdit(idenNo, defName, defSin, defType, defLevel, defCoinMany, defBase, defCoin, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int identityPassiveEdit(int idenNo, String pasName, int pasKind, int pasSin, int pasSinSec,
			int pasType, int pasMany, int pasManySec,
			String supName, int supKind, int supSin, int supSinSec,
			int supType, int supMany, int supManySec) {
		Connection conn = getConnection();
		int result = sd.identityPassiveEdit(idenNo, pasName, pasKind, pasSin, pasSinSec, pasType, pasMany, pasManySec, supName, supKind, supSin, supSinSec, supType, supMany, supManySec, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int identityRelationEdit(int idenNo, String relation) {
		Connection conn = getConnection();
		int result = sd.identityRelationEdit(idenNo, relation, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int identityKeywordEdit(int idenNo, String keyword) {
		Connection conn = getConnection();
		int result = sd.identityKeywordEdit(idenNo, keyword, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public List<Identity> charList(int n) {
		Connection conn = getConnection();
		
		List<Identity> list = sd.charList(n, conn);
		close(conn);
		
		return list;
	}
}
