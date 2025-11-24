package com.kh.basic;

import java.util.Scanner;

public class Input {
	/*
	 * 입력 : 외부에서의 데이터를 코드내부로 가져오는 것
	 * 
	 * Scanner를 사용하면 키보드에 입력값을 가져올 수 있음.
	 * (java.util.Scanner 클래스를 이용함.)
	 * 
	 * [사용법]
	 * Scanner 이름 = new Scanner(System.in);
	 * */

	public static void main(String[] args) {
		//Scanner객체 생성
		Scanner sc = new Scanner(System.in);
		
		//.next()
		//문자열 입력받는 함수(공백 전까지)
		//입력받은 값을 특정 변수에 바로 대입;
		/*
		String str1, str2;
		
		System.out.print("입력 : ");
		str1 = sc.next();
		str2 = sc.next();
		
		System.out.println("str1에 입력한 값 : " + str1);
		System.out.println("str2에 입력한 값 : " + str2);
		*/
		
		//.nextLine()
		//문자열 입력박는 함수(공백을 포함한 한줄, 개행문자까지)
		System.out.print("이름 : ");
		String name = sc.nextLine();
		
		System.out.print("나이 : ");
		int age = sc.nextInt(); //정수를 입력받고자 할 때
		sc.nextLine(); //버퍼에 남아있는 nextInt()를 입력할 때 발생한 \n(엔터값)을 비워주는 코드
		
		System.out.print("주소 : ");
		String address = sc.nextLine();
		
		System.out.printf("제 이름은 %s이며 %s에 살고 있습니다. 저는 올해 %d살 입니다.", name, address, age);
	
		//문자이외에 원시타입을 입력받을 때
		//.next타입()
		// nextInt(), nextDouble(), nextBoolean() ...
		
		sc.close(); //Scanner자원을 다쓰고 반납 *한번 반납하면 다시 사용x
		
		//sc = new Scanner(System.in); 에러가 발생함
	}

}
