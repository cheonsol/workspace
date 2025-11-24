package com.kh.practice.array;

import java.util.Scanner;

public class Practice1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 

		int n = sc.nextInt();
		int x = sc.nextInt();
		
		int[] iArr = new int[n];
		
		for(int i=0; i<n; i++) {
			iArr[i] = sc.nextInt();
		}
		
		for(int i=0; i<iArr.length; i++) {
			if(iArr[i] < x) {
				System.out.print(iArr[i] + " ");
			}
		}
		
		sc.close();
	}

}
