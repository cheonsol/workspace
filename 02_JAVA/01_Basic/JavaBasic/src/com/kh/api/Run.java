package com.kh.api;

/*
 * API(Application Programming Interface)
 * : 프로그램을 만들때 사용할 수 있도록 제공되는 미리 만들어진 기능의 모음. -> 미리 작성해둔 유용한 인터페이스(명령어, 클래스, 메서드, 라이브러리...)
 *
 * 자바 API = 자바 표준 라이브러리
 * -> 자바언어는 기본 문법(변수, 제어문, 클래스...)만 제공을 함.
 * -> 실제 개발에서는 문자열 처리, 날짜 계산, 입출력, 네트워크통신, 자료구조사용 등 수많은 기능이 필요함.
 * -> 이런 기능을 자바 개발팀에서 미리 구현해둔 클래스/인터페이스 집합을 Java 표준 API라고 함.
 * 
 * java.lang.String, java.io.File....
 */

public class Run {

	public static void main(String[] args) {
		//new MathAPI().method();
		//new StringAPI().method01();
		//new StringAPI().method02();
		//new StringAPI().method03();
		//new WrapperAPI().method();
		new DateAPI().method();
	}

}
