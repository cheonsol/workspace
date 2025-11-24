package com.project.lifegame.model.vo;

public class UserAchievement {
	private int achievement;
	private String userId;
	private boolean isAchieve;
	
	public UserAchievement() {
		super();
	}

	public UserAchievement(int achievement, String userId) {
		super();
		this.achievement = achievement;
		this.userId = userId;
	}

	public int getAchievement() {
		return achievement;
	}

	public void setAchievement(int achievement) {
		this.achievement = achievement;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isAchieve() {
		return isAchieve;
	}

	public void setAchieve(boolean isAchieve) {
		this.isAchieve = isAchieve;
	}
	
	
}
