package com.kh.inherit;

public class Desktop extends Product{
	private boolean allinOne; //일체형여부

	public Desktop() {
		//상속관계에서 부모생성자를 명시하지 않아도
		//부모의 기본생성자가 호출
	}

	public Desktop(String pName, int price, String brand, boolean allinOne) {
		super(pName, price, brand);
		this.allinOne = allinOne;
	}

	public boolean isAllinOne() {
		return allinOne;
	}

	public void setAllinOne(boolean allinOne) {
		this.allinOne = allinOne;
	}
	
	//오버라이딩
	//->부모클래스에 있는 메서드를 자식클레스에서 내용만 재정의 하는 것
	public String inform() {
		return super.inform() + " / 올인원 : " + allinOne;
	}
	
}
