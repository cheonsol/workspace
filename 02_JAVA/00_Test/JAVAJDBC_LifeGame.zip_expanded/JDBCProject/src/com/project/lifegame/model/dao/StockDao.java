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

import com.project.lifegame.model.vo.Stock;

public class StockDao {
    private Properties prop = new Properties();
    
    public StockDao() {
        super();
        try {
            prop.loadFromXML(new FileInputStream("resources/query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public int getCurrentStockPrice(int characterId, Connection conn) {
        ResultSet rset = null;
        PreparedStatement pstmt = null;
        int stockPrice = 500; // 기본값
        
        String sql = prop.getProperty("getStockPrice");
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, characterId);
            
            rset = pstmt.executeQuery();
            if(rset.next()) {
                stockPrice = rset.getInt("PRICE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return stockPrice;
    }
    
    public int updateStockPrice(int characterId, int newPrice, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("updateStockPrice");
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, newPrice);
            pstmt.setInt(2, characterId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
    
    public int initializeStock(int characterId, Connection conn) {
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("initializeStock");
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, characterId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }
    
    public ArrayList<Stock> selectStockList(int characterId, Connection conn){
        ResultSet rset = null;
        ArrayList<Stock> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("selectstock");
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, characterId);
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                Stock s = new Stock();
                s.setCharNo(rset.getInt("CHARACTER_ID"));
                s.setPrice(rset.getInt("PRICE"));
                list.add(s);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return list;
    }
}