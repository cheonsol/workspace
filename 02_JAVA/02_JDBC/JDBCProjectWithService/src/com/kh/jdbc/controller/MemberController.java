package com.kh.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.model.vo.Member;
import com.kh.jdbc.service.MemberService;
import com.kh.jdbc.view.MemberMenu;

/*
 * Controller : View를 통해서 사용자가 요청한 기능에 대해 처리하는 객체
 *              해당 메서드로 전달된 데이터를 가공한 후 dao로 전달하여 기능을 수행.
 *              dao로부터 반환받은 결과에 따라서 성공/실해 여부를 판단해서 응답화면 결정 -> View호출
 * */
public class MemberController {
	private MemberService ms = new MemberService();
	
	public MemberController() {
		super();

	}

	/*
	 * 사용자의 추가요청을 처리하는 메서드
	 *  userId ~ hobby : 사용자가 입력한 정보를 매개변수로 받음
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email,
			String phone, String address, String hobby) {
		
		//view로부터 전달받은 값을 바로 dao에 전달x
		//vo에 잘 담아서 전달
		Member m = new Member(userId, userPwd, userName, gender, 
				    Integer.parseInt(age), email, phone, address, hobby);
		
		int result = ms.insertMember(m);
		
		if(result > 0) {
			//성공화면
			new MemberMenu().displaySuccess("성공적으로 회원이 추가되었습니다.");
		} else {
			//실패화면
			new MemberMenu().displayFail("회원추가에 실패하였습니다.");
		}
	}
	
	//회원을 모두 조회
	public void selectMemberAll() {
		List<Member> list = ms.selectMemberList();
		
		//조회된 결과에 따라서 사용자가 보게될 화면
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("회원목록 조회결과가 없습니다.");
		}else {
			new MemberMenu().displayList(list, "회원 목록");
		}
	}
	
	//userId, email, phone, address, hobby를 전달받아
	//Member를 수정하는 메서드
	public void updateMember(String userId, String email, String phone, String address, String hobby) {
		Member m = new Member();
		m.setUserId(userId);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		m.setHobby(hobby);
		
		int result = ms.updateMember(m);
		
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원정보를 수정하였습니다.");
		} else {
			new MemberMenu().displayFail("회원정보를 수정하는데 실패하였습니다.");
		}
	}
	
	public void deleteMember(String userId, String userPwd) {
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		int result = ms.deleteMember(m);
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 회원탈퇴가 되었습니다.");
		} else {
			new MemberMenu().displayFail("회원탈퇴에 실패하였습니다.");
		}
	}
	
	//회원이름으로 키워드 검색
	public void memberSearchByName(String keyword) {
		ArrayList<Member> list = ms.memberSearchByName(keyword);
		
		if(list.isEmpty()) {
			new MemberMenu().displayNoData("회원목록 키워드 검색결과가 없습니다.");
		}else {
			new MemberMenu().displayList(list, "키워드 검색 목록");
		}
	}
}










