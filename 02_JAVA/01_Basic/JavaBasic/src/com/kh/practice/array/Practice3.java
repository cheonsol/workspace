package com.kh.practice.array;

import java.util.Scanner;

public class Practice3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); //바구니 수
		int m = sc.nextInt(); //작업반복횟수
		
		int[] basket = new int[n];
		
		for(int h=0; h<m; h++) {
			int i = sc.nextInt();//i번째 배열부터
			int j = sc.nextInt();//j번째 배열까지
			int k = sc.nextInt();//k를 대입
			for(int idx = i; idx <= j; idx++) {
				basket[idx - 1] = k;
			}
		}
		
		for(int num : basket) {
			System.out.print(num + " ");
		}
		sc.close();
		
	}

}
