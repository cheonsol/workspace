package com.project.lifegame.controller;
import static com.project.lifegame.view.DisplayMsg.*;
import com.project.lifegame.model.vo.User;
import com.project.lifegame.service.UserService;
import com.project.lifegame.view.MainMenu;

public class UserController {
	private UserService us;
	public UserController() {
		super();
		us = new UserService();
	}
	
	public void loginUser(String userId, String userPw) {
		User u = new User(userId, userPw);
		boolean result = us.loginUser(u);
		
		if(result) {
			displaySuccess(userId + "님 환영합니다!");
			new MainMenu(userId).gameLobby();
		} else {
			displayFail("로그인에 실패하였습니다.");
		}
	}
	
	
	public void signUpUser(String userId, String userPw) {
	    User u = new User(userId, userPw);
	    int result = us.signUpUser(u);
	    
	    if(result > 0) {
	        new AchievementController().initUserAchievements(userId);
	        displaySuccess("회원가입이 완료되었습니다.");
	    } else {
	        displayFail("회원가입에 실패하였습니다.");
	    }
	}
}
