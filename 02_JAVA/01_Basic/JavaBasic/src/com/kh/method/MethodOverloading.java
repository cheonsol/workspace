package com.kh.method;

public class MethodOverloading {
	/*
	 * 메서드 오버로딩
	 * 오버로딩 : 같은 이름의 메서드를 매개변수의 개수/타입/순서가 다르게 여러 개 정의하는 것
	 *          반환형(return type)은 오버로딩의 구분기준이 아님
	 *          
	 * 함수의 이름 = 기능의 이름
	 * 매개변수 = 함수에서 이용하는 데이터
	 * 반환형 = 결과
	 * 
	 * 오버로딩을 사용하는 목적
	 * 동일한 기능을 다양한 입력형태로 편리하게 사용하기 위함.
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		adder(1.3, 5);
	}
	
	public static int adder(int n1, int n2) {
		return n1 + n2;
	}
	
	public static int adder(int n1, int n2, int n3) {
		return n1 + n2 + n3;
	}

	public static int adder(int n1, double n2) {
		return (int)(n1 + n2);
	}
	
	public static int adder(double n1, int n2) {
		return (int)(n1 + n2);
	}
	
	//오버로딩 성립x
	//매개변수의 이름이 다르다고해서 오버로딩이 성립되지x
	/*
	public static int adder(int n5, int n6) {
		return n1 + n2;
	}
	
	//반환형이 다르다고해서 호출시점에 메서드를 구분할 수 없음 오버로딩x
	public static double adder(double n1, int n2) {
		return (n1 + n2);
	}
	*/
}
