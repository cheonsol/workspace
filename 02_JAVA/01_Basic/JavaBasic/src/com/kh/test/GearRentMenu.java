package com.kh.test;

import java.time.LocalDate;
import java.util.*;

public class GearRentMenu {
    private final GearRentController gc = new GearRentController();
    private final Scanner sc = new Scanner(System.in);

    public void mainMenu() {
        while (true) {
            System.out.println("\n=== KH 장비 대여 관리 ===");
            System.out.println("1) 장비등록  2) 회원등록  3) 대여  4) 반납  5) 태그검색");
            System.out.println("6) 키워드검색  7) 전체장비  8) 대여중목록  9) 종료");
            System.out.print("메뉴: ");
            String sel = sc.nextLine().trim();
            try {
                switch (sel) {
                    case "1": addDevice(); break;
                    case "2": addMember(); break;
                    case "3": borrow(); break;
                    case "4": returnItem(); break;
                    case "5": findByTag(); break;
                    case "6": findByKeyword(); break;
                    case "7": printAllDevices(); break;
                    case "8": printActiveLoans(); break;
                    case "9": System.out.println("프로그램을 종료합니다."); return;
                    default: System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
                }
            } catch (RuntimeException e) {
                System.out.println("[오류] " + e.getMessage());
            }
        }
    }

    public void addDevice() {
        System.out.print("유형(1:Camera, 2:Laptop): ");
        String type = sc.nextLine().trim();
        System.out.print("id: "); String id = sc.nextLine().trim();
        System.out.print("name: "); String name = sc.nextLine().trim();
        System.out.print("category: "); String category = sc.nextLine().trim(); // 자유 입력
        System.out.print("tags(쉼표로 구분): "); String tagsLine = sc.nextLine();

        Set<String> tags = new HashSet<>();
        if (tagsLine != null && !tagsLine.isBlank()) {
            String[] parts = tagsLine.split(",");
            for (String p : parts) {
                String t = p.trim();
                if (!t.isEmpty()) tags.add(t);
            }
        }

        boolean ok;
        if ("1".equals(type)) {
            ok = gc.addDevice(new Camera(id, name, category, tags));
        } else if ("2".equals(type)) {
            ok = gc.addDevice(new Laptop(id, name, category, tags));
        } else {
            System.out.println("유형 입력 오류");
            return;
        }
        System.out.println(ok ? "등록 완료" : "중복된 ID입니다. 다시 입력해주세요.");
    }

    public void addMember() {
        System.out.print("member id: "); String id = sc.nextLine().trim();
        System.out.print("name: "); String name = sc.nextLine().trim();
        boolean ok = gc.addMember(new Member(id, name));
        System.out.println(ok ? "가입 완료" : "중복된 ID입니다. 다시 입력해주세요.");
    }

    public void borrow() {
        System.out.print("memberId: "); String mid = sc.nextLine().trim();
        System.out.print("itemId: "); String iid = sc.nextLine().trim();
        LocalDate loanDate = readDate("대여일(YYYY-MM-DD): ");
        var loan = gc.borrow(mid, iid, loanDate);
        System.out.println("대여 완료: " + loan);
        System.out.println("반납 예정일: " + loan.getDueDate());
    }

    public void returnItem() {
        System.out.print("itemId: "); String iid = sc.nextLine().trim();
        LocalDate ret = readDate("반납일(YYYY-MM-DD): ");
        int fee = gc.returnItem(iid, ret);
        System.out.println("반납 완료. 연체료: " + fee + "원");
    }

    public void findByTag() {
        System.out.print("검색 태그: "); String tag = sc.nextLine();
        var list = gc.findByTag(tag);
        if (list.isEmpty()) System.out.println("(결과 없음)");
        for (Device d : list) System.out.println(" - " + d);
    }

    public void findByKeyword() {
        System.out.print("키워드: "); String kw = sc.nextLine();
        var list = gc.findByKeyword(kw);
        if (list.isEmpty()) System.out.println("(결과 없음)");
        for (Device d : list) System.out.println(" - " + d);
    }

    public void printAllDevices() {
        var col = gc.getAllDevices();
        if (col.isEmpty()) System.out.println("(등록된 장비 없음)");
        for (Device d : col) System.out.println(" - " + d);
    }

    public void printActiveLoans() {
        var col = gc.getActiveLoans();
        if (col.isEmpty()) System.out.println("(대여 중 없음)");
        for (Loan l : col) System.out.println(" - " + l);
    }

    private LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return LocalDate.parse(s);
            } catch (Exception e) {
                System.out.println("날짜 형식이 올바르지 않습니다. 예) 2025-08-25");
            }
        }
    }
}
