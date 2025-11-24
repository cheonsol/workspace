package com.kh.example.api;

import java.util.StringTokenizer;

public class TokenController {

	public TokenController() {
		super();
	}
	
	//띄어쓰기 제거 후 문자열 반환
	public String afterToken(String str) {
		StringTokenizer st = new StringTokenizer(str, " ");
		
		StringBuilder sb = new StringBuilder();
		while(st.hasMoreTokens()) {
			sb.append(st.nextToken());
		}
		
		return sb.toString();
	}
	
	//첫글자를 대문자로 변경
	public String firstCap(String input) {
		char first = input.toUpperCase().charAt(0);
		return first + input.substring(1);
	}
	
	//특정 문자 갯수 검색
	public int findChar(String input, char one) {
		int count = 0;
		for(int i=0; i<input.length(); i++) {
			if(input.charAt(i) == one) {
				count++;
			}
		}
		
		return count;
	}
	
}






