package com.kh.practice.loop;

import java.util.Scanner;

public class Practice2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int x = sc.nextInt(); //영수증 총 금액
		int n = sc.nextInt(); //구매한 물건의 종류수
		int sum = 0;
		
		for(int i=0; i<n; i++) {
			int a = sc.nextInt(); //물건의 가격
			int b = sc.nextInt(); //물건의 구매개수
			
			sum += (a * b);
		}
		
		System.out.println(sum == x ? "Yes" : "No");
		sc.close();
	}
}
