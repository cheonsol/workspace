package com.project.lifegame.view;

import static com.project.lifegame.common.UiTemplate.formatMoney;
import static com.project.lifegame.common.UiTemplate.line;
import static com.project.lifegame.common.UiTemplate.menuHeader;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.project.lifegame.controller.LifeCharacterController;
import com.project.lifegame.controller.StockController;
import com.project.lifegame.model.vo.LifeCharacter;

public class InvestMenu {
    private LifeCharacter life;
    private Scanner sc;
    private StockController stc = new StockController();
    private LifeCharacterController lcc = new LifeCharacterController();

    public InvestMenu(LifeCharacter life, Scanner sc) {
        this.life = life;
        this.sc = sc;
    }

    public void showMenu() {
        while(true) {
            String menuName = "투자";
            menuHeader(menuName, life);

            System.out.println("1. 주식");
            System.out.printf("        현재 가격: %s\n", formatMoney(stc.getCurrentStockPrice(life.getcharacterId())));
            System.out.println("        변동 -10% ~ +11%");
            System.out.println("=== 부동산 투자");
            System.out.println("2. 아파트 구매");
            System.out.println("        구매가: " + formatMoney(5000));
            System.out.println("        연수익: " + formatMoney(300));
            System.out.println("3. 빌딩 구매");
            System.out.println("        구매가: " + formatMoney(30000));
            System.out.println("        연수익: " + formatMoney(2000));
            System.out.println("4. 호텔 구매");
            System.out.println("        구매가: " + formatMoney(100000));
            System.out.println("        연수익: " + formatMoney(5000));
            System.out.println("0. ← 돌아가기");
            line();
  
            boolean valid = false;
            int sel = -1;
            while (!valid) {
                System.out.print("메뉴 입력: ");
                try {
                    sel = sc.nextInt();
                    valid = true; 
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    sc.nextLine(); 
                }
            }
            sc.nextLine();
            
            switch(sel) {
                case 1: 
                    new StockMenu(sc, stc, life).showMenu();
                    break;
                case 2: 
                    buyRealEstate("아파트");
                    break;
                case 3: 
                    buyRealEstate("빌딩");
                    break;
                case 4: 
                    buyRealEstate("호텔");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
            
            if(sel != 1 && sel != 0) {
                System.out.println("Enter 키를 눌러 계속하세요...");
                sc.nextLine();
            }
        }
    }
    
    private void buyRealEstate(String type) {
        System.out.println("\n=== " + type + " 구매 ===");
        
        int[] prices = {5000, 30000, 100000};
        int[] incomes = {300, 2000, 5000};
        String[] types = {"아파트", "빌딩", "호텔"};
        
        int index = -1;
        for(int i = 0; i < types.length; i++) {
            if(types[i].equals(type)) {
                index = i;
                break;
            }
        }
        
        if(index == -1) return;
        
        System.out.println("구매가: " + formatMoney(prices[index]));
        System.out.println("연간 수익: " + formatMoney(incomes[index]));
        System.out.println("현재 자금: " + formatMoney(life.getMoney()));
        
        if(!life.canBuyRealEstate(type)) {
            if((type.equals("아파트") && life.isHasApartment()) ||
               (type.equals("빌딩") && life.isHasBuilding()) ||
               (type.equals("호텔") && life.isHasHotel())) {
                System.out.println("이미 " + type + "을(를) 보유하고 있습니다.");
            } else {
                System.out.printf("자금이 부족합니다. (필요: %s, 보유: %s)\n", 
                    formatMoney(prices[index]), formatMoney(life.getMoney()));
            }
            return;
        }
        
        life.buyRealEstate(type);
        lcc.updateRealEstate(life);
        
        System.out.println(type + "을(를) 성공적으로 구매했습니다!");
        System.out.println("남은 자금: " + formatMoney(life.getMoney()));
    }
}