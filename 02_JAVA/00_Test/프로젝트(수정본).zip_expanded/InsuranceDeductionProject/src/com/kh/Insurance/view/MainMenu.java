package com.kh.Insurance.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.Insurance.controller.AccidentWeightController;
import com.kh.Insurance.controller.AmountController;
import com.kh.Insurance.controller.CarController;
import com.kh.Insurance.controller.CompanyController;
import com.kh.Insurance.controller.DriveWeightController;
import com.kh.Insurance.controller.MemberController;
import com.kh.Insurance.model.MemberVo;

public class MainMenu{
	private Scanner sc = new Scanner(System.in);
	private MemberController mc=new MemberController();
	private CompanyController cc = new CompanyController();
	private AccidentWeightController aw = new AccidentWeightController();
	private DriveWeightController dw = new DriveWeightController();
	private CarController c = new CarController();
	private AmountController ac = new AmountController();
	public MainMenu() {
		super();
	}
	public void Menu() {
		while(true) {
		System.out.println("========초기 정보 세팅========");
		System.out.println("1. 차량 정보 추가");
		System.out.println("2. 공제조합 회사 추가");
		System.out.println("3. 각 회사별 운전 경력 가중치 추가");
		System.out.println("4. 각 회사별 사고 횟수 가중치 추가");
		System.out.println("5. 각 회사별 가입된 명단");
		System.out.println("9. 사용자 메뉴로.");
		System.out.print("뭘 먼저 하실래요 : ");
			
		int sel = sc.nextInt();
		sc.nextLine();
		
		switch(sel) {
		case 1 :
			insertCar();
			break;
		case 2 :
			insertCompany();
			break;
		case 3 :
			insertDriveWeight();
			break;
		case 4 :
			insertAccidentWeight();
			break;
		case 5 :
			mc.selectMemberList();
			break;
		case 9 :
			return;
		}
		}
	}
	public void insertCar() {
		System.out.println("====차량 등록====");
		System.out.print("차 종류(1.승용차, 2.승합차, 3.화물차, 4.특수차) 번호와 차량 이름을 붙여서 입력하세요 ex)1승용차 : ");
		int sel = sc.nextInt();
		sc.nextLine();
		String name = sc.nextLine();
		
		c.insertCar(sel,name);
	}
	public void insertCompany() {
		System.out.println("====회사 등록====");
		System.out.print("회사 명 : ");
		String name = sc.nextLine();
		System.out.print("회사 번호 : ");
		String phone = sc.nextLine();
		System.out.print("기본 공제료 : ");
		int fee = sc.nextInt();
		sc.nextLine();
		
		cc.insertCompany(name,phone,fee);
	}
	public void insertDriveWeight() {
		System.out.println("====운전 경력 가중치====");
		System.out.print("운전 경력 가중치 번호 : ");
		int no = sc.nextInt();
		sc.nextLine();
		System.out.println("===운전 가중치 넣을 회사 목록===");
		cc.selectCompanyList();
		System.out.print("회사명을 입력하세요 : ");
		String companyName = sc.nextLine();
		int companyId = cc.searchCompanyIdByName(companyName);
		System.out.print("차 종류(1.승용차, 2.승합차, 3.화물차, 4.특수차) : ");
		int car = sc.nextInt();
		sc.nextLine();
		System.out.print("운전 최소 경력 : ");
		int min = sc.nextInt();
		sc.nextLine();
		System.out.print("운전 최대 경력 : ");
		int max = sc.nextInt();
		sc.nextLine();
		System.out.print("가중치 점수 : ");
		double score = sc.nextDouble();
		sc.nextLine();
		
		dw.insertDriveWeight(no,companyId, car, min, max, score);
	}
	public void insertAccidentWeight() {
		System.out.println("====사고 횟수 가중치====");
		System.out.print("사고 횟수 가중치 번호 : ");
		int no = sc.nextInt();
		sc.nextLine();
		System.out.println("===사고 횟수 가중치 넣을 회사 목록===");
		cc.selectCompanyList();
		System.out.print("회사명을 입력하세요 : ");
		String companyName = sc.nextLine();
		int companyId = cc.searchCompanyIdByName(companyName);
		System.out.print("차 종류(1.승용차, 2.승합차, 3.화물차, 4.특수차 : ");
		int car = sc.nextInt();
		sc.nextLine();
		System.out.print("사고 최소 횟수 : ");
		int min = sc.nextInt();
		sc.nextLine();
		System.out.print("사고 최대 횟수 : ");
		int max = sc.nextInt();
		sc.nextLine();
		System.out.print("가중치 점수 : ");
		double score = sc.nextDouble();
		sc.nextLine();
		
		aw.insertAccidentWeight(no,companyId, car, min, max, score);
	}
	public void UserMenu() {
		while(true) {
		System.out.println("========공제조합 보험 회사 프로그램에 오신것을 환영합니다========");
		System.out.println("1(번)을 누르시면 회원가입 화면으로 넘어갑니다.");
		System.out.println("2(번)을 누르시면 로그인 화면으로 넘어갑니다.");
		System.out.println("3(번)을 누르시면 프로그램을 종료 합니다.");
		try {
		int sel = sc.nextInt();
		sc.nextLine();
		
		switch(sel) {
		case 1 : insertMember(); break;
		case 2 : memberLog(); break;
		case 3 : return;
		}
		}catch(InputMismatchException e) {
			System.out.println("숫자를 입력해주세요.");
			sc.nextLine();
		}
		}
	}
	public void insertMember() {
		try {
		System.out.println("========고객님의 회원 가입을 도와드리겠습니다.========");
		System.out.print("사용하실 아이디를 입력하세요 : ");
		String id = sc.nextLine();
		System.out.print("사용하실 비밀번호를 입력하세요 : ");
		String pass = sc.nextLine();
		System.out.print("성함을 기입하세요 : ");
		String name = sc.nextLine();
		System.out.print("주민등록번호를 입력하세요(-포함) : ");
		String info = sc.nextLine();
		System.out.print("고객님의 현재 주소를 입력하세요 : ");
		String address = sc.nextLine();
		System.out.print("핸드폰 번호를 입력하세요(없을 시 보호자 번호를 입력하세요) : ");
		String phone = sc.nextLine();
		System.out.print("현재 고객님의 운전경력(년)을 입력하세요 : ");
		int career = sc.nextInt();
		sc.nextLine();
		int car =0;
		while(true) {
		System.out.println("=======보험 가입 차량=======");
		System.out.println("1번 : 승용차, 2번 : 승합차(9~15인승), 3번 : 화물차(1톤,2.5톤,5톤), 4번 : 특수차");
		System.out.print("보험 가입 하고 싶은 차량 정보를 입력하세요(번호로 입력 바랍니다.) : ");
		car = sc.nextInt();
		sc.nextLine();
		if(car<1||car>4) {
			System.out.println("차량 정보는 1~4 사이로 입력 해주세요.");
		}else {
			break;
			
		}
		}
		int companyId=0;
		while(true) {
		System.out.println("======가입을 원하시는 회사명을 입력하세요======");
		cc.selectCompanyList();
		System.out.print("회사명 : ");
		String companyName = sc.nextLine();
		companyId = cc.searchCompanyIdByName(companyName);
		if(companyId!=cc.searchCompanyIdByName(companyName)) {
			System.out.println("존재하지 않는 회사명 입니다. 다시 입력해주세요");
		}else {
			break;
		}
		}
		System.out.print("사고 횟수 : ");
		int count = sc.nextInt();
		sc.nextLine();
		MemberVo member = mc.insertMember(id, pass, name, info, address, phone, career,car,companyId, count);
		}catch (InputMismatchException e) {
		    System.out.println("숫자를 정확히 입력해주세요.");
		    sc.nextLine();
		}
	}
	public void memberLog() {
		System.out.print("아이디를 입력하세요 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호를 입력하세요 : ");
		String pass = sc.nextLine();
		
		MemberVo member = mc.memberLog(id,pass);
		if(member==null) {
			System.out.println("가입된 이력이 없습니다.");
			return;
		}else {
			if(!ac.existAmount(member.getUserId())) {
		  ac.insertAmount(member.getUserId(),(int)mc.selectFee(member.getId(), member.getPass()),0,(int)mc.selectFee(member.getId(), member.getPass()),0,"N" );
		  logMenu(member);
			}else {
		  logMenu(member);
			}
		}
	}
	//======가입된 회원에 대한 정보 화면==========
	public void logMenu(MemberVo m) {
		while(true) {
		System.out.println("1(번)을 입력하시면 회원 정보를 알려드립니다.");
		System.out.println("2(번)을 누르시면 회원 정보 수정을 도와드립니다. ");
		System.out.println("3(번)을 누르시면 고객님께서 납부해야 하시는 총 납입액 공제료를 알려드립니다.");
		System.out.println("4(번)을 누르시면 회원 탈퇴를 도와드립니다. ");
		System.out.println("5(번)을 누르시면 공제료 납부를 도와드립니다.");
		System.out.println("9(번)을 누르시면 프로그램 처음으로 돌아갑니다. ");
		
		try {
		int sel = sc.nextInt();
		sc.nextLine();
		
		switch(sel) {
		case 1 :
			mc.searchMember(m.getId(),m.getPass());
			break;
		case 2 :
			updateJoinMember(m);
			return;
		case 3 :
			displayFee(ac.searchRestFee(m));
			break;
		case 4 :
			deleteJoinMember(m.getId(),m.getPass());
			return;
		case 5 :
			if(ac.searchRestFee(m)==0) {
				System.out.println("회원님은 완납 대상입니다.");
			}else {
			payFee(m);
			}
			break;
		case 9 :
			return;
			
		}
		}catch (InputMismatchException e) {
		    System.out.println("숫자를 입력해주세요.");
		    sc.nextLine();
		}
	}
	}
	public void updateJoinMember(MemberVo m) {
		try {
		System.out.println("======회원 정보 변경======");
		System.out.print("변경하실 아이디 : ");
		String id = sc.nextLine();
		System.out.print("변경하실  비밀번호 : ");
		String pass = sc.nextLine();
		System.out.print("변경하실  주소 : ");
		String address = sc.nextLine();
		System.out.print("변경하실  전화번호 : ");
		String phone = sc.nextLine();
		System.out.print("변경하실  운전경력 : ");
		int career = sc.nextInt();
		sc.nextLine();
		System.out.print("(변경하실 차량 정보) 1(번) : 승용차 2(번) : 승합차 3(번) : 화물차 4(번) : 특수차 : ");
		int car = sc.nextInt();
		sc.nextLine();
		System.out.print("변경하실 사고 횟수 : ");
		int score = sc.nextInt();
		sc.nextLine();
		mc.updateJoinMember(m.getUserId(),id,pass, address, phone, career, car, score);
		}catch(InputMismatchException e) {
		    System.out.println("숫자를 정확히 입력해주세요.");
		    sc.nextLine();
		}
		
	}
	public void payFee(MemberVo m) {
		System.out.println("========공제료 납부를 도와드리겠습니다.========");
		int totalFee = ac.searchRestFee(m);
		System.out.println("회원님이 납입해야할 금액 : "+ totalFee+ "원");
		System.out.println("납입 횟수가 5회 발생 시 자동적으로 탈퇴되므로 주의 하시길 바랍니다.");
		int count =0;
		for(int i=0;i<5;i++) {
		    System.out.print("납입하실 금액을 입력해주세요 : ");
		    int fee = sc.nextInt(); 
		    ac.inputFee(m,fee); 
		    ac.feeCalculation(m);

		    int rest = ac.searchRestFee(m);
		    System.out.println("남은 금액 : " + rest+"원");

		    if(rest == 0) {
		    	int totalPaid =(int)mc.selectFee(m.getId(), m.getPass());
		    	if(totalPaid<ac.selectFee(m)) {
		    		int pay =ac.selectFee(m)-totalPaid;
		    		System.out.println("납부해야 하시는 금액보다"+pay+"원 더 보내셔서 남은"+pay+"원 돌려드리겠습니다.");
		    	}
		        System.out.println("정상적으로 납부가 완료 됐습니다.");
		        ac.fixAmount(m,(int)mc.selectFee(m.getId(), m.getPass()));
		        ac.updateStatus(m); 
		        return;
		    } else {
		    	count++;
		    	ac.insertCount(m,count);
		    }
		}
		if(ac.selectCount(m)==5) {
			mc.deleteJoinMemebr(m.getId(),m.getPass());
			return;
		}
		
	}
	public void deleteJoinMember(String id, String pass) {
		System.out.print("======회원 정보 탈퇴 화면======");
		System.out.print("정말로 탈퇴 하시겠습니까 ? (네/아니오) : ");
		String yn = sc.nextLine().toLowerCase();
		if(yn.equals("네")) {
			mc.deleteJoinMemebr(id,pass);
			successDisplay("회원 탈퇴가");
			
		}else {
			failureDisplay("회원 탈퇴가");
		
		}
	}
	public void successDisplay(String s) {
		System.out.println(s + "정상적으로 완료 됐습니다.");
	}
	public void failureDisplay(String s) {
		System.out.println(s + "실패 됐습니다.");
	}
	//가입자 회원이 있는 없는지 화면 추렭
	public void joinSuccessDisplay(String s) {
		System.out.println("가입자 : " + s);
	}
	public void joinFailureDisplay(String s) {
		System.out.println(s + "없습니다.");
	}
	//회원 조회 목록 결과
	public void listSuccess(List<MemberVo> list) {
		for(MemberVo m : list) {
			System.out.println(m);
		}
	}
	public void displayFee(int fee) {
		if(fee>0) {
		 System.out.println("납입해야 하시는 보험료 : " + fee+ "원");
		}else {
			System.out.println("공제료가 없습니다.");
		}
	}
	
	
	
}
