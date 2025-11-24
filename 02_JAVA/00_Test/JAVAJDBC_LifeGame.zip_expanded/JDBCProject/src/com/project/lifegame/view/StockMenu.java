package com.project.lifegame.view;
import static com.project.lifegame.common.UiTemplate.formatMoney;
import static com.project.lifegame.common.UiTemplate.line;
import static com.project.lifegame.common.UiTemplate.menuHeader;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.project.lifegame.controller.LifeCharacterController;
import com.project.lifegame.controller.StockController;
import com.project.lifegame.model.vo.LifeCharacter;

public class StockMenu {
    private Scanner sc;
    private LifeCharacter life;
    private StockController stc;
    private LifeCharacterController lcc;

    public StockMenu(Scanner sc, StockController stc, LifeCharacter life) {
        this.sc = sc;
        this.life = life;
        this.stc = stc;
        this.lcc = new LifeCharacterController();
    }

    public void showMenu() {
        while(true) {
            String menuName = "주식";
            menuHeader(menuName, life);

            int currentPrice = stc.getCurrentStockPrice(life.getcharacterId());
            System.out.printf("현재 주식 가격: %s\n", formatMoney(currentPrice));
            System.out.printf("내가 가진 주식 수: %d주\n", life.getStockCount());
            System.out.printf("주식 총 가치: %s\n", formatMoney(life.getStockCount() * currentPrice));
            line();
            
            System.out.println("1. 주식 구매");
            System.out.println("2. 주식 판매");
            System.out.println("0. ← 돌아가기");
            System.out.print("메뉴 입력: ");
            
            int sel = sc.nextInt();
            sc.nextLine();
            
            switch(sel) {
                case 1: 
                    buyStock(currentPrice);
                    break;
                case 2: 
                    sellStock(currentPrice);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
            
            System.out.println("\nEnter 키를 눌러 계속하세요...");
            sc.nextLine();
        }
    }
    
    private void buyStock(int currentPrice) {
        System.out.println("\n=== 주식 구매 ===");
        System.out.printf("현재 주식 가격: %s\n", formatMoney(currentPrice));
        System.out.printf("보유 자금: %s\n", formatMoney(life.getMoney()));
        System.out.printf("최대 구매 가능 수량: %d주\n", life.getMoney() / currentPrice);
        
        int buyStockCnt = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print("구매할 주식 수: ");
            try {
                buyStockCnt = sc.nextInt();
                valid = true; 
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                sc.nextLine(); 
            }
        }
        sc.nextLine();
        
        if (buyStockCnt <= 0) {
            System.out.println("구매 수량은 1 이상이어야 합니다.");
            return;
        }
        
        int totalCost = buyStockCnt * currentPrice;
        if (!life.canAfford(totalCost)) {
            System.out.printf("자금이 부족합니다. (필요: %s, 보유: %s)\n", formatMoney(totalCost), formatMoney(life.getMoney()));
            return;
        }
        
        life.buyStock(buyStockCnt, currentPrice);
        lcc.updateStockTrade(life);
        
        System.out.printf("주식 %d주를 %s에 구매했습니다!\n", buyStockCnt, formatMoney(totalCost));
        System.out.printf("현재 보유 주식: %d주\n", life.getStockCount());
        System.out.printf("남은 자금: %s\n", formatMoney(life.getMoney()));
    }
    
    private void sellStock(int currentPrice) {
        if (life.getStockCount() <= 0) {
            System.out.println("보유한 주식이 없습니다.");
            return;
        }
        
        System.out.println("\n=== 주식 판매 ===");
        System.out.printf("현재 주식 가격: %s\n", formatMoney(currentPrice));
        System.out.printf("보유 주식 수: %d주\n", life.getStockCount());
        System.out.printf("총 가치: %s\n", formatMoney(life.getStockCount() * currentPrice));
        
        int sellStockCnt = -1;
        boolean valid = false;
        while (!valid) {
            System.out.print("판매할 주식 수: ");
            try {
                sellStockCnt = sc.nextInt();
                valid = true; 
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                sc.nextLine(); 
            }
        }
        sc.nextLine();
        
        if (sellStockCnt <= 0) {
            System.out.println("판매 수량은 1 이상이어야 합니다.");
            return;
        }
        
        if (life.getStockCount() < sellStockCnt) {
            System.out.printf("보유 수량보다 더 많은 수량을 판매할 수 없습니다. (보유: %d주)\n", 
                            life.getStockCount());
            return;
        }
        
        int totalEarning = sellStockCnt * currentPrice;
        life.sellStock(sellStockCnt, currentPrice);
        lcc.updateStockTrade(life);
        
        System.out.printf("주식 %d주를 %s에 판매했습니다!\n", sellStockCnt, formatMoney(totalEarning));
        System.out.printf("현재 보유 주식: %d주\n", life.getStockCount());
        System.out.printf("현재 자금: %s\n", formatMoney(life.getMoney()));
    }
}