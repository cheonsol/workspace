package com.kh.array;

import java.util.Scanner;

public class Array2D {
	/*
	 * 2차원 배열
	 * 자료형이 같은 1차원배열의 묶음.
	 * ex) int[행][열] arr -> ~열의 1차월배열이 행만큼 존재.
	 * 2차원배열은 할당된 공간마다 index를 2개 부여(앞은 행 : 몇번째 1차원배열인지, 뒤는 열 : 1차원배열의 갯수) 
	 * 
	 * 선언된 공간마다 같은 길이의 1차원배열을 사용할 수도 있고
	 * 각각 다른길이의 1차원배열을 할당할 수도 있음.
	 * */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//2차원배열 선언(1차원배열의 묶음을 참조하는 2차원배열 참조변수 선언)
		//자료형[][] 배열명;
		int[][] arr;
		
		//2차원배열의 메모리 할당
		//배열명 = new 자료형[n][m];
		//배열명 = new 자료형[n][];
		
		arr = new int[2][3];
		//arr라는 2차원배열에 2행3열의 메모리구조가 바로 할당됨
		//3개짜리 1차원배열 2개가 생성.
		arr[0][0] = 5;
		arr[0][1] = 10;
		arr[0][2] = 15;
		arr[1][0] = 1;
		arr[1][1] = 2;
		arr[1][2] = 3;
		
		
		for(int j=0; j<arr.length; j++) {
			for(int i=0; i<arr[j].length; i++) {
				System.out.print(arr[j][i] + " ");
			}
			System.out.println();
		}
		
		//2차원배열의 모든 요소를 탐색시
		//두개의 for문을 통해서 탐색하면 됨.
		/*
		 * for(int i=0; i<2차원배열.length; i++){
		 * 		for(int j=0; j<2차원배열[i].length; j++){
		 *			//2차원배열[i][j];
		 *		} 	
		 * }
		 * */
		
		
		/*
		arr = new int[3][];
		//1차원배열의 참조변수만 3개 생성;
		arr[0] = new int[5];
		arr[1] = new int[3];
		arr[2] = new int[2];
		*/
	
		
		
	}

}
