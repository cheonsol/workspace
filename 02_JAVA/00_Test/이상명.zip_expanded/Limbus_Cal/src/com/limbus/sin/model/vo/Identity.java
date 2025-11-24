package com.limbus.sin.model.vo;

import static com.limbus.sin.model.vo.ValueReturner.*;

public class Identity {
	private int idenNo;
	private int charNo;
	private String idenName;
	private int season;
	private int rarity;
	
	private int hp;
	private int speedMin;
	private int speedMax;
	private int hitLevel;
	private int hitSlash;
	private int hitPene;
	private int hitBlow;
	
	private String firName;
	private int firSin;
	private int firType;
	private int firLevel;
	private int firCoinMany;
	private int firBase;
	private int firCoin;
	
	private String secName;
	private int secSin;
	private int secType;
	private int secLevel;
	private int secCoinMany;
	private int secBase;
	private int secCoin;
	
	private String thiName;
	private int thiSin;
	private int thiType;
	private int thiLevel;
	private int thiCoinMany;
	private int thiBase;
	private int thiCoin;
	
	private String defName;
	private int defSin;
	private int defType;
	private int defLevel;
	private int defCoinMany;
	private int defBase;
	private int defCoin;
	
	private String pasName;
	private int pasKind;
	private int pasSin;
	private int pasSinSec;
	private int pasType;
	private int pasMany;
	private int pasManySec;
	
	private String supName;
	private int supKind;
	private int supSin;
	private int supSinSec;
	private int supType;
	private int supMany;
	private int supManySec;
	
	private String relation;
	
	private String keyword;
	
	public int getIdenNo() {return idenNo;}
	public void setIdenNo(int idenNo) {this.idenNo = idenNo;}
	public int getCharNo() {return charNo;}
	public void setCharNo(int charNo) {this.charNo = charNo;}
	public String getIdenName() {return idenName;}
	public void setIdenName(String idenName) {this.idenName = idenName;}
	public int getSeason() {return season;}
	public void setSeason(int season) {this.season = season;}
	public int getRarity() {return rarity;}
	public void setRarity(int rarity) {this.rarity = rarity;}
	public int getHp() {return hp;}
	public void setHp(int hp) {this.hp = hp;}
	public int getSpeedMin() {return speedMin;}
	public void setSpeedMin(int speedMin) {this.speedMin = speedMin;}
	public int getSpeedMax() {return speedMax;}
	public void setSpeedMax(int speedMax) {this.speedMax = speedMax;}
	public int getHitLevel() {return hitLevel;}
	public void setHitLevel(int hitLevel) {this.hitLevel = hitLevel;}
	public int getHitSlash() {return hitSlash;}
	public void setHitSlash(int hitSlash) {this.hitSlash = hitSlash;}
	public int getHitPene() {return hitPene;}
	public void setHitPene(int hitPene) {this.hitPene = hitPene;}
	public int getHitBlow() {return hitBlow;}
	public void setHitBlow(int hitBlow) {this.hitBlow = hitBlow;}
	public String getFirName() {return firName;}
	public void setFirName(String firName) {this.firName = firName;}
	public int getFirSin() {return firSin;}
	public void setFirSin(int firSin) {this.firSin = firSin;}
	public int getFirType() {return firType;}
	public void setFirType(int firType) {this.firType = firType;}
	public int getFirLevel() {return firLevel;}
	public void setFirLevel(int firLevel) {this.firLevel = firLevel;}
	public int getFirCoinMany() {return firCoinMany;}
	public void setFirCoinMany(int firCoinMany) {this.firCoinMany = firCoinMany;}
	public int getFirBase() {return firBase;}
	public void setFirBase(int firBase) {this.firBase = firBase;}
	public int getFirCoin() {return firCoin;}
	public void setFirCoin(int firCoin) {this.firCoin = firCoin;}
	public String getSecName() {return secName;}
	public void setSecName(String secName) {this.secName = secName;}
	public int getSecSin() {return secSin;}
	public void setSecSin(int secSin) {this.secSin = secSin;}
	public int getSecType() {return secType;}
	public void setSecType(int secType) {this.secType = secType;}
	public int getSecLevel() {return secLevel;}
	public void setSecLevel(int secLevel) {this.secLevel = secLevel;}
	public int getSecCoinMany() {return secCoinMany;}
	public void setSecCoinMany(int secCoinMany) {this.secCoinMany = secCoinMany;}
	public int getSecBase() {return secBase;}
	public void setSecBase(int secBase) {this.secBase = secBase;}
	public int getSecCoin() {return secCoin;}
	public void setSecCoin(int secCoin) {this.secCoin = secCoin;}
	public String getThiName() {return thiName;}
	public void setThiName(String thiName) {this.thiName = thiName;}
	public int getThiSin() {return thiSin;}
	public void setThiSin(int thiSin) {this.thiSin = thiSin;}
	public int getThiType() {return thiType;}
	public void setThiType(int thiType) {this.thiType = thiType;}
	public int getThiLevel() {return thiLevel;}
	public void setThiLevel(int thiLevel) {this.thiLevel = thiLevel;}
	public int getThiCoinMany() {return thiCoinMany;}
	public void setThiCoinMany(int thiCoinMany) {this.thiCoinMany = thiCoinMany;}
	public int getThiBase() {return thiBase;}
	public void setThiBase(int thiBase) {this.thiBase = thiBase;}
	public int getThiCoin() {return thiCoin;}
	public void setThiCoin(int thiCoin) {this.thiCoin = thiCoin;}
	public String getDefName() {return defName;}
	public void setDefName(String defName) {this.defName = defName;}
	public int getDefSin() {return defSin;}
	public void setDefSin(int defSin) {this.defSin = defSin;}
	public int getDefType() {return defType;}
	public void setDefType(int defType) {this.defType = defType;}
	public int getDefLevel() {return defLevel;}
	public void setDefLevel(int defLevel) {this.defLevel = defLevel;}
	public int getDefCoinMany() {return defCoinMany;}
	public void setDefCoinMany(int defCoinMany) {this.defCoinMany = defCoinMany;}
	public int getDefBase() {return defBase;}
	public void setDefBase(int defBase) {this.defBase = defBase;}
	public int getDefCoin() {return defCoin;}
	public void setDefCoin(int defCoin) {this.defCoin = defCoin;}
	public String getPasName() {return pasName;}
	public void setPasName(String pasName) {this.pasName = pasName;}
	public int getPasKind() {return pasKind;}
	public void setPasKind(int pasKind) {this.pasKind = pasKind;}
	public int getPasSin() {return pasSin;}
	public void setPasSin(int pasSin) {this.pasSin = pasSin;}
	public int getPasSinSec() {return pasSinSec;}
	public void setPasSinSec(int pasSinSec) {this.pasSinSec = pasSinSec;}
	public int getPasType() {return pasType;}
	public void setPasType(int pasType) {this.pasType = pasType;}
	public int getPasMany() {return pasMany;}
	public void setPasMany(int pasMany) {this.pasMany = pasMany;}
	public int getPasManySec() {return pasManySec;}
	public void setPasManySec(int pasManySec) {this.pasManySec = pasManySec;}
	public String getSupName() {return supName;}
	public void setSupName(String supName) {this.supName = supName;}
	public int getSupKind() {return supKind;}
	public void setSupKind(int supKind) {this.supKind = supKind;}
	public int getSupSin() {return supSin;}
	public void setSupSin(int supSin) {this.supSin = supSin;}
	public int getSupSinSec() {return supSinSec;}
	public void setSupSinSec(int supSinSec) {this.supSinSec = supSinSec;}
	public int getSupType() {return supType;}
	public void setSupType(int supType) {this.supType = supType;}
	public int getSupMany() {return supMany;}
	public void setSupMany(int supMany) {this.supMany = supMany;}
	public int getSupManySec() {return supManySec;}
	public void setSupManySec(int supManySec) {this.supManySec = supManySec;}
	public String getRelation() {return relation;}
	public void setRelation (String relation) {this.relation = relation;}
	public String getKeyword() {return keyword;}
	public void setKeyword(String keyword) {this.keyword = keyword;}

