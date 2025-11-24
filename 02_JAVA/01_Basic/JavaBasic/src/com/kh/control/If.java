package com.kh.control;

import java.util.Scanner;

public class If {
	/*
	 * 기본적으로 프로그램의 진행은 순차적으로 이루어진다.
	 * 
	 * 특정 코드를 선택적으로 실행시키고 싶을 때 -> 조건문
	 * 특정 코드를 반복해서 실행시키고 싶을 때 -> 반복문
	 * 
	 * *조건문
	 * "조건식"을 통해 true 또는 false를 판단하고 thue일 경우 그에 해당하는 코드를 실행.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/*
		 * if문을 단독으로 사용하는 경우
		 * if(조건식) {
		 * 		...조건에따라 실행할 구문
		 * }
		 * 
		 * 조건식이 true -> 중괄호 블럭안의 코드를 실행
		 * 조건식이 false -> 중괄호 블럭안의 코드를 무시하고 넘어감.
		 * */
		
		System.out.print("정수 입력 : ");
		int num = sc.nextInt();
		
		if(num > 0) {
			System.out.println("양수입니다.");
		}
		
		if(num == 0) {
			System.out.println("0입니다.");
		}
		
		if(num < 0) {
			System.out.println("음수입니다.");
		}
		
		/*
		 * if와 else를 함께 사용하는 경우
		 * 
		 * if(조건식){
		 * 		실행할코드
		 * } else {
		 * 		실행할코드
		 * }
		 * 
		 * 조건식이 true -> 중괄호 블럭안의 코드를 실행
		 * 조건식이 false -> else 중괄호 블럭안의 코드를 실행.
		 * */
		
		if(num > 0) {
			System.out.println("양수입니다.");
		} else {
			if(num == 0) {
				System.out.println("0입니다.");
			} else {
				System.out.println("음수입니다.");
			}
		}
		
		/*
		 * 조건을 나열하는 경우 if ~ else if ~ [else]
		 * if(조건식){
		 * 		실행할코드
		 * } else if(조건식){
		 * 		실행할코드
		 * }
		 * 
		 * 각 if문에 들어있는 다른 조건을 확인하며 
		 * 블록안의 코드를 실행할 수 있고 모든 조건이 연동되어 있음
		 */
		if(num > 0) {
			System.out.println("양수입니다.");
		} else if(num == 0) {
			System.out.println("0입니다.");
		} else {
			System.out.println("음수입니다.");
		}
		
	}
}
