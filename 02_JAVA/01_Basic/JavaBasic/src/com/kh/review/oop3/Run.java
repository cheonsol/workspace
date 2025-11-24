package com.kh.review.oop3;

public class Run {

	public static void main(String[] args) {
		new Book().inform();
		new Book("자바는 즐거워", "KH", "최지원").inform();
		new Book("자바는 즐거워", "KH", "최지원", 10000, 0.1).inform();
	}

}
