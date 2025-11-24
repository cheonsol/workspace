package com.kh.review.oop4;

public class TriangleController {
	private Shape s;
	
	public double calcArea(double height, double width) {
		s = new Shape(2, height, width);
		return (s.getWidth() * s.getHeight() / 2);
	}
	
	public void paintColor(String color) {
		s.setColor(color);
	}
	
	public String print() {
		return "삼각형 " + s.information();
	}
}
