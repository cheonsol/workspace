package com.project.lifegame.service;
import static com.project.lifegame.common.LifegameTemplate.close;
import static com.project.lifegame.common.LifegameTemplate.commit;
import static com.project.lifegame.common.LifegameTemplate.getConnection;
import static com.project.lifegame.common.LifegameTemplate.rollback;

import java.sql.Connection;

import com.project.lifegame.model.dao.StockDao;

public class StockService {
    private StockDao sd;

    public StockService() {
        super();
        sd = new StockDao();
    }
    
    public int getCurrentStockPrice(int characterId) {
        Connection conn = getConnection();
        int stockPrice = sd.getCurrentStockPrice(characterId, conn);
        close(conn);
        return stockPrice;
    }
    
    public int updateStockPrice(int characterId) {
        Connection conn = getConnection();
        int currentPrice = getCurrentStockPrice(characterId);
        
        // 변동 알고리즘 적용 (-10% ~ +11%)
        double changeRate = -0.10 + (Math.random() * 0.21);
        int newPrice = Math.max(1, (int)(currentPrice * (1 + changeRate))); // 최소 1만원
        
        int result = sd.updateStockPrice(characterId, newPrice, conn);
        if(result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
        return result;
    }
    
    public int initializeStock(int characterId) {
        Connection conn = getConnection();
        int result = sd.initializeStock(characterId, conn);
        if(result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        close(conn);
        return result;
    }
}