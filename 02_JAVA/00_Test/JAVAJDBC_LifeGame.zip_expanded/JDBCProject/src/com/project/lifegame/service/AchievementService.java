package com.project.lifegame.service;

import static com.project.lifegame.common.LifegameTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.project.lifegame.model.dao.AchievementDao;
import com.project.lifegame.model.vo.Achievement;

public class AchievementService {
	AchievementDao ad = new AchievementDao();
	public AchievementService() {
		super();
	}
	
	public List<Achievement> showAchievedList(String accessId){
		Connection conn = getConnection();
		List<Achievement> list = ad.showAchievedList(accessId, conn);
		close(conn);
		return list;
	}
	public int initUserAchievements(String userId) {
	    Connection conn = getConnection();
	    int result = ad.initUserAchievements(userId, conn);
	    
	    if(result > 0) {
	        commit(conn);
	    } else {
	        rollback(conn);
	    }
	    close(conn);
	    return result;
	}
	
	public int updateAchievement(String userId, int achievementId) {
		Connection conn = getConnection();
	    int result = ad.updateAchievement(userId, achievementId, conn);
	    
	    if(result > 0) {
	        commit(conn);
	    } else {
	        rollback(conn);
	    }
	    close(conn);
	    return result;
	}
	
	public boolean isAlreadyAchieved(String userId, int achievementId) {
		Connection conn = getConnection();
	    boolean result = ad.isAlreadyAchieved(userId, achievementId, conn);
	    close(conn);
	    return result;
	}
}
