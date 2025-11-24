package com.kh.jdbc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.kh.jdbc.model.vo.Member;

/*
 * Dao(Data Access Object) : db에 직접적으로 접근해서 사용자의 요청에 맞게 sql문을 실행 후 결과를 반환
 */
public class MemberDao {

	public int insertMember(Member m) {
		//db에 member를 insert
		//JDBC를 사용
		/*
		 * JDBC사용순서
		 * 1) JDBC Driver등록 : JDBC내의 다양한 인터페이스가 특정 DBMS가 제공하는 클래스에 의해서 구현됨.(사용할 수 있도록 등록)
		 * 2) Connection 생성 : 연결하고자하는 db정보를 입력해서 해당 db와 연결할 수 있는 객체 생성
		 * 3) [Prepared]Statement 생성 : Connection객체를 이용해서 생성, sql문을 실행하고 결과를 받아주는 객체
		 * 4) sql문을 전달해서 실행 : Statement객체를 이용해서 sql문 실행
		 * 5) 결과받기 -> select문 실행 -> ResultSet객체(조회된 결과를 담아줌) -> 6_1
		 *           -> DML(insert, update, delete) -> 처리된 행 수  -> 6_2
		 * 6_1) ResultSet객체에 담겨있는 데이터를 하나씩 추출해서 자바메모리에 담아 사용
		 * 6_2) 트랜잭션 처리(성공했으면 commit, 실패했다면 rollback 실행)
		 * 7) 다 사용한 JDBC객체를 반드시 반납(close -> 생성의 역순)
		 */
		
		//insertMember -> insert -> 처리된 행 수(int)
		
		//필요한 변수 세팅
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//실행할 sql(sql뒤에 ;없어야함!!!)
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");
			conn.setAutoCommit(false);
			
			//아직 미완성 sql문으로 ?의 값을 전부 채워야함
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	//회원목록을 반환하는 메서드
	public ArrayList<Member> selectMemberList(){
		//select문(여러개) -> ResultSet -> ArrayList담기
		
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<>(); //비어있는 상태
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "SELECT * FROM MEMBER";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");
			
			//완성된 sql
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
				m.setUserNo(rset.getInt("USER_NO"));
				m.setUserId(rset.getString("USER_ID"));
				m.setUserPwd(rset.getString("USER_PWD"));
				m.setUserName(rset.getString("USER_NAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getTimestamp("ENROLL_DATE").toLocalDateTime());
				
				list.add(m);
			}
			
			//반복문이 끝난시점
			// list -> 비어있거나/ 데이터가 들어있거나
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	//Member객체 m을 통해서 update sql을 전달하는 메서드
	public int updateMember(Member m) {
		//update문 -> 처리된 행 수 : int -> 트랜잭션처리
		
		//필요한 변수 세팅
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//실행할 sql(sql뒤에 ;없어야함!!!)
		String sql = "UPDATE MEMBER SET EMAIL=?, PHONE=?, ADDRESS=?, HOBBY=? WHERE USER_ID=?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");
			conn.setAutoCommit(false);
			
			//아직 미완성 sql문으로 ?의 값을 전부 채워야함
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, m.getEmail());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getAddress());
			pstmt.setString(4, m.getHobby());
			pstmt.setString(5, m.getUserId());
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	//Member객체 m을 통해서 delete sql을 전달하는 메서드
	public int deleteMember(Member m) {
		//delete문 -> 처리된 행 수 : int -> 트랜잭션처리
		
		//필요한 변수 세팅
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//실행할 sql(sql뒤에 ;없어야함!!!)
		String sql = "DELETE FROM MEMBER WHERE USER_ID=? AND USER_PWD=?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##JDBC", "JDBC");
			conn.setAutoCommit(false);
			
			//아직 미완성 sql문으로 ?의 값을 전부 채워야함
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
		
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
