package com.kh.polymorphism;

public class Sonata extends Car{

	public Sonata(String color, String fuel, int year) {
		super(color, fuel, year);
	}
	
	public void moveSonata() {
		System.out.println("빵빵 ~ sonata");
	}
}
