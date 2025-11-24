package com.kh.review.oop1;

public class Run {

	public static void main(String[] args) {
		Product p1 = new Product();
		p1.setBrand("삼성");
		p1.setpName("갤럭시북3");
		p1.setPrice(2000000);
		
		Product p2 = new Product();
		p2.setBrand("LG");
		p2.setpName("그램 15");
		p2.setPrice(1800000);
		
		p1.information();
		p2.information();
	}

}
