package com.project.lifegame.service;

import java.sql.Connection;
import static com.project.lifegame.common.LifegameTemplate.*;
import com.project.lifegame.model.dao.UserDao;
import com.project.lifegame.model.vo.User;

public class UserService {
	private UserDao ud ;
	public UserService() {
		super();
		ud = new UserDao();
	}
	
	public boolean loginUser(User u) {
		Connection conn = getConnection();
		boolean islogin = ud.loginUser(u, conn);
		close(conn);
		return islogin;
	}
	
	
	public int signUpUser(User u) {
		Connection conn = getConnection();
		int result = ud.signUpUser(u, conn);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
}
