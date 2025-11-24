package com.kh.object.ex3;

public class Run {
	/*
	 * 메서드 오버로딩
	 * 메서드의 이름이 동일해도 매개변수의 타입, 갯수, 순서에 따라 다른 메서드로 인식한다.
	 * */

	public static void main(String[] args) {
		Math m = new Math();
		System.out.println(m.adder(10, 20));
		System.out.println(m.adder(10));
		System.out.println(m.adder(10.0, 30.0));
		System.out.println(m.adder('a', 'b'));
		System.out.println(m.adder(10, "살입니다."));
		System.out.println(m.adder("나이 : ", 10));
	}

}
