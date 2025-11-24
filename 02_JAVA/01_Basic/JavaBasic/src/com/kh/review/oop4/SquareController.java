package com.kh.review.oop4;

public class SquareController {
	private Shape s;
	
	public double calcPerimeter(double height, double width) {
		this.s = new Shape(1, height, width);
		return (s.getWidth() * 2) + (s.getHeight() * 2);
	}
	
	public double calcArea(double height, double width) {
		this.s = new Shape(1, height, width);
		return s.getWidth() * s.getHeight();
	}
	
	public void paintColor(String color) {
		s.setColor(color);
	}
	
	public String print() {
		return "사각형 " + s.information();
	}
}
