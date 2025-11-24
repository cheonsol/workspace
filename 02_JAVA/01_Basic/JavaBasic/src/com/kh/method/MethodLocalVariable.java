package com.kh.method;

public class MethodLocalVariable {
	/*
	 * 지역변수
	 * - 블록({})내부에서 선언된 변수
	 * - 해당 블록을 벗어나면 메모리에서 제거됨.
	 * - 선언시 반드시 초기화 후 사용해야됨.
	 * 
	 * 매개변수
	 * - 메서드 호출시 외부에서 전달받는 값을 저장하는 지역변수
	 * - 메서드 선언부에 정의됨
	 * - 메서드 실행시 생성되고, 메서드 종료시 사라짐.
	 * */

	public static void main(String[] args) {
		//main의 지역변수로
		//main이 종료되면 사라짐.
		String name = "철수";
		int age = 20;
		
		System.out.println("say실행 전 : " + name);
		say(name, age);
		System.out.println("say실행 후 : " + name);
		if(name.equals("철수")) {
//			String name = "지원"; 포함되는 관계는 같은 지역이므로 동일한 이름을 사용할 수 없음.
		}
		
		//Call By Value(값에 의한 호출) -> 자바는 무조건 이방식으로 호출
		//메모리에 담긴 값 자체를 전달
		// 기본형 -> 값(데이터)자체가 복사됨
		// 참조형 -> 참조값(주소)이 복사됨
		//			주소값을 복사해서 같은 객체를 가르키게 되므로, 내부 데이터를 변경하면 원본에도 영향을 줌

		//기본형
		int num = 10;
		changeValue(num);
		System.out.println("main에서 changeValue 호출 후 : " + num);
		
		//참조형
		int[] arr = {1,2,3};
		changeReference(arr);
		System.out.println("main문의 arr ----------------------");
		for(int n : arr) { 
			System.err.print(n + " "); 
		}
		
		//참조형을 전달하면 실제 값자체를 전달하는게 아니라
		//참조형에 담긴 주소값을 전달하기 때문에
		//해당 주소로 접근해서 원본데이터를 변경할 수 있다.
	}
	
	public static void changeValue(int value) {
		System.out.println("전달받은 value : " + value);
		value = 100;
		System.out.println("변경된 vlaue : " + value);
	}
	
	public static void changeReference(int[] array) {
		System.out.println("전달받은 array ----------------------");
		for(int n : array) { 
			System.err.print(n + " "); 
		}
		
		System.out.println();
		array[0] = 100;
		System.out.println("전달받은 array 0번째 변경완료");
	}
	
	public static void say(String name, int age) {
		name = "지원";
		System.out.println("say내부 : " + name);
		//name, age는 매개변수이자 이 메서드의 지역변수
	}

}
