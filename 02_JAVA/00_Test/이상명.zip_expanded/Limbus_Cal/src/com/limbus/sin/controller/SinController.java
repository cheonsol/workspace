package com.limbus.sin.controller;

import java.util.List;

import com.limbus.sin.model.vo.Identity;
import static com.limbus.sin.model.vo.ValueReturner.*;
import com.limbus.sin.service.SinService;

public class SinController {
	SinService ss = new SinService();
	
	public void sinPrintAll(){
		List<Identity> list = ss.selectSinList();
		
		if(list.isEmpty()) {
			System.out.println("조회결과 없음");
		}else {
			for(Identity i : list) {
				System.out.println(i + "\n");
			}
		}
	}
	
	public void deckPrintAll() {
		List<Identity> list = ss.selectDeckList();
		
		if(list.isEmpty()) {
			System.out.println("덱을 불러오지 못했습니다.");
		} else {
			for(Identity i : list) {
				System.out.println(" (" + i.getCharNo() + ") " + charReturn(i.getCharNo())+ " " + "\t" + i.getIdenNo() + " [" + i.getIdenName() + " "+ charReturn(i.getCharNo())+"]");
			}
		}
	}
	
	public void IdentityList() {
		List<Identity> list = ss.selectSinList();
		
		if(list.isEmpty()) {
			System.out.println("덱을 불러오지 못했습니다.");
		} else {
			for(Identity i : list) {
				System.out.println(i.getIdenNo() + " [" + i.getIdenName() + " " + charReturn(i.getCharNo()) + "]");
			}
		}
	}
	
	public int IdentityView(int idenNo) {
		List<Identity> list = ss.identityView(idenNo);
		
		if(list.isEmpty()) {
			System.out.println("조회결과 없음");
			return 0;
		} else {
			for(Identity i : list) {
				System.out.println(i.toString());
			}
			return 1;
		}
	}
	
	public void deckPrintAllParticular() {
		List<Identity> list = ss.selectDeckList();
		
		if(list.isEmpty()) {
			System.out.println("덱을 불러오지 못했습니다.");
		} else {
			for(Identity i : list) {
				System.out.println(i.toString() + "\n");
			}
		}
	}
	
	public void charList(int n) {
		List<Identity> list = ss.charList(n);
		
		if(list.isEmpty()) {
			System.out.println("조회결과 없음");
		}else {
			for(Identity i : list) {
				System.out.println(i.getIdenNo() + " [" + i.getIdenName() + " " + charReturn(i.getCharNo()) + "]");
			}
		}
	}
	
	public int charCount(int charNo, int season, int rarity) {
		int count = ss.charCount(charNo);
		String cn = charNo + "";
		String ss = null;
		if(season/10<1) {
			ss = "0"+season;
		} else {
			ss = ""+season;
		}
		String rr = rarity + "";
		String ct = null;
		if(count/10<1) {
			ct = "0"+count;
		} else {
			ct = "" + count;
		}
		
		String CC = cn+ss+rr+ct;
		
		return Integer.parseInt(CC);
		
	}
	
	public void identityChange(int charNo, int idenNo) {
		int result = ss.identityChange(charNo, idenNo);
		if(result > 0) {
			System.out.println("성공적으로 인격패가 교체되었습니다.");
		} else {
			System.out.println("인격패 교체에 실패하였습니다.");
		}
	}
	
	public void identityNameEdit(int idenNo, String idenName) {
		int result = ss.identityNameEdit(idenNo, idenName);
		if(result > 0) {
			System.out.println("성공적으로 인격명이 교체되었습니다.");
		} else {
			System.out.println("인격명 교체에 실패하였습니다.");
		}
	}
	
	public void identityStateEdit(int idenNo, int hp, int speedMin, int speedMax, int hitLevel,
									int hitSlash, int hitPene, int hitBlow) {
		int result = ss.identityStateEdit(idenNo, hp, speedMin, speedMax, hitLevel, hitSlash, hitPene, hitBlow);
		if(result > 0) {
			System.out.println("성공적으로 인격정보가 교체되었습니다.");
		} else {
			System.out.println("인격정보 교체에 실패하였습니다.");
		}
	}
	
