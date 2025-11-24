package com.project.lifegame.view;

import static com.project.lifegame.common.UiTemplate.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.project.lifegame.model.vo.LifeCharacter;
import com.project.lifegame.controller.LifeCharacterController;

public class ShoppingMenu {
    private LifeCharacter life;
    private Scanner sc;
    private LifeCharacterController lcc;
    
    public ShoppingMenu(LifeCharacter life, Scanner sc) {
        this.life = life;
        this.sc = sc;
        this.lcc = new LifeCharacterController();
    }
    
    public void showMenu() {
        while(true) {
            String menuName = "쇼핑";
            menuHeader(menuName, life);
            
            System.out.println("1. 책 구매 - 가격: " + formatMoney(550) + ", 효과: 지능 +1");
            System.out.println("2. 영양제 - 가격: " + formatMoney(3000) + ", 효과: 체력 +2");
            System.out.println("3. 행운의 부적 - 가격: " + formatMoney(500) + ", 효과: 운 +1");
            System.out.println("4. 로또 - 가격: " + formatMoney(1000) + ", 당첨금: " + formatMoney(50000));
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
            System.out.println("\n");
            sc.nextLine();
            
            switch(sel) {
                case 1: buyItem("책", 550, 1, 0, 0); break;
                case 2: buyItem("영양제", 3000, 0, 2, 0); break;
                case 3: buyItem("행운의 부적", 500, 0, 0, 1); break;
                case 4: buyLotto(); break;
                case 0: return;
                default: System.out.println("잘못된 입력입니다.");
            }
            
            if(sel != 0) {
                System.out.println("Enter 키를 눌러 계속하세요...");
                sc.nextLine();
            }
        }
    }
    
    private void buyItem(String itemName, int price, int intBonus, int stamBonus, int luckBonus) {
        System.out.println("\n=== " + itemName + " 구매 ===");
        System.out.println("개당 가격: " + formatMoney(price));
        System.out.println("현재 자금: " + formatMoney(life.getMoney()));
        
        boolean valid = false;
        int cnt = -1;
        while (!valid) {
            System.out.print("구매 수량: ");
            try {
                cnt = sc.nextInt();
                if(cnt <= 0) {
                    System.out.println("1개 이상 입력해주세요.");
                    continue;
                }
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력해주세요.");
                sc.nextLine();
            }
        }
        sc.nextLine();
        
        int totalCost = price * cnt;
        if(!life.canAfford(totalCost)) {
            System.out.printf("자금이 부족합니다. (필요: %s, 보유: %s)\n", formatMoney(totalCost), formatMoney(life.getMoney()));
            return;
        }

        int prevInt = life.getIntelligence();
        int prevStam = life.getStamina();
        int prevLuck = life.getLuck();

        life.buyItems(itemName, price, cnt, intBonus, stamBonus, luckBonus);
        lcc.updateShopping(life);

        System.out.println("\n" + itemName + " " + cnt + "개를 구매했습니다!");
        System.out.println("총 지출: " + formatMoney(totalCost));

        if(intBonus > 0) {
            System.out.println("지능: " + prevInt + " → " + life.getIntelligence() + 
                             " (+" + (intBonus * cnt) + ")");
        }
        if(stamBonus > 0) {
            System.out.println("체력: " + prevStam + " → " + life.getStamina() + 
                             " (+" + (stamBonus * cnt) + ")");
        }
        if(luckBonus > 0) {
            System.out.println("운: " + prevLuck + " → " + life.getLuck() + 
                             " (+" + (luckBonus * cnt) + ")");
        }

        System.out.println("남은 자금: " + formatMoney(life.getMoney()));
    }
    
    private void buyLotto() {
        System.out.println("\n=== 로또 구매 ===");
        System.out.println("가격: " + formatMoney(1000));
        System.out.println("현재 자금: " + formatMoney(life.getMoney()));
        System.out.println("당첨 확률: 0.5% + (운 × 0.05%)");
        System.out.println("당첨금: " + formatMoney(50000));
        
        if(!life.canAfford(1000)) {
            System.out.printf("자금이 부족합니다. (필요: %s, 보유: %s)\n", formatMoney(1000), formatMoney(life.getMoney()));
            return;
        }
        
        System.out.print("로또를 구매하시겠습니까? (y/n): ");
        String confirm = sc.nextLine().trim().toLowerCase();
        
        if(!confirm.equals("y") && !confirm.equals("yes")) {
            System.out.println("구매를 취소했습니다.");
            return;
        }
        
        System.out.println("\n🎲 추첨 중...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        boolean won = life.playLotto(1000);
        
        if(won) {
            System.out.println("🎉 당첨! 상금 " + formatMoney(50000) + " 획득!");
            System.out.println("순이익: " + formatMoney(49000));
        } else {
            System.out.println("💔 꽝입니다...");
        }
        
        System.out.println("현재 자금: " + formatMoney(life.getMoney()));
        
        lcc.updateShopping(life);
    }
}