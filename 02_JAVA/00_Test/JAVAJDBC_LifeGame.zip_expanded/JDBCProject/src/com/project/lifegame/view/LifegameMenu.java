package com.project.lifegame.view;
import static com.project.lifegame.common.UiTemplate.*;
import static com.project.lifegame.view.GameEndHandler.*;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.project.lifegame.model.vo.LifeCharacter;

public class LifegameMenu {
	private LifeCharacter life;
    private Scanner sc;
    private boolean isGameEnded = false;  
    
    public LifegameMenu(LifeCharacter life) {
        super();
        this.life = life;
        this.sc = new Scanner(System.in);
    }

	public void gameMain() {
		 while(true) {
			String menuName = "인생게임";
			menuHeader(menuName, life);
			
			System.out.println("1. 일하기");
			System.out.println("2. 자기개발");
			System.out.println("3. 투자");
			System.out.println("4. 쇼핑");
			System.out.println("5. 상세 정보");
			System.out.println("9. 종료");
			line();
			boolean valid = false;
			int sel=-1;
			while (!valid) {
				System.out.print("메뉴 선택 : ");
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
            case 1:  
            	isGameEnded = new WorkMenu(life, sc).showMenu(); 
                if (isGameEnded) {
                    return;
                }
                break;
            case 2:  
            	isGameEnded = new SelfDevelopMenu(life, sc).showMenu(); 
                if (isGameEnded) {
                    return;
                }
                break;
            case 3: new InvestMenu(life, sc).showMenu(); break;
            case 4: new ShoppingMenu(life, sc).showMenu();; break;
            case 5: showStatistics(); 
            	break;
            case 9: 
            	handleGameEnd(life, "게임 중단",  sc);
            	sc.nextLine();
                return;
            default: 
                System.out.println("잘못된 입력입니다.");
			}
		 }
	}
	public void showStatistics() {
		line();
        line();
        System.out.println(basicStat(life));
        System.out.println();
        System.out.println(abilityStat(life));
        System.out.println();
        System.out.println(fullExperienceStat(life));
        System.out.println();
        System.out.println(investmentStat(life));
        sc.nextLine();
	}
}