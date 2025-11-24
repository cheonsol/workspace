package com.project.lifegame.view;

import static com.project.lifegame.common.UiTemplate.*;

import java.util.Scanner;

import com.project.lifegame.controller.AchievementController;
import com.project.lifegame.model.vo.LifeCharacter;

public class GameEndHandler {
    public static boolean handleGameEnd(LifeCharacter life, String endReason, Scanner sc) {
        try {
            Thread.sleep(500);  
            line();
            System.out.println("🎮 " + endReason + " 🎮");
            line();
            Thread.sleep(500);
            
            System.out.println("📊 최종 기본 정보");
            System.out.println(basicStat(life));
            System.out.println();
            Thread.sleep(500);
            
            System.out.println("💪 최종 능력치");
            System.out.println(abilityStat(life));
            System.out.println();
            Thread.sleep(500);
            
            System.out.println("👔 전체 경력 정보");
            System.out.println(fullExperienceStat(life));
            System.out.println();
            Thread.sleep(500);
            
            System.out.println("💰 투자 현황");
            System.out.println(investmentStat(life));
            Thread.sleep(500);
            line();
            
            System.out.println("🏆 최종 자산: " + formatMoney(life.getTotalAsset()));
            line();
            Thread.sleep(500);
            
            new AchievementController().checkAchievements(life, endReason);
            Thread.sleep(500);
            System.out.println("게임이 종료되었습니다. Enter 키를 눌러 메인 메뉴로 돌아가세요...");
            sc.nextLine();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        return true;
    }
}