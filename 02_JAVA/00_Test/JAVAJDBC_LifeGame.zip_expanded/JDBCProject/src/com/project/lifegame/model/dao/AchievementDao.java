package com.project.lifegame.model.dao;

import static com.project.lifegame.common.LifegameTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.project.lifegame.model.vo.Achievement;

public class AchievementDao {
	private Properties prop = new Properties();
	public AchievementDao() {
		super();
		try {
	        prop.loadFromXML(new FileInputStream("resources/query.xml"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<Achievement> showAchievedList(String accessId, Connection conn){
		ResultSet rset = null;
		ArrayList<Achievement> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("showAchievedList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accessId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Achievement a = new Achievement();
				a.setAchievementId(rset.getInt("ACHIEVEMENT_ID"));
				a.setTitle(rset.getString("ACHIEVEMENT_TITLE"));
				a.setDescribe(rset.getString("ACHIEVEMENT_DESCRIBE"));
				a.setAchieved(rset.getBoolean("IS_ACHIEVE"));
				list.add(a);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				close(rset);
				close(pstmt);
				close(conn);
		}
		return list;
	}
	
	public int initUserAchievements(String userId, Connection conn) {
	    int result = 0;
	    PreparedStatement pstmt = null;
	    String sql = prop.getProperty("initUserAchievements");
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, userId);
	        result = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(pstmt);
	    }
	    return result;
	}
	
	public int updateAchievement(String userId, int achievementId, Connection conn) {
		int result = 0;
	    PreparedStatement pstmt = null;
	    String sql = prop.getProperty("updateAchievement");
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, userId);
	        pstmt.setInt(2, achievementId);
	        result = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(pstmt);
	    }
	    return result;
	}
	
	public boolean isAlreadyAchieved(String userId, int achievementId, Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		String sql = prop.getProperty("isAlreadyAchieved");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, achievementId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				if(rset.getBoolean("IS_ACHIEVE")) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				close(rset);
				close(pstmt);
				close(conn);
		}
		return result;
	}
}
