package com.kh.interface1;

public class Dog implements Animal{

	@Override
	public void speak() {
		System.out.println("멍멍!");
	}

	@Override
	public void move() {
		System.out.println("강아지가 달립니다.");
	}
	
	public void eat() {
		System.out.println("냠냠");
	}
}
