package com.kh.example.exception2;

import java.util.Scanner;

public class NumberMenu {
	public void menu() {
		Scanner sc = new Scanner(System.in);
		System.out.print("정수1 : ");
		int num1 = sc.nextInt();
		System.out.print("정수2 : ");
		int num2 = sc.nextInt();
		
		NumberController nc = new NumberController();
		
		try {
			boolean isCheck = nc.checkDouble(num1, num2);
			System.out.printf("%d은(는) %d의 배수인가 ? %s", num1, num2, isCheck ? "true" : "false");
		} catch (NumRangeException e) {
			e.printStackTrace();
		}
	}
}
