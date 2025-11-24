package com.kh.practice.array;

import java.util.Scanner;

public class Practice6 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[][] arrA = new int[n][m]; 
		int[][] arrB = new int[n][m];
		
		for(int i=0; i<arrA.length; i++) {
			for(int j=0; j<arrA[i].length; j++) {
				arrA[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<arrB.length; i++) {
			for(int j=0; j<arrB[i].length; j++) {
				arrB[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print((arrA[i][j] + arrB[i][j]) + " ");
			}
			System.out.println();
		}

	}

}
