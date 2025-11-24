package com.kh.Insurance.controller;

import java.lang.reflect.Member;
import java.util.List;

import com.kh.Insurance.model.MemberVo;
import com.kh.Insurance.service.MemberService;
import com.kh.Insurance.view.MainMenu;

public class MemberController {
	private MemberService ms = new MemberService();

	public MemberController() {
		super();
	}
	public MemberVo insertMember(String id, String pass, String name, String info, String address, String phone, int career, int car ,int companyId, int accidentCount) {
		MemberVo m = new MemberVo(id, pass, name, info, address, phone, career,car, companyId,accidentCount);
		int result = ms.insertMember(m);
		
		if(result > 0) {
			//성공화면
			new MainMenu().successDisplay("회원 추가가");
	        return m;
		} else {
			//실패화면
			new MainMenu().failureDisplay("회원 추가가");
			return null;
		}
	}
	
	public int updateJoinMember(int userId, String id,String pass, String address, String phone, int career, int car, int count) {

		MemberVo m = new MemberVo();
		m.setUserId(userId);
		m.setId(id);
		m.setPass(pass);
		m.setAddress(address);
		m.setPhone(phone);
		m.setDriveCareer(career);
		m.setUserCar(car);
		m.setAccidentCount(count);
		
		int result = ms.updateJoinMember(m);
		
		if(result>0) {
			// 성공적으로 회원정보를 수정하였습니다 정보
			new MainMenu().successDisplay("회원 수정이");
		}else {
			//회원정보를 수정하는데 실패했습니다 정보
			new MainMenu().failureDisplay("회원 수정이");
		}
		return result;
		
	}
	public void deleteJoinMemebr(String id, String pass) {
		MemberVo m = new MemberVo();
		m.setId(id);
		m.setPass(pass);
		int result = ms.deleteJoinMember(m);
		
		if(result>0) {
			m=null;
			new MainMenu().successDisplay("회원 삭제가");
		}else {
			new MainMenu().successDisplay("회원 삭제가");
		}
	}
	
	public double selectFee(String id,String pass) {
		MemberVo m = new MemberVo();
		m.setId(id);
		m.setPass(pass);
		double Fee = ms.selectFee(m);
		
		if(Fee<=0) {
			return 0.0;
		}else {
			return Fee;
		}
		
	}
	public void searchMember(String id, String pass) {
		MemberVo m = new MemberVo();
		m.setId(id);
		m.setPass(pass);
		MemberVo mo = ms.searchMember(m);
		if(mo==null) {
			new MainMenu().joinFailureDisplay("가입된 회원이");
		}else {
			new MainMenu().joinSuccessDisplay(mo.toString());
		}
	}
	public MemberVo memberLog(String id, String pass) {	
		MemberVo m = new MemberVo();
		m.setId(id);
		m.setPass(pass);
		MemberVo mo = ms.searchLogMember(m);
		if(mo!=null) {
			new MainMenu().successDisplay("로그인에");
			return mo;
		}else {
			new MainMenu().failureDisplay("로그인에");
			return null;
		}
		
	}
	public void selectMemberList() {
        List<MemberVo> list = ms.selectMemberList();
		
		
		if(list.isEmpty()) {
			new MainMenu().joinFailureDisplay("회원 목록 조회 결과가 ");
		}else {
			new MainMenu().listSuccess(list);
		}
	}
	
	
	
	
}
