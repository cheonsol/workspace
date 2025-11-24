package com.kh.collection.list;

public class Human {
	private String name;
	private int age;
	
	public Human() {
		super();
	}

	public Human(String name, int age) {
		super();
		this.name = name;
		this.age = age;
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

	@Override
	public String toString() {
		return "[name=" + name + ", age=" + age + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Human) {
			Human h = (Human)obj;
			return this.name.equals(h.getName()) && this.age == h.getAge();
		}
		return false;
	}
}
