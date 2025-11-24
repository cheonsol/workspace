package com.project.lifegame.model.vo;

public class Achievement {
	private int achievementId;
	private String title;
	private String describe;
	private boolean isAchieved;  
	public Achievement() {
		super();
	}

	public Achievement(int achievementId, String title, String describe) {
		super();
		this.achievementId = achievementId;
		this.title = title;
		this.describe = describe;
	}

	public int getAchievementId() {
		return achievementId;
	}

	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public boolean isAchieved() {
		return isAchieved;
	}

	public void setAchieved(boolean isAchieved) {
		this.isAchieved = isAchieved;
	}

	@Override
	public String toString() {
	    String displayTitle = isAchieved ? title : "?????";
	    String displayDesc = isAchieved ? describe : "?????";
	    return String.format("업적 %2d : %s - %s", achievementId, displayTitle, displayDesc);
	}
	
	
	
	
}
