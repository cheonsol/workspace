package com.project.lifegame.model.vo;

public class StockBusiness {
    
    public static int calculateNewPrice(int currentPrice) {
        double changeRate = -0.10 + (Math.random() * 0.21);
        return Math.max(1, (int)(currentPrice * (1 + changeRate)));
    }
    
    public static int getDefaultPrice() {
        return 500;
    }
}