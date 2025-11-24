package com.semi.cm.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.semi.cm.controller.CompanyController;
import com.semi.cm.controller.ExpenseController;
import com.semi.cm.controller.ItemController;
import com.semi.cm.model.vo.BuyExpManVo;
import com.semi.cm.model.vo.EmpVo;
import com.semi.cm.model.vo.ItemInputVo;
import com.semi.cm.model.vo.ItemOrderVo;
import com.semi.cm.model.vo.ItemOutputVo;
import com.semi.cm.model.vo.ItemUseVo;
import com.semi.cm.model.vo.ItemVo;
import com.semi.cm.model.vo.MemberVo;

/**
 * 메인 메뉴
 */
public class CompanyMenu {

	private Scanner sc = new Scanner(System.in);
	private CompanyController cc = new CompanyController();
	private ItemController ic = new ItemController();
	private ExpenseController ec = new ExpenseController();
	
	private List<EmpVo> empList = new ArrayList<>();
	public CompanyMenu() {
		super();
		this.sc = new Scanner(System.in);
		this.cc = new CompanyController();
		this.ic = new ItemController();
		this.ec = new ExpenseController();
	}
	
	
	/**
	 * 로그인 화면
	 */
	public void loginMenu() {
		boolean whileVal = true;
		while(whileVal) {
			System.out.println("====회사 관리 프로그램====");
			System.out.println("1. 회원 가입");
			System.out.println("2. 로그인");
			System.out.println("9. 프로그램 종료");
			
			int loginNo = sc.nextInt();
			sc.nextLine();
			
			switch(loginNo) {
			// 회원 가입(직원 추가)
			case 1:
				signEmp();
				break;
			// 로그인 (메인 메뉴)
			case 2:
				loginEmp();
				break;
			case 8:
				cc.rollback2();
				break;
			case 9:
				System.out.println("프로그램이 종료됩니다.");
				whileVal = false;
				break;
			default :
				System.out.println("잘못입력하셨습니다.");
				break;
			}
			
		}
		
	}
	
	/**
	 * 로그인후 메인 메뉴
	 * @param member
	 */
	public void mainMenu(List<EmpVo> member) {
		boolean whileVal = true;
		empList = member;
		while(whileVal) {
			System.out.println("====메인 메뉴====");
			System.out.println("1. 인사 관리");
			System.out.println("2. 비품 관리");
			System.out.println("3. 자금 관리");
			System.out.println("9. 로그아웃");
			
			System.out.print("메뉴 번호 : ");
			int mainNo = sc.nextInt();
			sc.nextLine();
			switch(mainNo) {
			//인시 관리
			case 1:
				empMenu();
				break;
			//비품 관리
			case 2:
				itemMenu();
				break;
			//자금 관리
			case 3:
				expMenu();
				break;
				
			case 9:
				System.out.println("로그아웃 되었습니다.");
				empList =  new ArrayList<>();
				loginMenu();
				whileVal = false;
				break;
			default :
				System.out.println("잘못입력하셧습니다.");
				break;
			}
			
		}
		
	}
	
	/**
	 * 자금 관리 메뉴
	 */
	public void expMenu() {
		boolean whileVal = true;
		while(whileVal) {
			System.out.println("====자금 관리====");
			System.out.println("1. 자금 조회");
			System.out.println("2. 입금");
			System.out.println("3. 메인 메뉴");
			
			System.out.print("메뉴 번호 : ");
			
			int expNo = sc.nextInt();
			sc.nextLine();
			
			switch(expNo) {
			//자금 조회
			case 1:
				selectExp();
				break;
			//입금
			case 2:
				insertExp();
				break;
			case 3:
				mainMenu(empList);
				break;
			default :
				System.out.println("잘못입력하셧습니다.");
				break;
			
			}
			
		}
	}
	
	/**
	 * 입금
	 */
	public void insertExp() {
		System.out.println("====입금====");
		String empDept = empList.get(0).getEmpDept();
		if (!empDept.equals("D2")) {
			System.out.println("권한이 없습니다.");
			return;
		}
		System.out.println("입금할 금액을 입력해주세요.(단위 : 원)");
		int deposit = sc.nextInt();
		sc.nextLine();
		
		BuyExpManVo b = new BuyExpManVo();
		
		b.setDeposit(deposit);
		
		ec.insertExp(b);
		
		
		
	}
	
