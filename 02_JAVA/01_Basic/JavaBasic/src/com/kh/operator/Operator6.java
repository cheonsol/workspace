package com.kh.operator;

import java.util.Scanner;

public class Operator6 {
	/*
	 * 삼항연산자
	 * 
	 * [표현식]
	 * 조건식 ? 조건이참일경우 : 조건이거짓일경우
	 * 
	 * 이때, 조건식은 반드시 true또는 false를 반환해야함(논리값).
	 * 주로 비교연산, 논리연산자를 통해 작성.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = (3 < 10) ? 10 : 20;
		
		//입력받은 정수값이 양수인지 아닌지 판별해서 출력.
		//정수값 입력 : 
		//양수 / 음수
		
		System.out.print("정수값 입력 : ");
		int num2 = sc.nextInt();
		
		String result = (num2 == 0) ? "0" : ((num2 > 0) ? "양수" : "음수");
		System.out.println(result);
	}

}
