package com.kh.practice.controll;

import java.util.Scanner;

public class Practice3 {
	/*
	 * 
	 	어린이, 청소년, 성인의 구분에 따라 입장료가 다르게 부과되는 *놀이공원 요금 계산기*를 만들어보세요.
		또한, 주말에는 20% 할인이 적용됩니다.
		
		#요금표
		| 구분   | 나이 범위 | 기본 요금 |
		| ---   | ---     | ---     |
		| 어린이  | 0 ~ 12세| 5,000원 |
		| 청소년  |13 ~ 18세| 7,000원 |
		| 성인   | 19세 이상| 10,000원 |
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int age;
		String day;
		
		System.out.print("나이를 입력하세요 : ");
		age = sc.nextInt();
		sc.nextLine();
		
		System.out.print("요일을 입력하세요 : ");
		day = sc.nextLine();
		
		int fee = 0;
		String kind = "";
		
		if(age <= 12) {
			fee = 5000;
			kind = "어린이";
		} else if(age <= 18) {
			fee = 7000;
			kind = "청소년";
		} else {
			fee = 10000;
			kind = "성인";
		}
		
		if(day.equals("토") || day.equals("일")) {
			fee = (int)(fee * 0.8);
			System.out.println(kind + " 요금입니다. (주말 할인 적용)");
		} else {
			System.out.println(kind + " 요금입니다.");
		}
		
		System.out.println("최종 요금은 " + fee + "원입니다.");
		sc.close();
	}

}
