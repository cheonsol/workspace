package com.project.lifegame.model.vo;
import static com.project.lifegame.common.UiTemplate.formatMoney;

public class LifeCharacter {
    private int characterId;
    private String characterName;
    private String userId;
    private int age;
    private int money;
    private int intelligence;
    private int stamina;
    private int luck;
    
    private int partTimeExp;
    private int employeeExp;
    private int executiveExp;
    private int entrepreneurExp;
    private int laborerExp;
    private int supervisorExp;
    private int trainerExp;
    private int athleteExp;
    
    private int stockCount;
    private int stockValue;
    private boolean hasApartment;
    private boolean hasBuilding;
    private boolean hasHotel;

    public LifeCharacter(int characterId) {
        this.characterId = characterId;
    }

    public LifeCharacter(String characterName, String userId) {
        this.characterName = characterName;
        this.userId = userId;
        this.age = 20;
        this.money = 1000;
        this.intelligence = 5;
        this.stamina = 3;
        this.luck = 5;
        this.partTimeExp = 0;
        this.employeeExp = 0;
        this.executiveExp = 0;
        this.entrepreneurExp = 0;
        this.laborerExp = 0;
        this.supervisorExp = 0;
        this.trainerExp = 0;
        this.athleteExp = 0;
        this.stockCount = 0;
        this.stockValue = 0;
        this.hasApartment = false;
        this.hasBuilding = false;
        this.hasHotel = false;
    }

    public LifeCharacter(int charNo, String characterName, String userId, int age, int money, int intelligence,
            int stamina, int luck, int partTimeExp, int employeeExp, int executiveExp, int entrepreneurExp,
            int laborerExp, int supervisorExp, int trainerExp, int athleteExp, int stockCount, int stockValue,
            boolean hasApartment, boolean hasBuilding, boolean hasHotel) {
        this.characterId = charNo;
        this.characterName = characterName;
        this.userId = userId;
        this.age = age;
        this.money = money;
        this.intelligence = intelligence;
        this.stamina = stamina;
        this.luck = luck;
        this.partTimeExp = partTimeExp;
        this.employeeExp = employeeExp;
        this.executiveExp = executiveExp;
        this.entrepreneurExp = entrepreneurExp;
        this.laborerExp = laborerExp;
        this.supervisorExp = supervisorExp;
        this.trainerExp = trainerExp;
        this.athleteExp = athleteExp;
        this.stockCount = stockCount;
        this.stockValue = stockValue;
        this.hasApartment = hasApartment;
        this.hasBuilding = hasBuilding;
        this.hasHotel = hasHotel;
    }

    public boolean canWork(String jobName) {
        switch(jobName) {
            case "노가다": return this.stamina >= 5;
            case "회사원": return this.intelligence >= 10 && this.stamina >= 5;
            case "회사 임원": return this.employeeExp >= 30 && this.intelligence >= 50;
            case "창업자": return (this.employeeExp >= 55 && this.intelligence >= 70) || 
                              (this.employeeExp >= 10 && this.intelligence >= 150);
            case "현장 책임자": return this.stamina >= 60 && this.laborerExp >= 20;
            case "트레이너": return this.stamina >= 30 && this.intelligence >= 10;
            case "운동선수": return this.stamina >= 60 && this.intelligence >= 20;
            default: return true;
        }
    }

    public int calculateJobIncome(String jobName) {
        switch(jobName) {
            case "아르바이트": return 1000 + (this.partTimeExp * 50);
            case "노가다": return 6000 + (this.laborerExp * 50);
            case "회사원": return 3000 + (this.employeeExp * 100);
            case "회사 임원": return 6000 + (this.executiveExp * 250);
            case "창업자": return 20000 + (this.entrepreneurExp * 500);
            case "현장 책임자": return 9000 + (this.supervisorExp * 70);
            case "트레이너": return 2500 + (this.trainerExp * 70);
            case "운동선수": return calculateAthleteIncome();
            default: return 0;
        }
    }

    private int calculateAthleteIncome() {
        int baseIncome = 5000 + (this.athleteExp * 500);
        
        double starChance = 0.10 + (this.athleteExp * 0.01);
        if (Math.random() < starChance) {
            baseIncome += 30000;
        }
        
        if (Math.random() < 0.05) {
            baseIncome = 3000;
        }
        
        return baseIncome;
    }

