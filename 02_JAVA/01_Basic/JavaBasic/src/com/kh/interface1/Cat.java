package com.kh.interface1;

public class Cat implements Animal{

	@Override
	public void speak() {
		System.out.println("야옹");
		
	}

	@Override
	public void move() {
		System.out.println("고양이가 살금살금 기어갑니다.");
	}
	
}