	/**
	 * 자금 조회
	 */
	public void selectExp() {
		System.out.println("====보유 자금 조회====");
		
		ec.selectExp();
		
	}
	
	/**
	 * 비품 관리 메뉴
	 */
	public void itemMenu() {
		boolean whileVal = true;
		while(whileVal) {
			System.out.println("====비품 관리====");
			System.out.println("1. 비품 조회");
			System.out.println("2. 비품 추가");
			System.out.println("3. 발주 신청");
			System.out.println("4. 발주 승인");
			System.out.println("5. 비품 입고");
			System.out.println("6. 비품 사용 신청");
			System.out.println("7. 사용 승인");
			System.out.println("8. 비품 출고");
			System.out.println("9. 발주 요청 조회"); // 추가 필요
			System.out.println("10. 사용 요청 조회"); // 추가 필요
			System.out.println("11. 메인 메뉴");
			
			System.out.print("메뉴 번호 : ");
			int itemNo = sc.nextInt();
			sc.nextLine();
			
			switch(itemNo) {
			//비품 조회
			case 1:
				selectItem();
				break;
			//비품 추가
			case 2:
				insertItem();
				break;
			//발주 신청
			case 3:
				orderReq();
				break;
			//발주 승인
			case 4:
				orderReqRespone();
				break;
			//비품 입고
			case 5:
				inputItem();
				break;
			//비품 사용 신청
			case 6:
				useReq();
				break;
			//사용 승인
			case 7:
				useReqRespone();
				break;
			//비품 출고
			case 8:
				outputItem();
				break;
			//발주 요청 조회
			case 9:
				selectOrderList();
				break;
			//사용 요청 조회
			case 10:
				selectUseList();
				break;
			case 11:
				mainMenu(empList);
				break;
			default :
				System.out.println("잘못입력하셧습니다.");
				break;
			}
		}
		
	}
	
	/**
	 * 사용 요청 조회
	 */
	public void selectUseList() {
		System.out.println("====사용 요청 리스트====");
		System.out.println("검색할 물품명을 입력하세요.");
		String itemName = sc.nextLine();
		System.out.println("검색할 진행도를 입력해주세요.(요청, 반려, 승인, 완료)");
		String useStateNm = sc.nextLine();
		String useState = "";
		if (useStateNm.equals("요청")) {
			useState = "R";
		} else if (useStateNm.equals("반려")) {
			useState = "D";
		} else if (useStateNm.equals("승인")) {
			useState = "O";
		} else if (useStateNm.equals("완료")) {
			useState = "C";
		} else if (useStateNm.equals("")) {
			useState ="";
		} else {
			System.out.println("잘못입력하셧습니다.");
			return;
		}
		
		ItemUseVo iu = new ItemUseVo();
		ItemVo i = new ItemVo();
		
		iu.setReqState(useState);
		i.setItemName(itemName);
		
		ic.selectUseList(iu,i);
		
	}
	
	/**
	 * 발주 요청 조회
	 */
	public void selectOrderList() {
		System.out.println("====발주 요청 리스트====");
		System.out.println("검색할 물품명을 입력하세요.");
		String itemName = sc.nextLine();
		System.out.println("검색할 진행도를 입력해주세요.(요청, 반려, 승인, 완료)");
		String orderStateNm = sc.nextLine();
		String orderState = "";
		if (orderStateNm.equals("요청")) {
			orderState = "R";
		} else if (orderStateNm.equals("반려")) {
			orderState = "D";
		} else if (orderStateNm.equals("승인")) {
			orderState = "O";
		} else if (orderStateNm.equals("완료")) {
			orderState = "C";
		} else if (orderStateNm.equals("")) {
			orderState="";
		} else {
			System.out.println("잘못입력하셧습니다.");
			return;
		}
		
		ItemOrderVo io = new ItemOrderVo();
		ItemVo i = new ItemVo();
		
		io.setOrderState(orderState);
		i.setItemName(itemName);
		
		ic.selectOrderList(io,i);
		
		
	}

