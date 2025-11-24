package com.project.lifegame.common;

import com.project.lifegame.model.vo.LifeCharacter;
import com.project.lifegame.controller.StockController;

public class UiTemplate {

    public static void line() {
        System.out.println("=====================================================================");
    }

    public static void menuHeader(String menuName, LifeCharacter life) {
        line();
        System.out.printf("%-30s | %s%n", menuName, basicStat(life));
        System.out.println(abilityStat(life));
        line();
    }

    public static String basicStat(LifeCharacter life) {
        return "이름: " + life.getCharacterName() + " | 나이: " + life.getAge() + "세";
    }

    public static String abilityStat(LifeCharacter life) {
        return String.format("지능: %d 체력: %d 운: %d 자본: %s",
            life.getIntelligence(), life.getStamina(), life.getLuck(),
            formatMoney(life.getMoney()));
    }

    public static String experienceStat(LifeCharacter life) {
        return String.format("알바 경력: %d년 회사 경력: %d년 노가다 경력: %d년",
            life.getPartTimeExp(), life.getEmployeeExp(), life.getLaborerExp());
    }

    public static String fullExperienceStat(LifeCharacter life) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("알바 경력: %d년 회사 경력: %d년 노가다 경력: %d년%n",
            life.getPartTimeExp(), life.getEmployeeExp(), life.getLaborerExp()));
        sb.append(String.format("임원 경력: %d년 창업 경력: %d년 책임자 경력: %d년%n",
            life.getExecutiveExp(), life.getEntrepreneurExp(), life.getSupervisorExp()));
        sb.append(String.format("트레이너 경력: %d년 운동선수 경력: %d년",
            life.getTrainerExp(), life.getAthleteExp()));
        return sb.toString();
    }

    public static String investmentStat(LifeCharacter life) {
        StockController stc = new StockController();
        int currentStockPrice = stc.getCurrentStockPrice(life.getcharacterId());
        int totalStockValue = life.getStockCount() * currentStockPrice;

        return String.format("아파트: %s 빌딩: %s 호텔: %s | 주식: %d주 (가치: %s)",
            life.isHasApartment() ? "보유" : "미보유",
            life.isHasBuilding() ? "보유" : "미보유",
            life.isHasHotel() ? "보유" : "미보유",
            life.getStockCount(),
            formatMoney(totalStockValue));
    }

    public static String calculateJobIncome(String jobName, int baseIncome, int experience, int bonus) {
        int totalIncome = baseIncome + (experience * bonus);
        return String.format("수입: %s (기본 %s + 경력 %d×%d)",
            formatMoney(totalIncome), formatMoney(baseIncome), experience, bonus);
    }

    public static String formatMoney(int money) {
        String result;
        if (money >= 10000) {
            int eok = money / 10000;
            int remain = money % 10000;

            if (remain == 0) {
                result = eok + "억";
            } else {
                result = String.format("%d억 %,d만원", eok, remain);
            }
        } else {
            result = String.format("%,d만원", money);
        }
        return String.format("%15s", result);
    }
}