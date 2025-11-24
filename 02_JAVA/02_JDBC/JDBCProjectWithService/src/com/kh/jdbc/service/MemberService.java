package com.kh.jdbc.service;

//static으로 import시 static메서드를 직접 가져와서 사용할 수 있음.
import static com.kh.jdbc.common.JDBCTemplate.close;
import static com.kh.jdbc.common.JDBCTemplate.commit;
import static com.kh.jdbc.common.JDBCTemplate.getConnection;
import static com.kh.jdbc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.model.dao.MemberDao;
import com.kh.jdbc.model.vo.Member;

/*
 * Service
 * 트랜잭션 관리와같은 비즈니스 로직을 처리하는 계층, Dao와 Contoller의 중간역할
 */
public class MemberService {
	public MemberService() {
		super();
	}

	public int insertMember(Member m) {
		Connection conn = getConnection();
		
		int result = new MemberDao().insertMember(m, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public List<Member> selectMemberList(){
		Connection conn = getConnection();
		
		List<Member> list = new MemberDao().selectMemberList(conn);
		close(conn);
		
		return list;
	}
	
	public int updateMember(Member m) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updateMember(m, conn);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int deleteMember(Member m) {
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(m, conn);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public ArrayList<Member> memberSearchByName(String keyword){
		Connection conn = getConnection();
		ArrayList<Member> list = new MemberDao().memberSearchByName(keyword, conn);
		close(conn);
		return list;
	}
}
