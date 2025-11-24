package com.kh.Insurance.model;

public class Car {
	private int carId;
	private String carName;
	public Car() {
		super();
	}
	public Car(int carId, String carName) {
		super();
		this.carId = carId;
		this.carName = carName;

	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}

	@Override
	public String toString() {
		return "차량 정보 : " + carName ;
	}

}
