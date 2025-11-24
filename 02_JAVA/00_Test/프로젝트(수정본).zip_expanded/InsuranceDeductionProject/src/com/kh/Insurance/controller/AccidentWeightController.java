package com.kh.Insurance.controller;

import java.util.List;

import com.kh.Insurance.model.AccidentWeight;
import com.kh.Insurance.service.AccidentWeightService;


public class AccidentWeightController {
	private AccidentWeightService as = new AccidentWeightService();

	public AccidentWeightController() {
		super();
	}
	public void insertAccidentWeight(int id, int company, int car, int min, int max, double score  ) {
		AccidentWeight a = new AccidentWeight();
		a.setAwId(id);
		a.setCompanyId(company);
		a.setCarId(car);
		a.setAccidentMin(min);
		a.setAccidentMax(max);
		a.setAwWeightScore(score);
		int result = as.insertAccidentWeight(a);
		
	}

}