	public void identityFirstEdit(int idenNo, String firName, int firSin, int firType, int firLevel,
									int firCoinMany, int firBase, int firCoin) {
		int result = ss.identityFirstEdit(idenNo, firName, firSin, firType, firLevel, firCoinMany, firBase, firCoin);
		if(result > 0) {
			System.out.println("성공적으로 스킬정보가 교체되었습니다.");
		} else {
			System.out.println("스킬정보 교체에 실패하였습니다.");
		}
	}
	
	public void identitySecondEdit(int idenNo, String secName, int secSin, int secType, int secLevel,
			int secCoinMany, int secBase, int secCoin) {
		int result = ss.identitySecondEdit(idenNo, secName, secSin, secType, secLevel, secCoinMany, secBase, secCoin);
		if(result > 0) {
			System.out.println("성공적으로 스킬정보가 교체되었습니다.");
		} else {
			System.out.println("스킬정보 교체에 실패하였습니다.");
		}
	}
	
	public void identityThirdEdit(int idenNo, String thiName, int thiSin, int thiType, int thiLevel,
			int thiCoinMany, int thiBase, int thiCoin) {
		int result = ss.identityThirdEdit(idenNo, thiName, thiSin, thiType, thiLevel, thiCoinMany, thiBase, thiCoin);
		if(result > 0) {
			System.out.println("성공적으로 스킬정보가 교체되었습니다.");
		} else {
			System.out.println("스킬정보 교체에 실패하였습니다.");
		}
	}
	
	public void identityDefenceEdit(int idenNo, String defName, int defSin, int defType, int defLevel,
			int defCoinMany, int defBase, int defCoin) {
		int result = ss.identityDefenceEdit(idenNo, defName, defSin, defType, defLevel, defCoinMany, defBase, defCoin);
		if(result > 0) {
			System.out.println("성공적으로 스킬정보가 교체되었습니다.");
		} else {
			System.out.println("스킬정보 교체에 실패하였습니다.");
		}
	}
	
	public void identityPassiveEdit(int idenNo, String pasName, int pasKind, int pasSin, int pasSinSec,
									int pasType, int pasMany, int pasManySec,
									String supName, int supKind, int supSin, int supSinSec,
									int supType, int supMany, int supManySec)  {
		int result = ss.identityPassiveEdit(idenNo, pasName, pasKind, pasSin, pasSinSec, pasType, pasMany, pasManySec, supName, supKind, supSin, supSinSec, supType, supMany, supManySec);
		if(result > 0) {
			System.out.println("성공적으로 패시브정보가 교체되었습니다.");
		} else {
			System.out.println("패시브정보 교체에 실패하였습니다.");
		}
	}
	
	public void identityRelationEdit(int idenNo, String relation)  {
		int result = ss.identityRelationEdit(idenNo, relation);
		if(result > 0) {
			System.out.println("성공적으로 소속정보가 교체되었습니다.");
		} else {
			System.out.println("패시브정보 교체에 실패하였습니다.");
		}
	}
	
	public void identityKeywordEdit(int idenNo, String keyword)  {
		int result = ss.identityKeywordEdit(idenNo, keyword);
		if(result > 0) {
			System.out.println("성공적으로 소속정보가 교체되었습니다.");
		} else {
			System.out.println("패시브정보 교체에 실패하였습니다.");
		}
	}
	
