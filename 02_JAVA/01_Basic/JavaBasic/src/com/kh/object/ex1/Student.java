package com.kh.object.ex1;

/**
 * 클래스의 구조
 * 
 * 접근제한자 class 클래스명{
 * 		//필드영역
 * 		  : 사용할 데이터를 선언하는 영역
 * 		//생성자영역
 * 		  : 데이터를 초기화하기위한 특수목적의 메서드(생성자)를 정의하는 영역
 * 		//메서드영역
 * 		  : 클래스의 기능을 정의하는 영역
 * }
 */

public class Student {
	//필드
	String name;
	int age;
	double height;
	
	//메서드
	//내 정보를 출력하는 기능
	void myInfo() {
		System.out.printf("안녕하세요. 저는 %d살의 %s입니다.\n", age, name);
	}
}
