package com.kh.example.inherit;

public class RectangleController {
	private Rectangle r = new Rectangle();
	
	public String calcArea(int x, int y, int height, int width) {
		r.setX(x);
		r.setY(y);
		r.setWidth(width);
		r.setHeight(height);
		
		double area = width * height;
		return r.toString() + " / " + area;
	}
	
	public String calcPerimeter(int x, int y, int height, int width) {
		r.setX(x);
		r.setY(y);
		r.setWidth(width);
		r.setHeight(height);
		
		double perimeter = (width + height) * 2;
		return r.toString() + " / " + perimeter;
	}
}
