package com.kh.operator;

import java.util.Scanner;

public class Operator5 {
	/*
	 * 논리연산자
	 * 두개의 논리값을 연산해주는 연산자
	 * 논리연산자의 결과도 논리값.
	 * 
	 * 논리값 && 논리값 : 왼쪽, 오른쪽의 조건이 모두 true일 경우 결과는 true.
	 * 논리값 || 논리값 : 왼쪽, 오른쪽의 조건중 하나라도 true인 경우 결과는 true.
	 * */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//사용자에게 숫자를 입력받아 해당 숫자가 1~100사이 값인지 확인
		int num;
		System.out.print("숫자 입력 : ");
		num = sc.nextInt();
		
		// 1~100사이값인지 확인
		// 1 <= num && num <= 100;
		boolean result = num >= 1 && num <= 100;
		System.out.println("사용자가 입력한 값은 1이상 100이하이다 : " + result);
		
		//사용자에게 알파벳 하나를 입력받아 대소문자를 확인
		char ch;
		System.out.print("문자 하나 입력 : ");
		ch = sc.next().charAt(0);
		//문자열에서 문자 하나를 추출하는 함수
		//charAt(index)
		
		boolean res1 = (ch >= 'A' && ch <= 'Z'); //true결과가 나오면 대문자
		boolean res2 = (ch >= 'a' && ch <= 'z'); //true결과가 나오면 소문자
		
		//둘다 거짓이면 a~z, A~Z사이에있는 알파벳이아닌 다른 문자
		System.out.println("사용자가 입력한 값은 알파벳이다. : " + (res1 || res2));
		System.out.println("사용자가 입력한 값은 대문자다. : " + (res1));
		System.out.println("사용자가 입력한 값은 소문자다. : " + (res2));
	}

}
