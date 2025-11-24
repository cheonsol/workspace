package com.limbus.sin.model.vo;

public class ValueReturner {
	public static String charReturn(int n) {
		switch(n) {
		case 1:
			return "이상";
		case 2:
			return "파우스트";
		case 3:
			return "돈키호테";
		case 4:
			return "료슈";
		case 5:
			return "뫼르소";
		case 6:
			return "홍루";
		case 7:
			return "히스클리프";
		case 8:
			return "이스마엘";
		case 9:
			return "로쟈";
		case 11:
			return "싱클레어";
		case 12:
			return "오티스";
		case 13:
			return "그레고르";
		default:
			return "[오류 : 존재하지않는 수감자입니다.]";
		
		
		}
	}
	
	public static String sinReturn(int n) {
		switch(n) {
		case 1:
			return "\u001B[41;97m분노\u001B[0m";
		case 2:
			return "\u001B[43;97m색욕\u001B[0m";
		case 3:
			return "\u001B[103;30m나태\u001B[0m";
		case 4:
			return "\u001B[42;97m탐식\u001B[0m";
		case 5:
			return "\u001B[106;30m우울\u001B[0m";
		case 6:
			return "\u001B[44;97m오만\u001B[0m";
		case 7:
			return "\u001B[45;97m질투\u001B[0m";
		default:
			return "[오류 : 존재하지않는 죄악입니다.]";
		}
	}

	public static String attackReturn(int n) {
		switch(n) {
		case 1:
			return "참격";
		case 2:
			return "관통";
		case 3:
			return "타격";
		default:
			return "[오류 : 존재하지않는 공격입니다.]";
		}
	}
	
	public static String damageReturn(int n) {
		switch(n) {
		case 2:
			return "취약";
		case 1:
			return "약점";
		case 0:
			return "보통";
		case -1:
			return "내성";
		default:
			return "[오류 : 존재하지않는 내성입니다.]";
		}
	}
	
	public static String defenceReturn(int n) {
		switch(n) {
		case 1:
			return "방어";
		case 2:
			return "회피";
		case 3:
			return "반격";
		case 4:
			return "강화방어";
		case 5:
			return "강화반격";
		default:
			return "[오류 : 존재하지않는 수비스킬입니다.]";
		}
	}
	
	public static String passiveReturn(int n) {
		switch(n) {
		case 1:
			return "보유";
		case 2:
			return "공명";
		default:
			return "[오류 : 존재하지않는 패시브 종류입니다.]";
		}
	}
	
	public static String seasonReturn(int n) {
		switch(n) {
		case 0:
			return "통상";
		case 1:
			return "\u001B[30;41mseason1, Orientation\u001B[0m";
		case 2:
			return "\u001B[36;103mseason2, Reminiscence\u001B[0m";
		case 3:
			return "\u001B[37;44mseason3, Bon Voyage\u001B[0m";
		case 4:
			return "\u001B[95;40mseason4, Clear All Cathy\u001B[0m";
		case 5:
			return "\u001B[31;43mseason5, Oblivion\u001B[0m";
		case 6:
			return "\u001B[92;47mseason6, Zàng Huā Yín\u001B[0m";
		case 99:
			return "\u001B[37;42mWalpurgis\u001B[30mnacht\u001B[0m";
		default:
			return "[오류 : 존재하지않는 시즌입니다.]";
		}
	}
	
	public static int pactorialReturn(int n) {
		int result=0;
		for(int i=1; i<=n; i++) {
			result = result+i;
		}
		return result;
	}
	
	public static double valueReturn(int level, int base, int coinMany, int coin) {
		double valueLevel = level;
		double valueBout = 0;
		if(coin > 0) {
			valueBout = base + coinMany*coin;
		} else {
			valueBout = base + coin;
		}
		double valueDamage = (pactorialReturn(coinMany)*coin) + base*coinMany - valueBout;
		
		double result = valueLevel/2 + valueBout + Math.sqrt(valueDamage);
		result = Math.round(result*100)/100.0;
		return result;
	}
}