	/**
	 * 비품 출고
	 */
	public void outputItem() {
		System.out.println("====비품 출고====");
		String loginDept = empList.get(0).getEmpDept();
		if (!loginDept.equals("D2")) {
			System.out.println("권한이 없습니다.");
			return;
		}
		
		System.out.print("처리된 요청 번호를 입력해주세요.");
		
		int reqNo = sc.nextInt();
		sc.nextLine();
		
		System.out.print("출고된 물품 명을 입력해주세요.");
		String itemName = sc.nextLine();
		
		System.out.println("출고된 물품의 갯수를 입력해주세요.");
		int outputCnt = sc.nextInt();
		sc.nextLine();
		
		ItemOutputVo io = new ItemOutputVo();
		ItemVo i = new ItemVo();
		
		io.setReqNo(reqNo);
		io.setOutputCnt(outputCnt);
		
		i.setItemName(itemName);
		
		ic.outputItem(io,i);
		
	}
	
	/**
	 * 비품 사용 신청 처리
	 */
	public void useReqRespone() {
		System.out.println("====비품 사용 신청 응답====");
		String loginDept = empList.get(0).getEmpDept();
		String loginId = empList.get(0).getEmpId();
		if (!loginDept.equals("D2")) {
			System.out.println("권한이 없습니다.");
			return;
		}
	
		System.out.print("처리하실 요청 번호를 입력해주세요.");
		int reqNo = sc.nextInt();
		sc.nextLine();
		
		System.out.print("승인, 반려 : ");
		String respone = sc.nextLine();
		String responeCode = "";
		String reqCanRes ="";
		if(respone.equals("승인")) {
			responeCode = "O";
		} else if (respone.equals("반려")) {
			responeCode = "D";
			
			System.out.print("반려 사유를 입력해주세요.");
			reqCanRes = sc.nextLine();
			
		} else {
			System.out.println("잘못 입력하셨습니다.");
			return;
		}
		
		ItemUseVo iu = new ItemUseVo();
		iu.setReqNo(reqNo);
		iu.setReqState(responeCode);
		if (responeCode.equals("D")) {
			iu.setReqCancelReason(reqCanRes);
		}
		iu.setResId(loginId);
		
		ic.useReqRespone(iu);
		
		
	}
	
	/**
	 * 비품 사용 신청
	 */
	public void useReq() {
		System.out.println("====비품 사용 신청====");
		System.out.print("사용할 비품명을 입력해주세요.");
		String itemName = sc.nextLine();
		
		System.out.print("사용할 갯수를 입력해주세요.");
		int reqCnt = sc.nextInt();
		
		String reqId = empList.get(0).getEmpId();
		
		ItemUseVo iu = new ItemUseVo();
		ItemVo i = new ItemVo();
		
		iu.setReqId(reqId);
		iu.setReqCnt(reqCnt);

		i.setItemName(itemName);
		
		ic.useReq(iu,i);
	}
	
	/**
	 * 비품 입고
	 */
	public void inputItem() {
		System.out.println("====비품 입고====");
		String loginDept = empList.get(0).getEmpDept();
		
		if (!loginDept.equals("D2")) {
			System.out.println("권한이 없습니다.");
			return;
		}
		
		System.out.print("처리된 요청 번호를 입력해주세요.");
		
		int orderNo = sc.nextInt();
		sc.nextLine();
		
		System.out.print("입고된 물품 명을 입력해주세요.");
		String itemName = sc.nextLine();
		
		System.out.println("입고된 물품의 갯수를 입력해주세요.");
		int inputCnt = sc.nextInt();
		sc.nextLine();
		
		ItemInputVo ii = new ItemInputVo();
		ItemVo i = new ItemVo();
		
		ii.setOrderNo(orderNo);
		ii.setInputCnt(inputCnt);
		
		i.setItemName(itemName);
	
		ic.inputItem(ii,i);
	}
	
