package com.project.lifegame.view;

import static com.project.lifegame.common.UiTemplate.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.project.lifegame.model.vo.LifeCharacter;
import com.project.lifegame.controller.LifeCharacterController;

public class WorkMenu {
    private LifeCharacter life;
    private Scanner sc;
    private LifeCharacterController lcc;
    
    public WorkMenu(LifeCharacter life, Scanner sc) {
        this.life = life;
        this.sc = sc;
        this.lcc = new LifeCharacterController();
    }
    
    public boolean showMenu() {
        while(true) {
            String menuName = "일하기";
            menuHeader(menuName, life);
            System.out.println(experienceStat(life));
            line();
            
            displayJobOptions();
            
            boolean valid = false;
            int sel = -1;
            while (!valid) {
                System.out.print("메뉴 선택: ");
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
            
            String jobName = getJobName(sel);
            if (jobName != null) {
                return executeWork(jobName);
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
    
    private void displayJobOptions() {
        System.out.println("1. 아르바이트 - 수입: " + formatMoney(life.calculateJobIncome("아르바이트")) + 
                          ", 조건: 없음 (체력 -1)");
        
        System.out.println("2. 노가다 - 수입: " + formatMoney(life.calculateJobIncome("노가다")) + 
                          ", 조건: 체력 5+ (체력 -3)" + (life.canWork("노가다") ? "" : " ❌"));
        
        System.out.println();
        System.out.println("==== 출근하기");
        
        System.out.println("3. 회사원 - 수입: " + formatMoney(life.calculateJobIncome("회사원")) + 
                          ", 조건: 지능 10+, 체력 5+ (체력 -2) " + (life.canWork("회사원") ? "" : " ❌"));
        
        System.out.println("4. 회사 임원 - 수입: " + formatMoney(life.calculateJobIncome("회사 임원")) + 
                          ", 조건: 회사원 경력 30+, 지능 50+ (체력 -2)" + (life.canWork("회사 임원") ? "" : " ❌"));
        
        System.out.println("5. 창업자 - 수입: " + formatMoney(life.calculateJobIncome("창업자")) + 
                          ", 조건: 회사원 경력 55+, 지능 70+ 또는 특수 (체력 -1)" + (life.canWork("창업자") ? "" : " ❌"));
        
        System.out.println("6. 현장 책임자 - 수입: " + formatMoney(life.calculateJobIncome("현장 책임자")) + 
                          ", 조건: 체력 60+, 노가다 경력 20+ (체력 -2)" + (life.canWork("현장 책임자") ? "" : " ❌"));
        
        System.out.println("7. 트레이너 - 수입: " + formatMoney(life.calculateJobIncome("트레이너")) + 
                          ", 조건: 체력 30+, 지능 10+" + (life.canWork("트레이너") ? "" : " ❌"));
        
        System.out.println();
        System.out.println("==== 특수");
        
        System.out.println("8. 운동선수 - 수입: " + formatMoney(life.calculateJobIncome("운동선수")) + 
                          "(변동), 조건: 체력 60+, 지능 20+" + (life.canWork("운동선수") ? "" : " ❌"));
        
        System.out.println();
        System.out.println("0. ← 돌아가기");
        line();
    }
    
    private String getJobName(int sel) {
        switch(sel) {
            case 1: return "아르바이트";
            case 2: return "노가다";
            case 3: return "회사원";
            case 4: return "회사 임원";
            case 5: return "창업자";
            case 6: return "현장 책임자";
            case 7: return "트레이너";
            case 8: return "운동선수";
            default: return null;
        }
    }

    private boolean executeWork(String jobName) {
        if (!life.canWork(jobName)) {
            System.out.println("조건을 충족하지 않았습니다.");
            System.out.println("Enter 키를 눌러 계속하세요...");
            sc.nextLine();
            return false;
        }
        
        int previousAge = life.getAge();
        int previousMoney = life.getMoney();
        
        life.work(jobName);
        
        if (life.isDead()) {
            return GameEndHandler.handleGameEnd(life, "체력이 0이 되어 사망했습니다! 게임 오버", sc);
        }
        
        lcc.updateCharacter(life);
        
        line();
        System.out.println(jobName + "으로 1년간 근무했습니다!");
        System.out.println("총 수입: " + formatMoney(life.getMoney() - previousMoney));
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
}