package com.project.lifegame.model.vo;

import java.util.ArrayList;
import java.util.List;

public class AchievementChecker {
    
    public static List<Integer> checkAllAchievements(LifeCharacter life, String endReason) {
        List<Integer> newAchievements = new ArrayList<>();
        
        if (checkEarlyDeath(life, endReason)) newAchievements.add(1);
        if (checkBankrupt(life)) newAchievements.add(2);
        if (checkRealEstateMogul(life)) newAchievements.add(3);
        if (checkPartTimeWorker(life)) newAchievements.add(4);
        if (checkEmployeeOnly(life)) newAchievements.add(5);
        if (checkMillionaire(life)) newAchievements.add(6);
        if (checkBillionaire(life)) newAchievements.add(7);
        if (checkGenius(life)) newAchievements.add(8);
        if (checkInvincible(life)) newAchievements.add(9);
        if (checkLaborGod(life)) newAchievements.add(10);
        if (checkTrainerMaster(life)) newAchievements.add(11);
        if (checkSportsStar(life)) newAchievements.add(12);
        if (checkLuckyGod(life)) newAchievements.add(13);
        if (checkStockKing(life)) newAchievements.add(14);
        
        return newAchievements;
    }
    
    private static boolean checkEarlyDeath(LifeCharacter life, String endReason) {
        return endReason.contains("사망") && life.getAge() < 30;
    }
    
    private static boolean checkBankrupt(LifeCharacter life) {
        return life.getTotalAsset() == 0;
    }
    
    private static boolean checkRealEstateMogul(LifeCharacter life) {
        return life.isHasApartment() && life.isHasBuilding() && life.isHasHotel();
    }
    
    private static boolean checkPartTimeWorker(LifeCharacter life) {
        return life.getPartTimeExp() >= 50;
    }
    
    private static boolean checkEmployeeOnly(LifeCharacter life) {
        return life.getEmployeeExp() >= 50 && 
               life.getExecutiveExp() == 0 && 
               life.getEntrepreneurExp() == 0;
    }
    
    private static boolean checkMillionaire(LifeCharacter life) {
        return life.getMoney() >= 100000;
    }
    
    private static boolean checkBillionaire(LifeCharacter life) {
        return life.getTotalAsset() >= 1000000;
    }
    
    private static boolean checkGenius(LifeCharacter life) {
        return life.getIntelligence() >= 300;
    }
    
    private static boolean checkInvincible(LifeCharacter life) {
        return life.getStamina() >= 600;
    }
    
    private static boolean checkLaborGod(LifeCharacter life) {
        return life.getLaborerExp() >= 75;
    }
    
    private static boolean checkTrainerMaster(LifeCharacter life) {
        return life.getTrainerExp() >= 40;
    }
    
    private static boolean checkSportsStar(LifeCharacter life) {
        return life.getAthleteExp() >= 30 && life.getMoney() >= 1500000;
    }
    
    private static boolean checkLuckyGod(LifeCharacter life) {
        return life.getLuck() >= 1990;
    }
    
    private static boolean checkStockKing(LifeCharacter life) {
        return life.getStockValue() >= 1000000;
    }
}