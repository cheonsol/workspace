package com.kh.override;

public class Run {
	/*
	 * 참조변수를 호출하면 자동으로 .toString()이 호출됨.
	 * -> Object에 정의된 메서드로 모든 클래스는 Object를 상속받아 toString()을 사용할 수 있음.
	 * 
	 * 오버라이딩 전(Object) toString() : 클래스명 + @ + 객체주소의 hashCode를 16진수변환을 반환\
	 * 오버라이딩 후(Man) toString() : 내가 원하는 형식의 구조로 재정의
	 * 
	 * *오버라이딩
	 * - 자식클래스가 상속받고있는 부모클래스의 메서드를 재정의 하는 것
	 * - 부모가 제공하는 메서드를 자식이 일부 수정해서 사용하겠다는 의미
	 * 
	 * *오버라이딩의 성립조건
	 * -부모메서드명과 메서드명이 동일
	 * -매개변수의 개수, 자료형, 순서를 포함
	 * -반환형도 동일해야함.
	 * -부모메서드의 접근제한자보다는 범위가 같거나 커야함.
	 *  
	 */

	public static void main(String[] args) {
		String str1 = new String("최지원");
		String str2 = new String("최지원");
		Man m1 = new Man("최지원", "201212-1234567");
		Man m2 = new Man("최지원", "201212-1234567");
		
		System.out.println(m2);
		System.out.println(m1);
		System.out.println(str1);
		System.out.println(str2);
		
		System.out.println(str1.equals(str2));
		System.out.println(m1.equals(m2));
		
	}

}