    public void work(String jobName) {
        this.age++;
        this.money += calculateJobIncome(jobName);
        this.money += calculateRealEstateIncome();
        
        updateJobExperience(jobName);
        processJobEvents(jobName);
    }

    private void updateJobExperience(String jobName) {
        switch(jobName) {
            case "아르바이트":
                this.partTimeExp++;
                this.stamina = Math.max(0, this.stamina - 1);
                break;
            case "노가다":
                this.laborerExp++;
                this.stamina = Math.max(0, this.stamina - 3);
                if (Math.random() < 0.3) {
                    this.stamina = Math.max(0, this.stamina - 9);
                }
                break;
            case "회사원":
                this.employeeExp++;
                this.stamina = Math.max(0, this.stamina - 2);
                break;
            case "회사 임원":
                this.employeeExp++;
                this.executiveExp++;
                this.stamina = Math.max(0, this.stamina - 2);
                break;
            case "창업자":
                this.entrepreneurExp++;
                this.stamina = Math.max(0, this.stamina - 1);
                break;
            case "현장 책임자":
                this.supervisorExp++;
                this.stamina = Math.max(0, this.stamina - 2);
                break;
            case "트레이너":
                this.trainerExp++;
                if (this.trainerExp % 5 == 0) {
                    this.stamina = Math.max(0, this.stamina - 1);
                }
                break;
            case "운동선수":
                this.athleteExp++;
                if (Math.random() < 0.07) {
                    this.stamina = Math.max(0, this.stamina - 50);
                }
                break;
        }
    }

    private void processJobEvents(String jobName) {
        switch(jobName) {
            case "창업자":
                if (Math.random() < 0.08) {
                    bankruptcyEvent();
                }
                break;
            case "현장 책임자":
                if (Math.random() < 0.12) {
                    accidentEvent();
                }
                break;
        }
    }

    private void bankruptcyEvent() {
        this.money = 0;
        this.hasApartment = false;
        this.hasBuilding = false;
        this.hasHotel = false;
        this.stockCount = 0;
        this.stockValue = 0;
    }

    private void accidentEvent() {
        this.money = 0;
        this.hasApartment = false;
        this.hasBuilding = false;
        this.hasHotel = false;
        this.stockCount = 0;
        this.stockValue = 0;
    }

    private int calculateRealEstateIncome() {
        int income = 0;
        if (this.hasApartment) income += 300;
        if (this.hasBuilding) income += 2000;
        if (this.hasHotel) income += 5000;
        return income;
    }

    public void selfDevelop(String activity) {
        this.age++;
        this.money += calculateRealEstateIncome();
        
        switch(activity) {
            case "독서실 공부":
                if (this.money >= 300) {
                    this.money -= 300;
                    this.intelligence++;
                    this.stamina = Math.max(0, this.stamina - 1);
                }
                break;
            case "학원 다니기":
                if (this.money >= 2100) {
                    this.money -= 2100;
                    this.intelligence += 11;
                    this.stamina = Math.max(0, this.stamina - 5);
                }
                break;
            case "헬스 다니기":
                if (this.money >= 300) {
                    this.money -= 300;
                    this.intelligence = Math.max(0, this.intelligence - 1);
                    this.stamina += 2;
                }
                break;
            case "PT 받기":
                if (this.money >= 3200) {
                    this.money -= 3200;
                    this.intelligence = Math.max(0, this.intelligence - 1);
                    this.stamina += 22;
                }
                break;
            case "동전 줍기":
                this.money += 1;
                this.intelligence = Math.max(0, this.intelligence - 1);
                this.stamina = Math.max(0, this.stamina - 2);
                this.luck++;
                break;
        }
    }

    public boolean canAfford(int cost) {
        return this.money >= cost;
    }

    public void buyItems(String itemName, int price, int quantity, int intBonus, int stamBonus, int luckBonus) {
        int totalCost = price * quantity;
        if (canAfford(totalCost)) {
            this.money -= totalCost;
            this.intelligence += (intBonus * quantity);
            this.stamina += (stamBonus * quantity);
            this.luck += (luckBonus * quantity);
        }
    }

    public boolean playLotto(int price) {
        if (!canAfford(price)) {
            return false;
        }
        
        this.money -= price;
        double winChance = 0.005 + (this.luck * 0.0005);
        
        if (Math.random() < winChance) {
            this.money += 50000;
            return true;
        }
        return false;
    }

