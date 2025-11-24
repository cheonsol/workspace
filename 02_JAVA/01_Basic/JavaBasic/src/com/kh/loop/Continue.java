package com.kh.loop;

public class Continue {
	/*
	 * continue : 반복문 안에 기술되는 구문
	 * 			  continue; 코드를 실행시 그 뒤에 코드를 실행하지않고 곧바로 다시 반복문의 상단으로 이동
	 * */
	public static void main(String[] args) {
		
		//1부터 50까지의 수중 5의 배수만 출력
		for(int i=1; i<=50; i++) {
			if(i % 5 != 0) {
				continue;
			}
			System.out.print(i + " ");
		}
	}

}
