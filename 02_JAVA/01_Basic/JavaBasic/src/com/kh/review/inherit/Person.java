package com.kh.review.inherit;

public class Person extends Object{
	private String name;
	private int age;
	private double height;
	private double weight;
	
	public Person() {
		super();
	}
	public Person(String name, int age, double height, double weight) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	//class의 정보를 문자열로 반환
	@Override
	public String toString() {
		return name + ", " + age + ", " + height + ", " + weight;
	}
}