	public Identity() {super();}
	public Identity(int idenNo, int charNo, String idenName, int season, int rarity, int hp, int speedMin, int speedMax,
			int hitLevel, int hitSlash, int hitPene, int hitBlow, String firName, int firSin, int firType, int firLevel,
			int firCoinMany, int firBase, int firCoin, String secName, int secSin, int secType, int secLevel,
			int secCoinMany, int secBase, int secCoin, String thiName, int thiSin, int thiType, int thiLevel,
			int thiCoinMany, int thiBase, int thiCoin, String defName, int defSin, int defType, int defLevel,
			int defCoinMany, int defBase, int defCoin, String pasName, int pasKind, int pasSin, int pasSinSec,
			int pasType, int pasMany, int pasManySec, String supName, int supKind, int supSin, int supSinSec,
			int supType, int supMany, int supManySec, String relation,
			String keyword) {
		super();
		this.idenNo = idenNo;
		this.charNo = charNo;
		this.idenName = idenName;
		this.season = season;
		this.rarity = rarity;
		this.hp = hp;
		this.speedMin = speedMin;
		this.speedMax = speedMax;
		this.hitLevel = hitLevel;
		this.hitSlash = hitSlash;
		this.hitPene = hitPene;
		this.hitBlow = hitBlow;
		this.firName = firName;
		this.firSin = firSin;
		this.firType = firType;
		this.firLevel = firLevel;
		this.firCoinMany = firCoinMany;
		this.firBase = firBase;
		this.firCoin = firCoin;
		this.secName = secName;
		this.secSin = secSin;
		this.secType = secType;
		this.secLevel = secLevel;
		this.secCoinMany = secCoinMany;
		this.secBase = secBase;
		this.secCoin = secCoin;
		this.thiName = thiName;
		this.thiSin = thiSin;
		this.thiType = thiType;
		this.thiLevel = thiLevel;
		this.thiCoinMany = thiCoinMany;
		this.thiBase = thiBase;
		this.thiCoin = thiCoin;
		this.defName = defName;
		this.defSin = defSin;
		this.defType = defType;
		this.defLevel = defLevel;
		this.defCoinMany = defCoinMany;
		this.defBase = defBase;
		this.defCoin = defCoin;
		this.pasName = pasName;
		this.pasKind = pasKind;
		this.pasSin = pasSin;
		this.pasSinSec = pasSinSec;
		this.pasType = pasType;
		this.pasMany = pasMany;
		this.pasManySec = pasManySec;
		this.supName = supName;
		this.supKind = supKind;
		this.supSin = supSin;
		this.supSinSec = supSinSec;
		this.supType = supType;
		this.supMany = supMany;
		this.supManySec = supManySec;
		this.relation = relation;
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		String pas=null;
		if(pasKind==1) {
			pas = passiveReturn(pasType) + ": " + sinReturn(pasSin) + " " + pasMany;
		} else if(pasKind==2) {
			pas = passiveReturn(pasType) + ": " + sinReturn(pasSin) + " " + pasMany + " , " + sinReturn(pasSinSec) + " " + pasManySec; 
		}
		
		String sup=null;
		if(supKind==1) {
			sup = passiveReturn(supType) + ": " + sinReturn(supSin) + " " + supMany;
		} else if(supKind==2) {
			sup = passiveReturn(supType) + ": " + sinReturn(supSin) + " " + supMany + " , " + sinReturn(supSinSec) + " " + supManySec; 
		}
		
		return "No." + idenNo + " [" + idenName + " " + charReturn(charNo) + "] (" + seasonReturn(season) + " / " + rarity +"성)"
				+ "체력:" + hp + "   속도:" + speedMin + "~" + speedMax + "   방어레벨보정:" + hitLevel +"\n"
				+ "[내성] 참격:" + damageReturn(hitSlash) + "   관통:" + damageReturn(hitPene) + "   타격:" + damageReturn(hitBlow) + "\n"
				+ "[1스킬: " + firName+ "] " + sinReturn(firSin) + " / " + attackReturn(firType) + " / 공격레벨보정:" + firLevel + " "
				+ "(" + firCoinMany + "코인) / 기본위력:" + firBase + " / 코인위력:" + firCoin 
				+ " (합위력:" + firBase + "~" +(firBase+firCoin*firCoinMany) + " / 피해량:" + (firBase*firCoinMany) + "~" + (firBase*firCoinMany + firCoin*pactorialReturn(firCoinMany))
				+ " / 밸류:" + valueReturn(firLevel, firBase, firCoinMany, firCoin) +")\n"
				+ "[2스킬: " + secName + "] " + sinReturn(secSin) + " / " + attackReturn(secType) + " / 공격레벨보정:" + secLevel + " "
				+ "(" + secCoinMany + "코인) / 기본위력:" + secBase + " / 코인위력:" + secCoin 
				+ " (합위력:" + secBase + "~" +(secBase+secCoin*secCoinMany) + " / 피해량:" + (secBase*secCoinMany) + "~" + (secBase*secCoinMany + secCoin*pactorialReturn(secCoinMany))
				+ " / 밸류:" + valueReturn(secLevel, secBase, secCoinMany, secCoin) +")\n"
				+ "[3스킬: " + thiName + "] " + sinReturn(thiSin) + " / " + attackReturn(thiType) + " / 공격레벨보정:" + thiLevel + " "
				+ "(" + thiCoinMany + "코인) / 기본위력:" + thiBase + " / 코인위력:" + thiCoin 
				+ " (합위력:" + thiBase + "~" +(thiBase+thiCoin*thiCoinMany) + " / 피해량:" + (thiBase*thiCoinMany) + "~" + (thiBase*thiCoinMany + thiCoin*pactorialReturn(thiCoinMany))
				+ " / 밸류:" + valueReturn(thiLevel, thiBase, thiCoinMany, thiCoin) +")\n"
				+ "[수비스킬: " + defName + "] " + sinReturn(defSin) + " / 코인위력:" + defenceReturn(defType) + " / 방어레벨보정:" + defLevel + " "
				+ "(" + defCoinMany + "코인) / 기본위력:" + defBase + " / 코인위력:" + defCoin
				+ " (총위력:" + defBase + "~" + (defBase + defCoin*(defCoinMany))
				+ " / 밸류:" + valueReturn(defLevel, defBase, defCoinMany, defCoin) + ")\n"
				+ "[패시브: " + pasName + "] " + pas + "\n"
				+ "[서포트 패시브: " + supName + "] " + sup + "\n"
				+ "[소속] " + relation + "\n"
				+ "[키워드] " + keyword;
		
	}

	
}
