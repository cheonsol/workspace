package com.kh.array;

public class ArrayBasic {
	/*
	 * 변수 : 하나의 공간에 하나의 값을 담을 수 있음, 메모리공간에 이름을 붙여서 사용
	 * 배열 : 하나의 이름(참조변수)에 여러개의 "같은 자료형"의 값을 저장할 수 있는 연속적인 메모리 공간을 사용.
	 *
	 * 배열을 왜 사용할까요?
	 * 변수만을 사용하면 대량의 데이터를 보관하고자할 때 각각의 변수를 만들어서 따로 관리를 해야한다.
	 */
	public static void main(String[] args) {
		//int형 변수 5개 만들어서 0~4의 값을 각각 할당하자.
		int n1 = 0;
		int n2 = 1;
		int n3 = 2;
		int n4 = 3;
		int n5 = 4;
		
		//배열 사용
		int[] arr1 = new int[5];
		arr1[0] = 0;
		arr1[1] = 1;
		arr1[2] = 2;
		arr1[3] = 3;
		arr1[4] = 4;
		
		//배열 + 초기화
		int[] arr2 = {0,1,2,3,4};

		//배열 사용방법
		//1. 선언 : 자료형[] 배열이름;
		int[] arr3; // 배열의 참조변수만 생성, 아직 사용가능한 메모리가 없음
		
		//2. 생성(메모리를 할당) : 배열이름 = new 자료형[길이];
		arr3 = new int[3]; //정수 10개를 저장할 수 있는 공간 생성
		
		//반복문을 이용한 값 할당
		//for(int i=0; i<배열의길이; i++) {
		for(int i=0; i<arr3.length ; i++) {
			arr3[i] = (i + 1) * 10;
		}
		//반복문을 이용한 값 출력
		for(int i=0; i<arr3.length ; i++) {
			int num = arr3[i];
			System.out.println(num);
		}
		
		//for-each문 : 배열을 전체적으로 탐색할 때 쉽게 사용할 수 있는 문법
		/*
		for(배열의 값을 받아줄 변수 : 배열) {
			반복할 코드
		}
		*/
		for(int num : arr3) {
			System.out.println(num + "을 가져옴");
		}
		
		
	}

}
