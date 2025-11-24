package com.semi.cm.controller;

import java.util.List;

import com.semi.cm.model.vo.EmpVo;
import com.semi.cm.model.vo.MemberVo;
import com.semi.cm.service.CompanyService;
import com.semi.cm.view.CompanyMenu;

/**
 *  컨트롤러
 */
public class CompanyController {

	private CompanyService cs = new CompanyService();
	private EmpVo e = new EmpVo();
	public CompanyController() {
		super();
	}
	
	/**
	 * 회원가입(직원 추가)
	 * @param m
	 */
	public void signEmp(EmpVo e) {
		
		int result = cs.signEmp(e);
		
		if (result > 0) {
			new CompanyMenu().displaySuccess("회원가입이 완료되었습니다.");
		} else {
			new CompanyMenu().displayFail("회원가입에 실패 하였습니다.");
		}
		
	}
	
	/**
	 * 로그인
	 * @param e
	 */
	public void loginEmp(EmpVo e) {
		String empId = e.getEmpId();
		List<EmpVo> result = cs.loginEmp(e);
		
		if (result.size() > 0) {
			new CompanyMenu().displaySuccess("환영합니다."+empId+"님");
			new CompanyMenu().mainMenu(result);
		} else {
			new CompanyMenu().displayFail("로그인에 실패 하였습니다. 아이디 및 비밀번호를 확인해주세요.");
		}
		
		
	}
	
	/**
	 * 회원 조회
	 * @param e
	 */
	public void selectEmpList(EmpVo e) {
		
		List<EmpVo> result = cs.selectEmpList(e);
		
		if (result.size() > 0) {
			new CompanyMenu().displaySuccess("");
			for (int i=0; i<result.size(); i++) {
				System.out.println(result.get(i));
			}
		} else {
			new CompanyMenu().displayFail("검색된 사원이 없습니다.");
		}
		
	}
	
	/**
	 * 회원 정보 수정
	 * @param e
	 */
	public void updateEmp(EmpVo e) {
		
		int result = cs.updateEmp(e);
		
		if (result > 0) {
			new CompanyMenu().displaySuccess("회원정보가 수정되었습니다.");
		} else {
			new CompanyMenu().displayFail("회원정보 수정에 실패하였습니다.");
		}
		
	}
	
	/**
	 * 회원 탈퇴
	 * @param e
	 */
	public void deleteEmp(EmpVo e) {
		
		int result = cs.deleteEmp(e);
		
		if (result > 0) {
			new CompanyMenu().displaySuccess("퇴사 처리가 완료되었습니다.");
		} else {
			new CompanyMenu().displayFail("퇴사 처리가 실패하였습니다.");
		}
		
		
	}
	
	public void rollback2() {
		cs.rollback2();
	}
	
}
