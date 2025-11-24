package com.project.lifegame.controller;

import static com.project.lifegame.view.DisplayMsg.*;

import java.util.List;

import com.project.lifegame.model.vo.LifeCharacter;
import com.project.lifegame.service.LifeCharacterService;
import com.project.lifegame.view.LifegameMenu;

public class LifeCharacterController {
    private LifeCharacterService lcs = new LifeCharacterService();
    
    public LifeCharacterController() {
        super();
    }
    
    public void createNewCharacter(String characterName, String accessId) {
        LifeCharacter life = new LifeCharacter(characterName, accessId);
        int result = lcs.createNewCharacter(life);
        if(result > 0) {
            displaySuccess("인생게임을 시작합니다.");
            new LifegameMenu(life).gameMain();
        } else {
            displayFail("게임 시작에 실패하였습니다.");
        }
    }
    
    public void updateCharacter(LifeCharacter life) {
        int result = lcs.execute(life);
        if(result > 0) {
            displaySuccess("실행을 성공적으로 마쳤습니다.");
            new StockController().updateStockPrice(life.getcharacterId());
        } else {
            displayFail("실행에 실패하였습니다.");
        }
    }
    
    public void updateStockTrade(LifeCharacter life) {
        int result = lcs.executeStockTrade(life);
        if(result > 0) {
            displaySuccess("거래를 성공적으로 마쳤습니다.");
        } else {
            displayFail("거래에 실패하였습니다.");
        }
    }
    
    public void updateRealEstate(LifeCharacter life) {
        int result = lcs.updateRealEstate(life);
        if(result > 0) {
            displaySuccess("부동산 구매가 완료되었습니다.");
        } else {
            displayFail("부동산 구매에 실패하였습니다.");
        }
    }
    
    public void updateShopping(LifeCharacter life) {
        int result = lcs.updateShopping(life);
        if(result > 0) {
            displaySuccess("구매가 완료되었습니다.");
        } else {
            displayFail("구매에 실패하였습니다.");
        }
    }
    
    public void showRank() {
        List<LifeCharacter> rankList = lcs.showRank();
        if(rankList.isEmpty()) {
            displayNoData("랭킹 조회 결과가 없습니다.");
        } else {
            displayRank(rankList, " 랭킹 ");
        }
    }
}