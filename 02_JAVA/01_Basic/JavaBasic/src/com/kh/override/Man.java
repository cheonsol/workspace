package com.kh.override;

public class Man {
	private String name;
	private String number; //주민등록번호

	public Man(String name, String number) {
		super();
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Man [name=" + name + ", number=" + number + "]";
	}

	@Override
	public boolean equals(Object obj) {
        return this.number == ((Man)obj).getNumber();
    }
	
	
}
