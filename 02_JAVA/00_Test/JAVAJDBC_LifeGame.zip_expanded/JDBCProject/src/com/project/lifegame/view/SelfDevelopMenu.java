package com.project.lifegame.view;

import static com.project.lifegame.common.UiTemplate.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.project.lifegame.model.vo.LifeCharacter;
import com.project.lifegame.controller.LifeCharacterController;

public class SelfDevelopMenu {
    private LifeCharacter life;
    private Scanner sc;
    private LifeCharacterController lcc;
    
    public SelfDevelopMenu(LifeCharacter life, Scanner sc) {
        this.life = life;
        this.sc = sc;
        this.lcc = new LifeCharacterController();
    }
    
    public boolean showMenu() {
        while(true) {
            String menuName = "자기개발";
            menuHeader(menuName, life);
            System.out.println("1. 독서실 공부 - 지출: 300만원, 효과: 지능 +1, 체력 -1");
            System.out.println("2. 학원 다니기 - 지출: 2,100만원, 효과: 지능 +11, 체력 -5");
            System.out.println("3. 헬스 다니기 - 지출: 300만원, 효과: 지능 -1, 체력 +2");
            System.out.println("4. PT 받기 - 지출: 3,200만원, 효과: 지능 -1, 체력 +22");
            System.out.println("5. 동전 줍기 - 수입: 1만원, 효과: 지능 -1, 체력 -2, 운 +1");
            
            System.out.println();
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
            System.out.println("\n");
            
            if (sel == 0) return false;
            
            String activity = getActivity(sel);
            if (activity != null) {
                return executeSelfDevelop(activity);
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
    
    private String getActivity(int sel) {
        switch(sel) {
            case 1: return "독서실 공부";
            case 2: return "학원 다니기";
            case 3: return "헬스 다니기";
            case 4: return "PT 받기";
            case 5: return "동전 줍기";
            default: return null;
        }
    }
    
    private boolean executeSelfDevelop(String activity) {
        int cost = getSelfDevelopCost(activity);
        
        if (cost > 0 && !life.canAfford(cost)) {
            System.out.println("자금이 부족합니다. (필요: " + formatMoney(cost) + ", 보유: " + formatMoney(life.getMoney()) + ")");
            System.out.println("Enter 키를 눌러 계속하세요...");
            sc.nextLine();
            return false;
        }
        
        int previousAge = life.getAge();
        int previousMoney = life.getMoney();
        
        life.selfDevelop(activity);
        
        if (life.isDead()) {
            return GameEndHandler.handleGameEnd(life, "체력이 0이 되어 사망했습니다! 게임 오버", sc);
        }
        
        lcc.updateCharacter(life);
        
        line();
        System.out.println(activity + "을(를) 실행하여 1년이 지났습니다!");
        
        int moneyChange = life.getMoney() - previousMoney;
        if (moneyChange > 0) {
            System.out.println("수입: +" + moneyChange + "만원");
        } else if (moneyChange < 0) {
            System.out.println("지출: " + Math.abs(moneyChange) + "만원");
        }
        
        System.out.println("나이: " + previousAge + "세 → " + life.getAge() + "세");
        System.out.println("현재 자산: " + formatMoney(life.getMoney()));
        line();
        
        if (life.isOldEnough()) {
            return GameEndHandler.handleGameEnd(life, "100세가 되어 인생을 완주했습니다! 축하합니다!", sc);
        }
        
        System.out.println("Enter 키를 눌러 계속하세요...");
        sc.nextLine();
        return false;
    }
    
    private int getSelfDevelopCost(String activity) {
        switch(activity) {
            case "독서실 공부": return 300;
            case "학원 다니기": return 2100;
            case "헬스 다니기": return 300;
            case "PT 받기": return 3200;
            case "동전 줍기": return -1;
            default: return 0;
        }
    }
}