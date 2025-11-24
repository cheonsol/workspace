package com.kh.practice.array;

import java.util.Scanner;

public class Practice8 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/**
		 * f f f f f f ...
		 * f f f f f f 
		 * f f f f f f
		 * f f f f f f
		 * ...
		 */
		boolean[][] paper = new boolean[100][100];
		int n = sc.nextInt(); //색종이를 붙일 횟수
		
		for(int k=0; k<n; k++) {
			int x = sc.nextInt(); //왼쪽으로부터 색종이와의 거리
			int y = sc.nextInt(); //아래로부터 색종이와의 거리
			
			for(int i=x; i<x+10; i++) {
				for(int j=y; j<y+10; j++) {
					paper[i][j] = true;
				}
			}
		}
		
		int area = 0;
		for(int i=0; i<paper.length; i++) {
			for(int j=0; j<paper[i].length; j++) {
				if(paper[i][j]) {
					area++;
				}
			}
		}
		
		System.out.println(area);
		sc.close();
	}

}
