package com.kh.exception;

import java.io.IOException;

public class Run {
	/*
	 * 에러의 종류
	 * 시스템 에러 : 하드웨어/OS 장애 -> 코드로 해결x
	 * 컴파일 에러 : 문법 오류 -> 컴파일러가 발경하면 개발자가 수정.
	 * 런타임 발생(= 예외 Exception) : 실행 중 발생, 개발자가 바로 처리해서 대응.
	 * 논리 에러 : 실행은 되지만 의도와 다른 결과 -> 발견이 어려움.
	 * 
	 * 예외처리를 하는 이유
	 * 프로그램의 비정상적인 종료를 방지하고 복구가능한 흐름으로 만들어 사용자 경험을 보호하기 위해.
	 * 
	 * 예외를 처리방법
	 * 1. try~catch문을 이용
	 * 2. throws를 이용해서 위임
	 * 
	 * 사용한 리소스를 반납 방법 2가지
	 * 1. finally에서 반납
	 * 2. try-with-resource문법을 사용
	 * */

	public static void main(String[] args) {
		RunException re = new RunException();
		re.method1();

		try {
			re.methodA();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(CustomException e) {
			System.out.println(e.getMessage());
		}

	}

}
