package com.kh.polymorphism;

public class Avante extends Car{

	public Avante(String color, String fuel, int year) {
		super(color, fuel, year);
	}
	
	public void drive() {
		System.out.println("슈웅~ 슈웅~");
	}
	
	public void moveAvante() {
		System.out.println("빵빵~ avante");
	}
	
	//매개변수에 부모타입 참조변수를 사용하면 모든 자식타입을 받을 수 있음
	public void driveCar(Car car) {
		//매개변수로 넘어온 car가 실제 메모리공간에 Avante를 구현하고있어야 moveAvante()사용가능 
		if(car instanceof Avante) {
			((Avante)car).moveAvante();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Avante) {
			//비교
		}
		return false;
	}
	
	
}