    public void buyStock(int quantity, int price) {
        int totalCost = quantity * price;
        if (canAfford(totalCost)) {
            this.money -= totalCost;
            this.stockCount += quantity;
            this.stockValue = this.stockCount * price;
        }
    }

    public void sellStock(int quantity, int price) {
        if (this.stockCount >= quantity) {
            this.money += (quantity * price);
            this.stockCount -= quantity;
            this.stockValue = this.stockCount * price;
        }
    }

    public void updateStockValue(int currentPrice) {
        this.stockValue = this.stockCount * currentPrice;
    }

    public boolean canBuyRealEstate(String type) {
        switch(type) {
            case "아파트": return !this.hasApartment && canAfford(5000);
            case "빌딩": return !this.hasBuilding && canAfford(30000);
            case "호텔": return !this.hasHotel && canAfford(100000);
            default: return false;
        }
    }

    public void buyRealEstate(String type) {
        switch(type) {
            case "아파트":
                if (canBuyRealEstate("아파트")) {
                    this.money -= 5000;
                    this.hasApartment = true;
                }
                break;
            case "빌딩":
                if (canBuyRealEstate("빌딩")) {
                    this.money -= 30000;
                    this.hasBuilding = true;
                }
                break;
            case "호텔":
                if (canBuyRealEstate("호텔")) {
                    this.money -= 100000;
                    this.hasHotel = true;
                }
                break;
        }
    }

    public int getTotalAsset() {
        return this.money + this.stockValue;
    }

    public boolean isDead() {
        return this.stamina <= 0;
    }

    public boolean isOldEnough() {
        return this.age >= 100;
    }

    public int getcharacterId() { return characterId; }
    public void setcharacterId(int characterId) { this.characterId = characterId; }
    public String getCharacterName() { return characterName; }
    public void setCharacterName(String characterName) { this.characterName = characterName; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public int getMoney() { return money; }
    public void setMoney(int money) { this.money = money; }
    public int getIntelligence() { return intelligence; }
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }
    public int getStamina() { return stamina; }
    public void setStamina(int stamina) { this.stamina = stamina; }
    public int getLuck() { return luck; }
    public void setLuck(int luck) { this.luck = luck; }
    public int getPartTimeExp() { return partTimeExp; }
    public void setPartTimeExp(int partTimeExp) { this.partTimeExp = partTimeExp; }
    public int getEmployeeExp() { return employeeExp; }
    public void setEmployeeExp(int employeeExp) { this.employeeExp = employeeExp; }
    public int getExecutiveExp() { return executiveExp; }
    public void setExecutiveExp(int executiveExp) { this.executiveExp = executiveExp; }
    public int getEntrepreneurExp() { return entrepreneurExp; }
    public void setEntrepreneurExp(int entrepreneurExp) { this.entrepreneurExp = entrepreneurExp; }
    public int getLaborerExp() { return laborerExp; }
    public void setLaborerExp(int laborerExp) { this.laborerExp = laborerExp; }
    public int getSupervisorExp() { return supervisorExp; }
    public void setSupervisorExp(int supervisorExp) { this.supervisorExp = supervisorExp; }
    public int getTrainerExp() { return trainerExp; }
    public void setTrainerExp(int trainerExp) { this.trainerExp = trainerExp; }
    public int getAthleteExp() { return athleteExp; }
    public void setAthleteExp(int athleteExp) { this.athleteExp = athleteExp; }
    public int getStockCount() { return stockCount; }
    public void setStockCount(int stockCount) { this.stockCount = stockCount; }
    public int getStockValue() { return stockValue; }
    public void setStockValue(int stockValue) { this.stockValue = stockValue; }
    public boolean isHasApartment() { return hasApartment; }
    public void setHasApartment(boolean hasApartment) { this.hasApartment = hasApartment; }
    public boolean isHasBuilding() { return hasBuilding; }
    public void setHasBuilding(boolean hasBuilding) { this.hasBuilding = hasBuilding; }
    public boolean isHasHotel() { return hasHotel; }
    public void setHasHotel(boolean hasHotel) { this.hasHotel = hasHotel; }

    @Override
    public String toString() {
        return String.format("%-15s %-15s %15s", 
            this.userId, 
            this.characterName, 
            formatMoney(getTotalAsset()));
    }
}