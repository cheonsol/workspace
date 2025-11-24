package com.project.lifegame.service;

import static com.project.lifegame.common.LifegameTemplate.close;
import static com.project.lifegame.common.LifegameTemplate.commit;
import static com.project.lifegame.common.LifegameTemplate.getConnection;
import static com.project.lifegame.common.LifegameTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.project.lifegame.model.dao.LifeCharacterDao;
import com.project.lifegame.model.vo.LifeCharacter;

public class LifeCharacterService {
	LifeCharacterDao lcd;
	public LifeCharacterService() {
		super();
		lcd = new LifeCharacterDao();
	}
	
	public int createNewCharacter(LifeCharacter life){
		Connection conn = getConnection();
		
		int result = lcd.createNewCharacter(life, conn);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public LifeCharacter selectLifeCharacterByCharacterId(int characterId) {
		Connection conn = getConnection();
		LifeCharacter resChar = lcd.selectLifeCharacterByCharacterId(conn, characterId);
		if(resChar != null) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return resChar;
	}
	
	public int execute(LifeCharacter life) {
		Connection conn = getConnection();
		int result = lcd.execute(life, conn);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int executeStockTrade(LifeCharacter life) {
		Connection conn = getConnection();
		int result = lcd.executeStockTrade(life, conn);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int updateRealEstate(LifeCharacter life) {
		Connection conn = getConnection();
		int result = lcd.updateRealEstate(life, conn);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int updateShopping(LifeCharacter life) {
		Connection conn = getConnection();
	    int result = lcd.updateShopping(life, conn);
	    if(result > 0) {
	        commit(conn);
	    }else {
	        rollback(conn);
	    }
	    close(conn);
	    return result;
	}
	
	public List<LifeCharacter>showRank() { 
		Connection conn = getConnection();
		List<LifeCharacter> list = lcd.showRank(conn);
			
		close(conn);
		return list;
	}	


}