	/**
	 * 발주 요청 응답
	 */
	public void orderReqRespone() {
		
		
		System.out.println("====발주 요청 응답====");
		String loginDept = empList.get(0).getEmpDept();
		String loginJob = empList.get(0).getEmpJob();
		String loginId = empList.get(0).getEmpId();
		if (loginDept.equals("D2") && loginJob.equals("J3")) {
			//발주요청 리스트
			
			System.out.print("처리하실 요청 번호를 입력해주세요.");
			int orderReqNo = sc.nextInt();
			sc.nextLine();
			
			System.out.print("승인, 반려 : ");
			String respone = sc.nextLine();
			String responeCode = "";
			String orderCanRes ="";
			if(respone.equals("승인")) {
				responeCode = "O";
			} else if (respone.equals("반려")) {
				responeCode = "D";
				
				System.out.print("반려 사유를 입력해주세요.");
				orderCanRes = sc.nextLine();
				
			} else {
				System.out.println("잘못 입력하셨습니다.");
				return;
			}
			
			ItemOrderVo io = new ItemOrderVo();
			io.setOrderNo(orderReqNo);
			io.setOrderState(responeCode);
			if (responeCode.equals("D")) {
				io.setOrderCancelReason(orderCanRes);
			}
			io.setOrderResId(loginId);
			
			ic.orderReqRespone(io);
			
		} else {
			System.out.println("권한이 없습니다.");
		}
		
		
		
		
	}
	
	/**
	 * 발주 신청
	 */
	public void orderReq() {
		System.out.println("====발주 요청====");
		System.out.print("발주를 신청하실 비품명을 입력해주세요. : ");
		String itemName = sc.nextLine();
		System.out.print("요청하실 발주 수량을 입력해주세요 : ");
		int reqCnt = sc.nextInt();
		sc.nextLine();
		
		String reqId = empList.get(0).getEmpId();
		
		ItemOrderVo io = new ItemOrderVo();
		ItemVo i = new ItemVo();
		i.setItemName(itemName);
		io.setOrderCnt(reqCnt);
		io.setOrderReqId(reqId);
		
		ic.orderReq(i,io);
		
		
	}
	
	/**
	 * 비품 정보 등록
	 */
	public void insertItem() {
		System.out.println("====비품 등록====");
		System.out.print("추가 하실 비품 명을 입력해주세요.");
		String itemName = sc.nextLine();
		
		System.out.print("해당 비품의 가격을 입력해주세요.");
		int itemPrice = sc.nextInt();
		sc.nextLine();
		
		System.err.println("해당 비품에 등록하실 코드를 입력해주세요. 기존 코드와 중복될수 없습니다.");
		String itemCode = sc.nextLine();
		
		String itemReqId = empList.get(0).getEmpId();
		
		ItemVo i = new ItemVo();
		
		i.setItemCode(itemCode);
		i.setItemName(itemName);
		i.setItemPrice(itemPrice);
		i.setItemReqId(itemReqId);
		
		ic.insertItem(i);
	}
	
	/**
	 * 비품 조회 
	 */
	public void selectItem() {
		System.out.println("====비품 조회====");
		System.out.print("비품 명을 입력해주세요(공백시 전체 조회 : )");
		String itemName = sc.nextLine();
		
		ItemVo i = new ItemVo();
		
		i.setItemName(itemName);
		
		ic.selectItem(i);
		
	}
	
	/**
	 * 인사 관리 메뉴
	 */
	public void empMenu() {
		boolean whileVal = true;
		while(whileVal) {
			System.out.println("====인사 관리====");
			System.out.println("1. 직원 조회");
			System.out.println("2. 직원 정보 수정");
			System.out.println("3. 직원 탈퇴");
			System.out.println("4. 메인 메뉴");
			
			System.out.print("메뉴 번호 :");
			int empMenuNo = sc.nextInt();
			sc.nextLine();
			switch(empMenuNo) {
			
			case 1:
				selectEmpList();
				break;
			case 2:
				updateEmp();
				break;
			case 3:
				deleteEmp();
				break;
			case 4:
				mainMenu(empList);
				break;
			default :
				System.out.println("잘못입력하셧습니다.");
				break;
			}
			
		}
	}
	
