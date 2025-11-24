package com.kh.Insurance.controller;

import java.util.List;

import com.kh.Insurance.model.AccidentWeight;
import com.kh.Insurance.model.DriveWeight;
import com.kh.Insurance.service.DriveWeightService;



public class DriveWeightController {
	private DriveWeightService ds = new DriveWeightService();

	public DriveWeightController() {
		super();
	}
	public void insertDriveWeight(int id, int company, int car, int min, int max, double score  ) {
		DriveWeight d = new DriveWeight();
		d.setDwId(id);
		d.setCompanyId(company);
		d.setCarId(car);
		d.setExperienceMin(min);
		d.setExperienceMax(max);
		d.setDwWeightScore(score);
		int result = ds.insertDriveWeight(d);
		
	}
	

}