	public void deckData(int[] form) {
		List<Identity> list = ss.selectDeckList();
		double[] SinValueStack = {0,0,0,0,0,0,0};
		String[] keywordStack = new String[20];
		int keywordTop = 0;
		
		Identity i=null;
		for(int n : form) {
			int charNo =0;
			if(n>9) {charNo = n-2;} else {charNo = n-1;}
			i = list.get(charNo);
			double firSkill = valueReturn(i.getFirLevel(), i.getFirBase(), i.getFirCoinMany(), i.getFirCoin());
			double secSkill = valueReturn(i.getSecLevel(), i.getSecBase(), i.getSecCoinMany(), i.getSecCoin());
			double thiSkill = valueReturn(i.getThiLevel(), i.getThiBase(), i.getThiCoinMany(), i.getThiCoin());
			System.out.println(" (" + i.getCharNo() + ") " + charReturn(i.getCharNo())+ " " + "\t" + i.getIdenNo() + " [" + i.getIdenName() + " "+ charReturn(i.getCharNo())+"] "
					+ "("+sinReturn(i.getFirSin())+" "+firSkill+ " / " +sinReturn(i.getSecSin())+" "+secSkill+" / "+sinReturn(i.getThiSin())+" "+thiSkill+")");
			SinValueStack[i.getFirSin()-1] = SinValueStack[i.getFirSin()-1] + firSkill*1.7;
			SinValueStack[i.getSecSin()-1] = SinValueStack[i.getSecSin()-1] + secSkill*1.5;
			SinValueStack[i.getThiSin()-1] = SinValueStack[i.getThiSin()-1] + thiSkill*1;
			if(i.getKeyword() != null) {
				String[] keyStack = i.getKeyword().split(", ");
				for(String s : keyStack) {
					keywordStack[keywordTop] = s;
					keywordTop++;
				}
			}
		}
		/*
		System.out.print("\n\n죄악밸류 기댓값 합계(");
		for(int n=0; n<7; n++) {System.out.printf(sinReturn(n+1) + " %.2f%%", SinValueStack[n]);
								if(n!=6) {System.out.print(" / ");} else{System.out.println(")");}}
		*/
		double SinValueSum = 0;
		for(int n=0; n<7; n++) {SinValueSum = SinValueSum+SinValueStack[n];}
		for(int n=0; n<7; n++) {SinValueStack[n] = (SinValueStack[n] / SinValueSum)*100;}
		System.out.print("\n죄악 기대 비율 (");
		for(int n=0; n<7; n++) {System.out.printf(sinReturn(n+1) + " %.0f%%", SinValueStack[n]);
								if(n!=6) {System.out.print(" / ");} else{System.out.println(")");}}
		for(int n=0; n<7; n++) {if(SinValueStack[n]<11) {System.out.printf(sinReturn(n+1) + " ");} }
		System.out.println("자원의 비율이 낮습니다.");
		
		String keywordList = "";
		
		for(int n=0; n<20; n++) {
			int count = 0;
			String thisKeyword;
			if(keywordStack[n] != null) {
				thisKeyword = keywordStack[n];
				for(int m=n; m<20; m++) {
					if(thisKeyword.equals(keywordStack[m])) {
						count++;
						keywordStack[m] = null;
					}
				}
				keywordList = keywordList + thisKeyword + " " + count + " / ";
			}
		}
		
		System.out.println(keywordList);
	}
	
	public void identityAdd(int idenNo, int charNo,
			String idenName, int season, int rarity, int hp, int speedMin,
			int speedMax, int hitLevel, int hitSlash,
			int hitPene, int hitBlow, String firName,
			int firSin, int firType, int firLevel,
			int firCoinMany, int firBase, int firCoin,
			String secName, int secSin, int secType,
			int secLevel, int secCoinMany, int secBase,
			int secCoin, String thiName, int thiSin,
			int thiType, int thiLevel, int thiCoinMany,
			int thiBase, int thiCoin, String defName,
			int defSin, int defType, int defLevel, int defCoinMany,
			int defBase, int defCoin, String pasName, int pasKind,
			int pasSin, int pasSinSec, int pasType, int pasMany,
			int pasManySec, String supName, int supKind, int supSin,
			int supSinSec, int supType, int supMany, int supManySec,
			String relation, String keyword) {
		Identity i = new Identity(idenNo, charNo, idenName, season, rarity, hp, speedMin, speedMax, hitLevel, hitSlash, hitPene, hitBlow, firName, firSin, firType, firLevel, firCoinMany, firBase, firCoin, secName, secSin, secType, secLevel, secCoinMany, secBase, secCoin, thiName, thiSin, thiType, thiLevel, thiCoinMany, thiBase, thiCoin, defName, defSin, defType, defLevel, defCoinMany, defBase, defCoin, pasName, pasKind, pasSin, pasSinSec, pasType, pasMany, pasManySec, supName, supKind, supSin, supSinSec, supType, supMany, supManySec, relation, keyword);
		
		int result = ss.identityAdd(i);
		
		if(result > 0) {
			System.out.println("성공적으로 인격이 추출되었습니다.");
		} else {
			System.out.println("인격 추출에 실패하였습니다.");
		}
	}
}