	/**
	 * 회원 탈퇴
	 */
	public void deleteEmp() {
		System.out.println("====회원 탈퇴====");
		String loginDept = empList.get(0).getEmpDept();
		String loginJob = empList.get(0).getEmpJob();
		
		
		if (loginDept.equals("D1") && loginJob.equals("J3")) {
			System.out.print("탈퇴할 아이디 : ");
			String empId = sc.nextLine();
			
			EmpVo e = new EmpVo();
			
			e.setEmpId(empId);
			
			cc.deleteEmp(e);
			
			
			
		} else {
			System.out.println("권한이 없습니다.");
		}
		
		
		
		
		
	}
	
	/**
	 * 회원정보 수정
	 */
	//update시 타임아웃 발생 원인 불명
	public void updateEmp() {
		System.out.println("====회원 정보 수정====");
		String loginDept = empList.get(0).getEmpDept();
		
		if(!loginDept.equals("D1")) {
			System.out.println("권한이 없습니다.");
			return;
		}
		System.out.print("수정할 아이디 : ");
		String empId = sc.nextLine();
		
		System.out.print("수정할 부서 명 : ");
		String empDept = sc.nextLine();
		
		System.out.print("수정할 직급 명 : ");
		String empJob = sc.nextLine();
		
		System.out.print("수정할 연봉 : ");
		int salary = sc.nextInt();
		sc.nextLine();
		
		
		EmpVo e = new EmpVo();
		
		e.setEmpId(empId);
		e.setEmpDept(empDept);
		e.setEmpJob(empJob);
		e.setSalary(salary);
		
		System.out.println(e);
		
		cc.updateEmp(e);
		
	}
	
	/**
	 * 회원 조회
	 */
	public void selectEmpList() {
		System.out.println("====회원 조회====");
		System.out.print("조회할 아이디(공백시 전체 조회) : ");
		String empId = sc.nextLine();
		
		System.out.print("조회할 이름(공백시 전체 조회) : ");
		String empName = sc.nextLine();
		
		System.out.print("조회할 부서(공백시 전체 조회) : ");
		String empDept = sc.nextLine();
		
		System.out.print("조회할 직급(공백시 전체 조회) : ");
		String empJob = sc.nextLine();
		
		EmpVo e = new EmpVo();
		e.setEmpId(empId);
		e.setEmpName(empName);
		e.setEmpDept(empDept);
		e.setEmpJob(empJob);
		System.out.println("????"+e);
		
		cc.selectEmpList(e);
		
	}
	
	
	/**
	 * 회원가입(직원 추가)
	 */
	public void signEmp() {
		System.out.println("====회원 추가====");
		System.out.print("아이디 : ");
		String empId = sc.nextLine();
		
		System.out.print("사원 명 : ");
		String empName = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String password = sc.nextLine();
		
		System.out.print("부서 명 : ");
		String empDept = sc.next();
		
		System.out.print("직급 명 : ");
		String empJob = sc.next();
		
		System.out.print("연봉 : ");
		int salary = sc.nextInt();
		sc.nextLine();
		
		System.out.print("생일(YYYY-MM-DD) : ");
		String birtDate = sc.nextLine();
		
		EmpVo e = new EmpVo();
		
		e.setEmpId(empId);
		e.setEmpName(empName);
		e.setPassword(password);
		e.setEmpDept(empDept);
		e.setEmpJob(empJob);
		e.setSalary(salary);
		e.setBirtDate(LocalDate.parse(birtDate));
		
		System.out.println(e);
		
		cc.signEmp(e);
		
		
	}
	
	/**
	 * 회원 로그인
	 */
	public void loginEmp() {
		 System.out.print("아이디 : ");
		 String empId = sc.nextLine();
		 System.out.print("비밀번호 : ");
		 String password = sc.nextLine();
		 

		 EmpVo e = new EmpVo();
		 e.setEmpId(empId);
		 e.setPassword(password);
		 
		 cc.loginEmp(e);
		
		
	}
	
	
	
	//서비스 요청 처리후 성공했을때 사용자가 보게 될 화면
	public void displaySuccess(String msg) {
		
		System.out.println("\n 서비스 요청 성공 :" + msg);
		
	}
	//서비스 요청 처리후 실패했을때 사용자가 보게 될 화면
	public void displayFail(String msg) {
		
		System.out.println("\n 서비스 요청 실패 :" + msg);
		
	}
	
	
}
