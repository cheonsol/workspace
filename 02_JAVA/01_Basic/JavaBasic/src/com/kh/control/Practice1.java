package com.kh.control;

import java.util.Scanner;

public class Practice1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/*
		 * 나이를 입력받아
		 * 13세이하면 : 어린이
		 * 13세초과 19세이하 : 청소년
		 * 19세 초과 : 성인
		 * 
		 *  [출력]
		 *  나이를 입력 : xx
		 *  xx은 xxx에 속합니다.
		 * 
		 * */
		
		System.out.print("나이를 입력 : ");
		int age = sc.nextInt();
		String grade = "성인";
		
		if(age <= 13) {
			grade = "어린이";
		} else if(age <= 19) {
			grade = "청소년";
		} 
		
		System.out.println(age + "살은 " + grade + "에 속합니다.");
	}

}
