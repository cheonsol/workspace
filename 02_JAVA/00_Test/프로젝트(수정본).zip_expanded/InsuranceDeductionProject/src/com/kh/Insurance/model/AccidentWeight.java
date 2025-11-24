package com.kh.Insurance.model;

public class AccidentWeight {
	private int awId;
	private int companyId;
	private int carId;
	private int accidentMin;
	private int accidentMax;
	private double awWeightScore;
	public AccidentWeight() {
		super();
	}
	public AccidentWeight(int awId, int companyId, int carId, int accidentMin, int accidentMax, double awWeightScore) {
		super();
		this.awId = awId;
		this.companyId = companyId;
		this.carId = carId;
		this.accidentMin = accidentMin;
		this.accidentMax = accidentMax;
		this.awWeightScore = awWeightScore;
	}
	public AccidentWeight(int awId, int accidentMin, int accidentMax, double awWeightScore) {
		super();
		this.awId = awId;
		this.accidentMin = accidentMin;
		this.accidentMax = accidentMax;
		this.awWeightScore = awWeightScore;
	}
	public int getAwId() {
		return awId;
	}
	public void setAwId(int awId) {
		this.awId = awId;
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
	public int getAccidentMin() {
		return accidentMin;
	}
	public void setAccidentMin(int accidentMin) {
		this.accidentMin = accidentMin;
	}
	public int getAccidentMax() {
		return accidentMax;
	}
	public void setAccidentMax(int accidentMax) {
		this.accidentMax = accidentMax;
	}
	public double getAwWeightScore() {
		return awWeightScore;
	}
	public void setAwWeightScore(double awWeightScore) {
		this.awWeightScore = awWeightScore;
	}
	@Override
	public String toString() {
		return "AccidentWeight [awId=" + awId + ", companyId=" + companyId + ", carId=" + carId + ", accidentMin="
				+ accidentMin + ", accidentMax=" + accidentMax + ", awWeightScore=" + awWeightScore + "]";
	}

}
