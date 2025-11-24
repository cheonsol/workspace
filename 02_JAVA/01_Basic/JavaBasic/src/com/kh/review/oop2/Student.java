package com.kh.review.oop2;

public class Student {
	private int grade;
	private int classroom;
	private String name;
	private double height;
	private char gender;
	
	{
		grade = 1;
		classroom = 1;
		name = "미입력";
		height = 0;
		gender = '여';
	}
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getClassroom() {
		return classroom;
	}
	public void setClassroom(int classroom) {
		this.classroom = classroom;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public String information() {
		return "Student [grade=" + grade + ", classroom=" + classroom + ", name=" + name + ", height=" + height
				+ ", gender=" + gender + "]";
	}
	
}
