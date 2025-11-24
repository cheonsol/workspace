package com.limbus.sin.view;

import java.util.Scanner;
import com.limbus.sin.controller.SinController;
import static com.limbus.sin.model.vo.ValueReturner.*;

public class SinMenu {
	Scanner scan = new Scanner(System.in);
	SinController sc = new SinController();
	
	public boolean mainMenu() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("========== 인격 계산기 ==========");
		System.out.println("1. 전체 인격 추출");
		System.out.println("2. 인격 추가");
		System.out.println("3. 인격 내용 편집");
		System.out.println("4. 덱 추출");
		System.out.println("5. 덱 편집");
		System.out.println("6. 초기 편성 선언");
		System.out.print("메뉴선택 : ");
		
		int sel = scan.nextInt();scan.nextLine();
		
		switch(sel) {
		case 1:
			sinPrintAll();
			break;
		case 2:
			identityAdd();
			break;
		case 3:
			identityEdit();
			break;
		case 4:
			deckPrint();
			break;
		case 5:
			deckEdit();
			break;
		case 6:
			forming();
			break;
		default:
			System.out.println("[오류 : 존재하지않는 메뉴입니다.]");
			System.out.println("");
			System.out.println("엔터를 눌러 처음으로...");
			scan.nextLine();
		}
		
