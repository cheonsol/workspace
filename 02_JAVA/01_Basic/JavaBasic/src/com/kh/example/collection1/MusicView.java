package com.kh.example.collection1;

import java.util.List;
import java.util.Scanner;

public class MusicView {
    // Scanner, MusicController 생성
	private Scanner sc = new Scanner(System.in);
	private MusicController mc = new MusicController();

    public void mainMenu() {
        // ***** 메인 메뉴 *****
        // 1. 마지막 위치에 곡 추가        -> addList()
        // 2. 첫 위치에 곡 추가            -> addAtZero()
        // 3. 전체 곡 목록 출력            -> printAll()
        // 4. 특정 곡 검색                 -> searchMusic()
        // 5. 특정 곡 삭제                 -> removeMusic()
        // 6. 특정 곡 정보 수정            -> setMusic()
        // 7. 곡명 오름차순 정렬           -> ascTitle()
        // 8. 가수명 내림차순 정렬         -> descSinger()
        // 9. 종료 (메시지 출력 후 반환)
        // 반복 처리
    	
    	while(true) {
    		System.out.println("***** 메인 메뉴 *****");
    		System.out.println("1. 마지막 위치에 곡 추가");
    		System.out.println("2. 첫 위치에 곡 추가");
    		System.out.println("3. 전체 곡 목록 출력 ");
    		System.out.println("4. 특정 곡 검색 ");
    		System.out.println("5. 특정 곡 삭제 ");
    		System.out.println("6. 특정 곡 정보 수정");
    		System.out.println("7. 곡명 오름차순 정렬 ");
    		System.out.println("8. 가수명 내림차순 정렬");
    		System.out.println("9. 종료 (메시지 출력 후 반환)");
    		System.out.print("메뉴 번호 입력 : ");
    		
    		int sel = sc.nextInt();
    		sc.nextLine();
    		
    		switch(sel) {
    			case 1:
    				addList();
    				break;
    			case 2:
    				addAtZero();
    				break;
    			case 3:
    				printAll();
    				break;
    			case 4:
    				searchMusic();
    				break;
    			case 5:
    				removeMusic();
    				break;
    			case 6:
    				setMusic();
    				break;
    			case 7:
    				break;
    			case 8:
    				break;
    			case 9:
    				System.out.println("프로그램 종료");
    				return;
    			default:
    				System.out.println("잘못입력하셨습니다.");
    		}
    		System.out.println();
    	}
    }

    public void addList() {
    	System.out.println("****** 마지막 위치에 곡 추가 ******");
    	System.out.print("곡 명 : ");
    	String title = sc.nextLine();
    	System.out.print("가수 명 : ");
    	String singer = sc.nextLine();
    	
    	int result = mc.addList(new Music(title, singer));
        System.out.println(result == 1 ? "추가 성공" : "추가 실패");
    }

    public void addAtZero() {
    	System.out.println("****** 첫 위치에 곡 추가 ******");
    	System.out.print("곡 명 : ");
    	String title = sc.nextLine();
    	System.out.print("가수 명 : ");
    	String singer = sc.nextLine();
    	
    	int result = mc.addAtZero(new Music(title, singer));
        System.out.println(result == 1 ? "추가 성공" : "추가 실패");
    }

    public void printAll() {
    	System.out.println("****** 전체 곡 목록 출력 ******");
    	List<Music> list = mc.printAll();
    	System.out.println(list);
    }

    public void searchMusic() {
    	System.out.println("****** 특정 곡 검색 ****** ");
    	System.out.print("검색할 곡 명 : ");
    	String title = sc.nextLine();
    	Music m = mc.searchMusic(title);
    	if(m == null) {
    		System.out.println("검색한 곡이 없습니다.");
    	} else {
    		System.out.println("검색한 곡은 " + m.toString() + "입니다.");
    	}
    }

    public void removeMusic() {
    	System.out.println("****** 특정 곡 삭제 ******");
    	System.out.print("곡 명 : ");
    	String title = sc.nextLine();
    	Music m = mc.removeMusic(title);
    	if(m == null) {
    		System.out.println("삭제할 곡이 없습니다.");
    	} else {
    		System.out.println(m.toString() + "을(를) 삭제했습니다.");
    	}
    }

    public void setMusic() {
      	System.out.println("****** 특정 곡 삭제 ******");
    	System.out.print("검색할 곡 명 : ");
    	String oldTitle = sc.nextLine();
    	
    	System.out.print("수정할 곡 명 : ");
    	String newTitle = sc.nextLine();
    	System.out.print("수정할 가수 명 : ");
    	String newSinger = sc.nextLine();
    	Music m = mc.setMusic(oldTitle, new Music(newTitle, newSinger));
        if(m == null) {
        	System.out.println("수정할 곡이 없습니다.");
        } else {
        	System.out.println(m.toString() + "의 값이 변경되었습니다.");
        }
    }

    public void ascTitle() {
        // mc.ascTitle() 결과에 따라 "정렬 성공 / 정렬 실패"
    }

    public void descSinger() {
        // mc.descSinger() 결과에 따라 "정렬 성공 / 정렬 실패"
    }
}
