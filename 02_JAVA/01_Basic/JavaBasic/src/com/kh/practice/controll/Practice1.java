package com.kh.practice.controll;

import java.util.Scanner;

public class Practice1 {
	/*
	 * 0점부터 100점 사이의 정수를 입력받아 
	 * 아래 조건에 따라 등급(학점)을 출력하는 프로그램을 작성하세요.
	 * 90 ~ 100	A
		80 ~ 89	B
		70 ~ 79	C
		60 ~ 69	D
		0 ~ 59	F
		
		[출력]
		점수를 입력하세요 : 85
		당신의 성적은 B입니다.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수를 입력하세요 : ");
		int score = sc.nextInt();
		
		if(score >= 90) {
			System.out.println("당신의 성적은 A입니다.");
		} else if(score >= 80) {
			System.out.println("당신의 성적은 B입니다.");
		} else if(score >= 70) {
			System.out.println("당신의 성적은 C입니다.");
		} else if(score >= 60) {
			System.out.println("당신의 성적은 D입니다.");
		} else {
			System.out.println("당신의 성적은 F입니다.");
		}
		
		sc.close();
	}
}
