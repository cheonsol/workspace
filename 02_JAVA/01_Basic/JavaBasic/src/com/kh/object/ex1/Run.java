package com.kh.object.ex1;
/*
 * 해당클래스는 객체를 생성하기위함이 아닌
 * 실행을위한 main문을 포함한 클래스
 */
public class Run {

	public static void main(String[] args) {
		//객체를 생성할 때
		//class명 객체이름; -> 해당 class타입의 참조변수 생성
		Student choi;
		//객체이름 = new class명() -> 새로운 class타입의 메모리 공간을 할당해서 주소를 참조해라.
		choi = new Student();

		Student kim = new Student();
		
		choi.name = "최지원";
		choi.age = 55;
		
		kim.name = "김민수";
		kim.age = 30;
		
		choi.myInfo();
		kim.myInfo();
		
		System.out.println(choi);
		System.out.println(kim);
	}

}
