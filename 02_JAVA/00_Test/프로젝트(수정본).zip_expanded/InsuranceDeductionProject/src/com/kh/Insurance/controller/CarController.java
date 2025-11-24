package com.kh.Insurance.controller;

import java.util.List;

import com.kh.Insurance.model.Car;
import com.kh.Insurance.model.CompanyVo;
import com.kh.Insurance.service.CarService;

public class CarController {
	private CarService cs = new CarService();
	

	public CarController() {
		super();
	}
	public void insertCar(int id, String name) {
		Car c = new Car();
		c.setCarId(id);
		c.setCarName(name);
		cs.insertCar(c);
		
	}

}
