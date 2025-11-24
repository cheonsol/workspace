package com.kh.practice.array;

import java.util.Scanner;

public class Practice7 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[][] arr = new int[n][m];
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		int maxValue = Integer.MIN_VALUE;
		int x = 0;
		int y = 0;
		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				if(arr[i][j] > maxValue) {
					maxValue = arr[i][j];
					x = i;
					y = j;
				}
			}
		}
		
		System.out.println(maxValue);
		System.out.printf("%d %d", x+1, y+1);
		sc.close();
	}

}
