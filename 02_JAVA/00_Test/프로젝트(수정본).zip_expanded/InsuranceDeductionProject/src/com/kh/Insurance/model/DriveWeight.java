package com.kh.Insurance.model;

public class DriveWeight {
	private int dwId;
	private int companyId;
	private int carId;
	private int experienceMin;
	private int experienceMax;
	private double dwWeightScore;
	
	public DriveWeight() {
		super();
	}

	public DriveWeight(int dwId, int companyId, int carId, int experienceMin, int experienceMax, double dwWeightScore) {
		super();
		this.dwId = dwId;
		this.companyId = companyId;
		this.carId = carId;
		this.experienceMin = experienceMin;
		this.experienceMax = experienceMax;
		this.dwWeightScore = dwWeightScore;
	}

	public DriveWeight(int dwId, int experienceMin, int experienceMax, double dwWeightScore) {
		super();
		this.dwId = dwId;
		this.experienceMin = experienceMin;
		this.experienceMax = experienceMax;
		this.dwWeightScore = dwWeightScore;
	}

	public int getDwId() {
		return dwId;
	}

	public void setDwId(int dwId) {
		this.dwId = dwId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public int getExperienceMin() {
		return experienceMin;
	}

	public void setExperienceMin(int experienceMin) {
		this.experienceMin = experienceMin;
	}

	public int getExperienceMax() {
		return experienceMax;
	}

	public void setExperienceMax(int experienceMax) {
		this.experienceMax = experienceMax;
	}

	public double getDwWeightScore() {
		return dwWeightScore;
	}

	public void setDwWeightScore(double dwWeightScore) {
		this.dwWeightScore = dwWeightScore;
	}

	@Override
	public String toString() {
		return "DriveWeight [dwId=" + dwId + ", companyId=" + companyId + ", carId=" + carId + ", experienceMin="
				+ experienceMin + ", experienceMax=" + experienceMax + ", dwWeightScore=" + dwWeightScore + "]";
	}

}
