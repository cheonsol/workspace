package com.kh.interface1;

public class Run {
	/*
	 * 인터페이스 : 클래스가 구현해야할 메서드(추상메서드)를 정의해 둔 것
	 * 
	 * 추상메서드만 선언 가능(구현부가 없는 메서드)
	 * 	- java8이후 default메서드, static메서드도 사용 가능
	 * 모든 메서드는 묵시적으로 public abstract키워드가 생성(명시적으로 사용 가능) 
	 * 모든 변수는 묵시적으로 public static final(정적상수, 명시적 사용 가능)
	 * 인터페이스를 implements하는 클래스는 반드시 인터페이스의 모든 추상메서드를 구현
	 * 자바에서는 클래스 다중상속 불가 -> 인터페이스는 다중상속, 다중구현 가능
	 * 
	 * 장점
	 * 1. 다형성 제공(인터페이스를 참조타입으로 사용하여 여러 객체를 구현할 수 있음)
	 * 2. 기능의 표준화(기능을 미리 추상메서드로 정의하여 형식을 통일)
	 * 3. 결합도 낮아짐(구현체를 자유롭게 교체) 
	 */

	public static void main(String[] args) {
		Animal dog = new Dog();
		
		dog.speak();
		dog.move();
		//dog.eat(); 참조타입에따른 접근

	}

}