		System.out.println();
		return true;
	}
	
	public void sinPrintAll() {
		System.out.println("========== 전체 인격 추출 ==========");
		sc.sinPrintAll();
		
		System.out.println("(1:조건검색 9:처음으로");
		System.out.print("메뉴선택) ");
		
		System.out.println("< 조건 검색 >");
		System.out.println("(1:수감자 2:메인스킬속성 3:메인스킬타입 4:수비스킬속성 5:수비스킬타입 6:소속 7:키워드)");
		System.out.print("메뉴선택) ");

		System.out.println("엔터를 눌러 처음으로...");
		scan.nextLine();
	}
	
	public void filterSin() {
		
	}
	
	public void identityAdd() {
		System.out.println("========== 인격 추가 ==========");
		System.out.println("\n< 수감자 번호 입력 >");
		System.out.println("(1:이상 2:파우스트 3:돈키호테 4:료슈 5:뫼르소 6:홍루 7:히스클리프)");
		System.out.println("(8:이스마엘 9:로쟈 11:싱클레어 12:오티스 13:그레고르)");
		System.out.print("입력(정수): ");
		int charNo = scan.nextInt();scan.nextLine();
		System.out.println("\n< 인격명 입력 >");
		System.out.print("입력: ");
		String idenName = scan.nextLine();
		System.out.println("\n< 시즌 입력 (통상:0, 발푸르기스의밤:99) >");
		System.out.print("입력: ");
		int season = scan.nextInt();
		System.out.println("\n< 레어도 입력 >");
		System.out.print("입력(정수): ");
		int rarity = scan.nextInt();
		System.out.println("\n< 최대 HP 입력 >");
		System.out.print("입력(정수): ");
		int hp = scan.nextInt();
		System.out.println("\n< 최저속도 입력 >");
		System.out.print("입력(정수): ");
		int speedMin = scan.nextInt();
		System.out.println("\n< 최고속도 입력 >");
		System.out.print("입력(정수): ");
		int speedMax = scan.nextInt();
		System.out.println("\n< 방어레벨보정 입력 >");
		System.out.print("입력(정수): ");
		int hitLevel = scan.nextInt();
		System.out.println("\n< 참격 내성 입력 >");
		System.out.println("(취약:2 보통:0 내성:-1)");
		System.out.print("입력(정수): ");
		int hitSlash = scan.nextInt();
		System.out.println("\n< 관통 내성 입력 >");
		System.out.println("(취약:2 보통:0 내성:-1)");
		System.out.print("입력(정수): ");
		int hitPene = scan.nextInt();
		System.out.println("\n< 타격 내성 입력 >");
		System.out.println("(취약:2 보통:0 내성:-1)");
		System.out.print("입력(정수): ");
		int hitBlow = scan.nextInt();
		
		scan.nextLine();
		System.out.println("\n< 1스킬 스킬명 입력 >");
		System.out.print("입력: ");
		String firName = scan.nextLine();
		System.out.println("\n< 1스킬 죄악 속성 입력 >");
		System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
		System.out.print("입력(정수): ");
		int firSin = scan.nextInt();
		System.out.println("\n< 1스킬 공격 타입 입력 >");
		System.out.println("(1:참격 2:관통 3:타격)");
		System.out.print("입력(정수): ");
		int firType = scan.nextInt();
		System.out.println("\n< 1스킬 공격레벨보정 입력 >");
		System.out.print("입력(정수): ");
		int firLevel = scan.nextInt();
		System.out.println("\n< 1스킬 코인갯수 입력 >");
		System.out.print("입력(정수): ");
		int firCoinMany = scan.nextInt();
		System.out.println("\n< 1스킬 기본위력 입력 >");
		System.out.print("입력(정수): ");
		int firBase = scan.nextInt();
		System.out.println("\n< 1스킬 코인위력 입력 >");
		System.out.print("입력(정수): ");
		int firCoin = scan.nextInt();
		
		scan.nextLine();
		System.out.println("\n< 2스킬 스킬명 입력 >");
		System.out.print("입력: ");
		String secName = scan.nextLine();
		System.out.println("\n< 2스킬 죄악 속성 입력 >");
		System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
		System.out.print("입력(정수): ");
		int secSin = scan.nextInt();
		System.out.println("\n< 2스킬 공격 타입 입력 >");
		System.out.println("(1:참격 2:관통 3:타격)");
		System.out.print("입력(정수): ");
		int secType = scan.nextInt();
		System.out.println("\n< 2스킬 공격레벨보정 입력 >");
		System.out.print("입력(정수): ");
		int secLevel = scan.nextInt();
		System.out.println("\n< 2스킬 코인갯수 입력 >");
		System.out.print("입력(정수): ");
		int secCoinMany = scan.nextInt();
		System.out.println("\n< 2스킬 기본위력 입력 >");
		System.out.print("입력(정수): ");
		int secBase = scan.nextInt();
		System.out.println("\n< 2스킬 코인위력 입력 >");
		System.out.print("입력(정수): ");
		int secCoin = scan.nextInt();
		
		scan.nextLine();
		System.out.println("\n< 3스킬 스킬명 입력 >");
		System.out.print("입력: ");
		String thiName = scan.nextLine();
		System.out.println("\n< 3스킬 죄악 속성 입력 >");
		System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
		System.out.print("입력(정수): ");
		int thiSin = scan.nextInt();
		System.out.println("\n< 3스킬 공격 타입 입력 >");
		System.out.println("(1:참격 2:관통 3:타격)");
		System.out.print("입력(정수): ");
		int thiType = scan.nextInt();
		System.out.println("\n< 3스킬 공격레벨보정 입력 >");
		System.out.print("입력(정수): ");
		int thiLevel = scan.nextInt();
		System.out.println("\n< 3스킬 코인갯수 입력 >");
		System.out.print("입력(정수): ");
		int thiCoinMany = scan.nextInt();
		System.out.println("\n< 3스킬 기본위력 입력 >");
		System.out.print("입력(정수): ");
		int thiBase = scan.nextInt();
		System.out.println("\n< 3스킬 코인위력 입력 >");
		System.out.print("입력(정수): ");
		int thiCoin = scan.nextInt();
		
		scan.nextLine();
		System.out.println("\n< 수비스킬 스킬명 입력 >");
		System.out.print("입력: ");
		String defName = scan.nextLine();
		System.out.println("\n< 수비스킬 죄악 속성 입력 >");
		System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
		System.out.print("입력(정수): ");
		int defSin = scan.nextInt();
		System.out.println("\n< 수비스킬 타입 입력 >");
		System.out.println("(1:방어 2:회피 3:반격 4:강화방어 5:강화반격)");
		System.out.print("입력(정수): ");
		int defType = scan.nextInt();
		System.out.println("\n< 수비스킬 방어레벨보정 입력 >");
		System.out.print("입력(정수): ");
		int defLevel = scan.nextInt();
		System.out.println("\n< 수비스킬 코인갯수 입력 >");
		System.out.print("입력(정수): ");
		int defCoinMany = scan.nextInt();
		System.out.println("\n< 수비스킬 기본위력 입력 >");
		System.out.print("입력(정수): ");
		int defBase = scan.nextInt();
		System.out.println("\n< 수비스킬 코인위력 입력 >");
		System.out.print("입력(정수): ");
		int defCoin = scan.nextInt();
		
		scan.nextLine();
		System.out.println("\n< 패시브 이름 입력 >");
		System.out.print("입력: ");
		String pasName = scan.nextLine();
		System.out.println("\n< 패시브 요구 죄악속성 종류 입력 (1/2) >");
		System.out.print("입력(정수): ");
		int pasKind = scan.nextInt();
		System.out.println("\n< 패시브 1번 죄악 속성 입력 >");
		System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
		System.out.print("입력(정수): ");
		int pasSin = scan.nextInt();
		int pasSinSec = 0;
		if(pasKind==2) {
			System.out.println("\n< 패시브 2번 죄악 속성 입력 >");
			System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
			System.out.print("입력(정수): ");
			pasSinSec = scan.nextInt();
		}
		System.out.println("\n< 패시브 죄악요구 타입 입력 >");
		System.out.println("(1:보유 2:공명)");
		System.out.print("입력(정수): ");
		int pasType = scan.nextInt();
		
		System.out.println("\n< 패시브 1번 요구 죄악 갯수 입력 >");
		System.out.print("입력(정수): ");
		int pasMany = scan.nextInt();
		
		int pasManySec = 0;
		if(pasKind==2) {
			System.out.println("\n< 패시브 2번 요구 죄악 갯수 입력 >");
			System.out.print("입력(정수): ");
			pasSinSec = scan.nextInt();
		}
		
		scan.nextLine();
		System.out.println("\n< 서포트 패시브 이름 입력 >");
		System.out.print("입력: ");
		String supName = scan.nextLine();
		System.out.println("\n< 서포트 패시브 요구 죄악속성 종류 입력 (1/2) >");
		System.out.print("입력(정수): ");
		int supKind = scan.nextInt();
		System.out.println("\n< 서포트 패시브 1번 죄악 속성 입력 >");
		System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
		System.out.print("입력(정수): ");
		int supSin = scan.nextInt();
		int supSinSec = 0;
		if(supKind==2) {
			System.out.println("\n< 서포트 패시브 2번 죄악 속성 입력 >");
			System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
			System.out.print("입력(정수): ");
			supSinSec = scan.nextInt();
		}
		System.out.println("\n< 서포트 패시브 요구 종류 입력 >");
		System.out.println("(1:보유 2:공명)");
		System.out.print("입력(정수): ");
		int supType = scan.nextInt();
		
		System.out.println("\n< 서포트 패시브 1번 요구 죄악 갯수 입력 >");
		System.out.print("입력(정수): ");
		int supMany = scan.nextInt();
		
		int supManySec = 0;
		if(supKind==2) {
			System.out.println("\n< 패시브 2번 요구 죄악 갯수 입력 >");
			System.out.print("입력(정수): ");
			supSinSec = scan.nextInt();
		}
		
		scan.nextLine();
		System.out.println("\n< 소속 입력 (', '로 분리) >");
		System.out.print("입력: ");
		String relation = scan.nextLine();
		
		System.out.println("\n< 키워드 입력 (', '로 분리) >");
		System.out.print("입력: ");
		String keyword = scan.nextLine();
		
		int idenNo = sc.charCount(charNo, season, rarity);
		//charNo season rarity
		
		sc.identityAdd(idenNo, charNo, idenName, season, rarity, hp, speedMin, speedMax, hitLevel, hitSlash, hitPene, hitBlow,
				firName, firSin, firType, firLevel, firCoinMany, firBase, firCoin,
				secName, secSin, secType, secLevel, secCoinMany, secBase, secCoin,
				thiName, thiSin, thiType, thiLevel, thiCoinMany, thiBase, thiCoin,
				defName, defSin, defType, defLevel, defCoinMany, defBase, defCoin,
				pasName, pasKind, pasSin, pasSinSec, pasType, pasMany, pasManySec,
				supName, supKind, supSin, supSinSec, supType, supMany, supManySec,
				relation, keyword);
		System.out.println("엔터를 눌러 처음으로...");
		scan.nextLine();
 	}
	
	public void deckPrint() {
		System.out.println("========== 덱 추출 ==========");
		sc.deckPrintAll();
		System.out.print("(1:상세보기 2:처음으로) : ");
		int sel = scan.nextInt();scan.nextLine();
		switch(sel) {
		case 1:
			deckPrintParticular();
			break;
		default:
			return;
		}
	}
	
	public void deckPrintParticular() {
		System.out.println("========== 덱 추출 ==========");
		sc.deckPrintAllParticular();
		System.out.println("엔터를 눌러 처음으로...");
		scan.nextLine();
	}
	
	public void deckEdit() {
		System.out.println("===== 덱 편집 =====");
		sc.deckPrintAll();
		System.out.println(">>> 14 이상 입력시 탈출 <<<");
		System.out.print("편집할 수감자 번호 입력) ");
		int charNo = scan.nextInt(); scan.nextLine();
		if(charNo<=13 && charNo>=1) {
			System.out.println("===== " + charReturn(charNo) + " 변경 =====");
			sc.charList(charNo);
			System.out.print("변경할 인격 번호 입력) ");
			int idenNo = scan.nextInt(); scan.nextLine();
			sc.identityChange(charNo, idenNo);
		} else {
			return;
		}
		deckEdit();
	}
	
	public void identityEdit() {
		System.out.println("===== 인격 편집 =====");
		sc.IdentityList();
		System.out.println("< 편집하실 인격을 선택해주세요. >");
		System.out.print("인격No 입력) ");
		int idenNo = scan.nextInt();
		System.out.println("");
		int iden = sc.IdentityView(idenNo);
		if(iden==0) {
			System.out.println("엔터를 눌러 처음으로...");
			scan.nextLine();scan.nextLine();
			return;}
		System.out.println("\n< 편집하실 내용을 선택해주세요. >");
		System.out.println("(1:인격명 / 2:능력치 / 3:1스킬 / 4:2스킬 / 5:3스킬)");
		System.out.println("(6:수비스킬 / 7:패시브,서포트 / 8:소속 / 9:키워드)");
		System.out.print("메뉴선택) ");
		int sel = scan.nextInt();scan.nextLine();
		identituEditSel(sel, idenNo);
	}
	
	public void forming() {
		System.out.println("========== 초기 편성 ==========");
		sc.deckPrintAll();
		
		int[] form = new int[7];
		
		for(int n=0; n<7; n++) {
			System.out.print("편성 " + (n+1) + "번 수감자 입력 : ");
			form[n] = scan.nextInt();scan.nextLine();
		}
		
		sc.deckData(form);
		
		System.out.println("엔터를 눌러 처음으로...");
		scan.nextLine();
	}
	
	public void identituEditSel(int sel, int idenNo) {
		switch(sel) {
		case 1:
			System.out.println("\n< 인격명 입력 >");
			System.out.print("입력: ");
			String idenName = scan.nextLine();
			sc.identityNameEdit(idenNo, idenName);
			break;
		case 2:
			System.out.println("\n< 최대 HP 입력 >");
			System.out.print("입력(정수): ");
			int hp = scan.nextInt();
			System.out.println("\n< 최저속도 입력 >");
			System.out.print("입력(정수): ");
			int speedMin = scan.nextInt();
			System.out.println("\n< 최고속도 입력 >");
			System.out.print("입력(정수): ");
			int speedMax = scan.nextInt();
			System.out.println("\n< 방어레벨보정 입력 >");
			System.out.print("입력(정수): ");
			int hitLevel = scan.nextInt();
			System.out.println("\n< 참격 내성 입력 >");
			System.out.println("(취약:2 보통:0 내성:-1)");
			System.out.print("입력(정수): ");
			int hitSlash = scan.nextInt();
			System.out.println("\n< 관통 내성 입력 >");
			System.out.println("(취약:2 보통:0 내성:-1)");
			System.out.print("입력(정수): ");
			int hitPene = scan.nextInt();
			System.out.println("\n< 타격 내성 입력 >");
			System.out.println("(취약:2 보통:0 내성:-1)");
			System.out.print("입력(정수): ");
			int hitBlow = scan.nextInt();scan.nextLine();
			sc.identityStateEdit(idenNo, hp, speedMin, speedMax, hitLevel, hitSlash, hitPene, hitBlow);
			break;
		case 3:
			System.out.println("\n< 1스킬 스킬명 입력 >");
			System.out.print("입력: ");
			String firName = scan.nextLine();
			System.out.println("\n< 1스킬 죄악 속성 입력 >");
			System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
			System.out.print("입력(정수): ");
			int firSin = scan.nextInt();
			System.out.println("\n< 1스킬 공격 타입 입력 >");
			System.out.println("(1:참격 2:관통 3:타격)");
			System.out.print("입력(정수): ");
			int firType = scan.nextInt();
			System.out.println("\n< 1스킬 공격레벨보정 입력 >");
			System.out.print("입력(정수): ");
			int firLevel = scan.nextInt();
			System.out.println("\n< 1스킬 코인갯수 입력 >");
			System.out.print("입력(정수): ");
			int firCoinMany = scan.nextInt();
			System.out.println("\n< 1스킬 기본위력 입력 >");
			System.out.print("입력(정수): ");
			int firBase = scan.nextInt();
			System.out.println("\n< 1스킬 코인위력 입력 >");
			System.out.print("입력(정수): ");
			int firCoin = scan.nextInt();
			sc.identityFirstEdit(idenNo, firName, firSin, firType, firLevel, firCoinMany, firBase, firCoin);
			break;
		case 4:
			System.out.println("\n< 2스킬 스킬명 입력 >");
			System.out.print("입력: ");
			String secName = scan.nextLine();
			System.out.println("\n< 2스킬 죄악 속성 입력 >");
			System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
			System.out.print("입력(정수): ");
			int secSin = scan.nextInt();
			System.out.println("\n< 2스킬 공격 타입 입력 >");
			System.out.println("(1:참격 2:관통 3:타격)");
			System.out.print("입력(정수): ");
			int secType = scan.nextInt();
			System.out.println("\n< 2스킬 공격레벨보정 입력 >");
			System.out.print("입력(정수): ");
			int secLevel = scan.nextInt();
			System.out.println("\n< 2스킬 코인갯수 입력 >");
			System.out.print("입력(정수): ");
			int secCoinMany = scan.nextInt();
			System.out.println("\n< 2스킬 기본위력 입력 >");
			System.out.print("입력(정수): ");
			int secBase = scan.nextInt();
			System.out.println("\n< 2스킬 코인위력 입력 >");
			System.out.print("입력(정수): ");
			int secCoin = scan.nextInt();
			sc.identitySecondEdit(idenNo, secName, secSin, secType, secLevel, secCoinMany, secBase, secCoin);
			break;
		case 5:
			System.out.println("\n< 3스킬 스킬명 입력 >");
			System.out.print("입력: ");
			String thiName = scan.nextLine();
			System.out.println("\n< 3스킬 죄악 속성 입력 >");
			System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
			System.out.print("입력(정수): ");
			int thiSin = scan.nextInt();
			System.out.println("\n< 3스킬 공격 타입 입력 >");
			System.out.println("(1:참격 2:관통 3:타격)");
			System.out.print("입력(정수): ");
			int thiType = scan.nextInt();
			System.out.println("\n< 3스킬 공격레벨보정 입력 >");
			System.out.print("입력(정수): ");
			int thiLevel = scan.nextInt();
			System.out.println("\n< 3스킬 코인갯수 입력 >");
			System.out.print("입력(정수): ");
			int thiCoinMany = scan.nextInt();
			System.out.println("\n< 3스킬 기본위력 입력 >");
			System.out.print("입력(정수): ");
			int thiBase = scan.nextInt();
			System.out.println("\n< 3스킬 코인위력 입력 >");
			System.out.print("입력(정수): ");
			int thiCoin = scan.nextInt();
			sc.identityThirdEdit(idenNo, thiName, thiSin, thiType, thiLevel, thiCoinMany, thiBase, thiCoin);
			break;
		case 6:
			System.out.println("\n< 수비스킬 스킬명 입력 >");
			System.out.print("입력: ");
			String defName = scan.nextLine();
			System.out.println("\n< 수비스킬 죄악 속성 입력 >");
			System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
			System.out.print("입력(정수): ");
			int defSin = scan.nextInt();
			System.out.println("\n< 수비스킬 타입 입력 >");
			System.out.println("(1:방어 2:회피 3:반격 4:강화방어 5:강화반격)");
			System.out.print("입력(정수): ");
			int defType = scan.nextInt();
			System.out.println("\n< 수비스킬 방어레벨보정 입력 >");
			System.out.print("입력(정수): ");
			int defLevel = scan.nextInt();
			System.out.println("\n< 수비스킬 코인갯수 입력 >");
			System.out.print("입력(정수): ");
			int defCoinMany = scan.nextInt();
			System.out.println("\n< 수비스킬 기본위력 입력 >");
			System.out.print("입력(정수): ");
			int defBase = scan.nextInt();
			System.out.println("\n< 수비스킬 코인위력 입력 >");
			System.out.print("입력(정수): ");
			int defCoin = scan.nextInt();
			sc.identityDefenceEdit(idenNo, defName, defSin, defType, defLevel, defCoinMany, defBase, defCoin);
			break;
		case 7:
			System.out.println("\n< 패시브 이름 입력 >");
			System.out.print("입력: ");
			String pasName = scan.nextLine();
			System.out.println("\n< 패시브 요구 죄악속성 종류 입력 (1/2) >");
			System.out.print("입력(정수): ");
			int pasKind = scan.nextInt();
			System.out.println("\n< 패시브 1번 죄악 속성 입력 >");
			System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
			System.out.print("입력(정수): ");
			int pasSin = scan.nextInt();
			int pasSinSec = 0;
			if(pasKind==2) {
				System.out.println("\n< 패시브 2번 죄악 속성 입력 >");
				System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
				System.out.print("입력(정수): ");
				pasSinSec = scan.nextInt();
			}
			System.out.println("\n< 패시브 죄악요구 타입 입력 >");
			System.out.println("(1:보유 2:공명)");
			System.out.print("입력(정수): ");
			int pasType = scan.nextInt();
			
			System.out.println("\n< 패시브 1번 요구 죄악 갯수 입력 >");
			System.out.print("입력(정수): ");
			int pasMany = scan.nextInt();
			
			int pasManySec = 0;
			if(pasKind==2) {
				System.out.println("\n< 패시브 2번 요구 죄악 갯수 입력 >");
				System.out.print("입력(정수): ");
				pasSinSec = scan.nextInt();
			}
			
			scan.nextLine();
			System.out.println("\n< 서포트 패시브 이름 입력 >");
			System.out.print("입력: ");
			String supName = scan.nextLine();
			System.out.println("\n< 서포트 패시브 요구 죄악속성 종류 입력 (1/2) >");
			System.out.print("입력(정수): ");
			int supKind = scan.nextInt();
			System.out.println("\n< 서포트 패시브 1번 죄악 속성 입력 >");
			System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
			System.out.print("입력(정수): ");
			int supSin = scan.nextInt();
			int supSinSec = 0;
			if(supKind==2) {
				System.out.println("\n< 서포트 패시브 2번 죄악 속성 입력 >");
				System.out.println("(\u001B[41;97m1:분노 \u001B[43;97m2:색욕 \u001B[103;30m3:나태 \u001B[42;97m4:탐식 \u001B[106;30m5:우울 \u001B[44;97m6:오만 \u001B[45;97m7:질투\u001B[0m)");
				System.out.print("입력(정수): ");
				supSinSec = scan.nextInt();
			}
			System.out.println("\n< 서포트 패시브 요구 종류 입력 >");
			System.out.println("(1:보유 2:공명)");
			System.out.print("입력(정수): ");
			int supType = scan.nextInt();
			
			System.out.println("\n< 서포트 패시브 1번 요구 죄악 갯수 입력 >");
			System.out.print("입력(정수): ");
			int supMany = scan.nextInt();
			
			int supManySec = 0;
			if(supKind==2) {
				System.out.println("\n< 패시브 2번 요구 죄악 갯수 입력 >");
				System.out.print("입력(정수): ");
				supSinSec = scan.nextInt();
			}
			sc.identityPassiveEdit(idenNo, pasName, pasKind, pasSin, pasSinSec, pasType, pasMany, pasManySec, supName, supKind, supSin, supSinSec, supType, supMany, supManySec);
			break;
		case 8:
			System.out.println("\n< 소속 입력 (', '로 분리) >");
			System.out.print("입력: ");
			String relation = scan.nextLine();
			sc.identityRelationEdit(idenNo, relation);
			break;
		case 9:
			System.out.println("\n< 키워드 입력 (', '로 분리) >");
			System.out.print("입력: ");
			String keyword = scan.nextLine();
			sc.identityKeywordEdit(idenNo, keyword);
			break;
		default:
			System.out.println("잘못된 메뉴입니다.");
			return;
		}
	}
}
