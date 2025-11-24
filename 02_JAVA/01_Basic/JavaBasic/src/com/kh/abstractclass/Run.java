package com.kh.abstractclass;

public class Run {
	/*
	 * 추상클래스 : 하나 이상의 추상메서드를 포함하는 클래스(추상메서드 없이 선언도 가능)
	 * 
	 * abstract키워드를 명시적으로 class앞에 붙여서 선언.
	 * 추상메서드 또한 abstract키워드를 명시적으로 사용.
	 * 객체생성 불가 -> 반드시 상속하여 구현 후 사용
	 * 
	 * 용도
	 * 1. 필수기능(일반메서드) + 형식(추상메서드)를 동시에 제공할 수 있음
	 * 2. 상속을 통해 자식클래스에서 반드시 구현해야할 기능을 강제
	 * 3. 코드 재사용성과 일관성을 높임.
	 */

	public static void main(String[] args) {
		Animal dog = new Dog();
		
		dog.speak();
		dog.move();
		dog.breathe();
		
		Animal cat = new Cat();
		
		cat.speak();
		cat.move();
		cat.breathe();
	}

}
