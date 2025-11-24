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

import com.project.lifegame.model.vo.LifeCharacter;

public class LifeCharacterDao {
    private Properties prop = new Properties();

    public LifeCharacterDao() {
        super();
        try {
            prop.loadFromXML(new FileInputStream("resources/query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public int createNewCharacter(LifeCharacter life, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String characterSql = prop.getProperty("createNewCharacter");
        String stockSql = prop.getProperty("initializeStock");
        
        try {
            // 1) 캐릭터 생성
            pstmt = conn.prepareStatement(characterSql, new String[]{"CHARACTER_ID"}); 
            pstmt.setString(1, life.getCharacterName());
            pstmt.setString(2, life.getUserId());
            result = pstmt.executeUpdate();

            // 2) 방금 생성된 CHARACTER_ID 가져오기
            rset = pstmt.getGeneratedKeys();
            int charId = 0;
            if (rset.next()) {
                charId = rset.getInt(1);
                life.setcharacterId(charId);
            }

            // 3) STOCK 초기화
            if (charId != 0) {
                pstmt.close();
                pstmt = conn.prepareStatement(stockSql);
                pstmt.setInt(1, charId);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return result;
    }
    
    public int updateRealEstate(LifeCharacter life, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("updateRealEstate");
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, life.getMoney());
            pstmt.setInt(2, life.isHasApartment() ? 1 : 0);
            pstmt.setInt(3, life.isHasBuilding() ? 1 : 0);
            pstmt.setInt(4, life.isHasHotel() ? 1 : 0);
            pstmt.setString(5, life.getUserId());
            pstmt.setString(6, life.getCharacterName());
            
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
    
    public LifeCharacter selectLifeCharacterByCharacterId(Connection conn, int charNo) {
        LifeCharacter lc = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = prop.getProperty("selectLifeCharacterByCharNo");

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, charNo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                lc = new LifeCharacter(charNo);
                lc.setCharacterName(rs.getString("CHARACTER_NAME"));
                lc.setUserId(rs.getString("USER_ID"));
                lc.setAge(rs.getInt("AGE"));
                lc.setMoney(rs.getInt("MONEY"));
                lc.setIntelligence(rs.getInt("INTELLIGENCE"));
                lc.setStamina(rs.getInt("STAMINA"));
                lc.setLuck(rs.getInt("LUCK"));
                lc.setStockCount(rs.getInt("STOCK_COUNT"));
                lc.setPartTimeExp(rs.getInt("PART_TIME_EXP"));
                lc.setEmployeeExp(rs.getInt("EMPLOYEE_EXP"));
                lc.setExecutiveExp(rs.getInt("EXECUTIVE_EXP"));
                lc.setEntrepreneurExp(rs.getInt("ENTREPRENEUR_EXP"));
                lc.setLaborerExp(rs.getInt("LABORER_EXP"));
                lc.setSupervisorExp(rs.getInt("SUPERVISOR_EXP"));
                lc.setTrainerExp(rs.getInt("TRAINER_EXP"));
                lc.setAthleteExp(rs.getInt("ATHLETE_EXP"));
                lc.setHasApartment(rs.getInt("HAS_APARTMENT") == 1);
                lc.setHasBuilding(rs.getInt("HAS_BUILDING") == 1);
                lc.setHasHotel(rs.getInt("HAS_HOTEL") == 1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }

        return lc;
    }

    public int execute(LifeCharacter life, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("updateCharacter");
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, life.getAge());
            pstmt.setInt(2, life.getMoney());
            pstmt.setInt(3, life.getIntelligence());
            pstmt.setInt(4, life.getStamina());
            pstmt.setInt(5, life.getLuck());
            pstmt.setInt(6, life.getPartTimeExp());
            pstmt.setInt(7, life.getEmployeeExp());
            pstmt.setInt(8, life.getExecutiveExp());
            pstmt.setInt(9, life.getEntrepreneurExp());
            pstmt.setInt(10, life.getLaborerExp());
            pstmt.setInt(11, life.getSupervisorExp());
            pstmt.setInt(12, life.getTrainerExp());
            pstmt.setInt(13, life.getAthleteExp());
            pstmt.setInt(14, life.getStockCount());
            pstmt.setInt(15, life.isHasApartment() ? 1 : 0);
            pstmt.setInt(16, life.isHasBuilding() ? 1 : 0);
            pstmt.setInt(17, life.isHasHotel() ? 1 : 0);
            
            pstmt.setString(18, life.getUserId());
            pstmt.setString(19, life.getCharacterName());
            
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
    
    public int executeStockTrade(LifeCharacter life, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("updateStockTrade");
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, life.getMoney());
            pstmt.setInt(2, life.getStockCount());
            pstmt.setInt(3, life.getStockValue());
            pstmt.setString(4, life.getUserId());
            pstmt.setString(5, life.getCharacterName());
            
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
    
    public int updateShopping(LifeCharacter life, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("updateShopping");
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, life.getMoney());
            pstmt.setInt(2, life.getIntelligence());
            pstmt.setInt(3, life.getStamina());
            pstmt.setInt(4, life.getLuck());
            pstmt.setString(5, life.getUserId());
            pstmt.setString(6, life.getCharacterName());
            
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
    
    public ArrayList<LifeCharacter> showRank(Connection conn) {
        ArrayList<LifeCharacter> rankingList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("showRank");
        
        try {
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                LifeCharacter character = new LifeCharacter(0); 
                character.setUserId(rset.getString("USER_ID"));
                character.setCharacterName(rset.getString("CHARACTER_NAME"));
                character.setMoney(rset.getInt("MONEY"));
                character.setStockValue(rset.getInt("STOCK_VALUE"));
                // TOTAL_ASSET은 따로 안 가져와도 됨 (toString()에서 계산)
                
                rankingList.add(character);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        
        return rankingList;
    }
}