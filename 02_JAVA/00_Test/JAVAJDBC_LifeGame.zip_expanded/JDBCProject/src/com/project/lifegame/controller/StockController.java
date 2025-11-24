package com.project.lifegame.controller;
import static com.project.lifegame.view.DisplayMsg.displayFail;

import com.project.lifegame.service.StockService;

public class StockController {
    private StockService ss;
    
    public StockController() {
        super();
        this.ss = new StockService();
    }
    
    public void updateStockPrice(int characterId) {
    	int result = ss.updateStockPrice(characterId);
    	if(result <= 0) {
			displayFail("주식 변동에 실패했습니다.");
		} 
    }
    
    public int getCurrentStockPrice(int characterId) {
        return ss.getCurrentStockPrice(characterId);
    }
    
   
}