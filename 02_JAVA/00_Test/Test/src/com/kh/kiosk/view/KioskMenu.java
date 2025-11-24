package com.kh.kiosk.view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.kh.kiosk.controller.MemberController;
import com.kh.kiosk.controller.OrderController;
import com.kh.kiosk.controller.PointController;
import com.kh.kiosk.controller.ProductController;
import com.kh.kiosk.model.vo.Member;
import com.kh.kiosk.model.vo.OrderItem;
import com.kh.kiosk.model.vo.PointHistory;
import com.kh.kiosk.model.vo.Product;

public class KioskMenu {
	private final Scanner sc = new Scanner(System.in);

    private final MemberController memberController = new MemberController();
    private final ProductController productController = new ProductController();
    private final OrderController orderController = new OrderController();
    private final PointController pointController = new PointController();

    // 주문 결과 표시를 위해 간단한 DTO (컨트롤러 반환형과 맞춰서 사용)
    public static class OrderResult {
        public long orderId;
        public String approvalCode;
        public long totalAmount;
        public List<OrderItem> items = new ArrayList<>();

        public OrderResult(long orderId, String approvalCode, long totalAmount, List<OrderItem> items) {
            this.orderId = orderId;
            this.approvalCode = approvalCode;
            this.totalAmount = totalAmount;
            if (items != null) this.items = items;
        }
    }

    public void main() {
        while (true) {
            System.out.println("\n================= 무인편의점 키오스크 =================");
            System.out.println("1) 상품 관리");
            System.out.println("2) 주문하기");
            System.out.println("3) 주문 취소(환불)");
            System.out.println("4) 포인트 조회");
            System.out.println("9) 종료");
            System.out.print("메뉴 선택: ");
            String sel = sc.nextLine().trim();

            switch (sel) {
                case "1": productMenu(); break;
                case "2": placeOrderFlow(); break;
                case "3": cancelOrderFlow(); break;
                case "4": pointViewFlow(); break;
                case "9":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    // ========================= 상품 관리 =========================
    private void productMenu() {
        while (true) {
            System.out.println("\n--- 상품 관리 ---");
            System.out.println("1) 상품 등록");
            System.out.println("2) 상품 기본정보 수정(이름/카테고리/가격/판매여부)");
            System.out.println("3) 재고추가(입고)");
            System.out.println("4) 상품 목록 보기");
            System.out.println("0) 뒤로");
            System.out.print("선택: ");
            String sel = sc.nextLine().trim();

            try {
                switch (sel) {
                    case "1": addProduct(); break;
                    case "2": updateProductBasic(); break;
                    case "3": addStock(); break;
                    case "4": listProducts(); break;
                    case "0": return;
                    default: System.out.println("잘못 입력하셨습니다.");
                }
            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
            }
        }
    }

    private void addProduct() {
        System.out.println("\n[상품 등록]");
        System.out.print("상품명: ");
        String name = sc.nextLine().trim();

        System.out.print("카테고리(예: 음료/과자/즉석식): ");
        String category = sc.nextLine().trim();

        long price = askLong("가격(정수, KRW): ");


        System.out.print("신선/유통기한 필요? (Y/N): ");
        String perishable = yn(sc.nextLine());

        LocalDateTime expiry = null;
        if ("Y".equalsIgnoreCase(perishable)) {
            System.out.print("유통기한 입력 (예: 2025-12-31T00:00): ");
            String ex = sc.nextLine().trim();
            expiry = ex.isEmpty() ? null : LocalDateTime.parse(ex);
        }

        System.out.print("판매 여부 (Y/N, 기본 Y): ");
        String onSale = sc.nextLine().trim();
        if (onSale.isEmpty()) onSale = "Y";

        int result = productController.add(name, category, price, perishable.toUpperCase(), expiry, onSale.toUpperCase());
        if (result > 0) System.out.println("상품 등록 성공");
        else System.out.println("상품 등록 실패");
    }

    private void updateProductBasic() {
        System.out.println("\n[상품 기본정보 수정]");
        long prodId = askLong("상품 ID: ");

        System.out.print("새 이름 (Enter=변경없음): ");
        String name = nullIfEmpty(sc.nextLine());

        System.out.print("새 카테고리 (Enter=변경없음): ");
        String category = nullIfEmpty(sc.nextLine());

        String priceRaw;
        Long price = null;
        System.out.print("새 가격(정수, Enter=변경없음): ");
        priceRaw = sc.nextLine().trim();
        if (!priceRaw.isEmpty()) price = Long.parseLong(priceRaw);

        System.out.print("판매 여부 (Y/N, Enter=변경없음): ");
        String onSale = nullIfEmpty(sc.nextLine());
        if (onSale != null) onSale = onSale.toUpperCase();

        int result = productController.updateBasic(prodId, name, category, price, onSale);
        System.out.println(result > 0 ? "수정 성공" : "수정 실패");
    }

    private void addStock() {
        System.out.println("\n[재고 추가]");
        long prodId = askLong("상품 ID: ");
        int qty = (int) askLong("추가 수량(정수): ");
        int result = productController.addStock(prodId, qty);
        System.out.println(result > 0 ? "입고 반영 완료" : "입고 실패");
    }

    private void listProducts() {
        System.out.println("\n[상품 목록]");
        List<Product> list = productController.listAll();
        if (list == null || list.isEmpty()) {
            System.out.println("(등록된 상품이 없습니다)");
            return;
        }
        for (Product p : list) {
            System.out.printf("ID=%d | %s | %s | %,d원 | 재고:%d | 판매:%s%n",
                    p.getProdId(), p.getName(),
                    p.getCategory() == null ? "-" : p.getCategory(),
                    p.getPrice(), p.getStockQty(),
                    p.getOnSaleYn());
        }
    }

    // ========================= 주문하기 =========================
    private void placeOrderFlow() {
        System.out.println("\n[주문하기]");
        // 1) 회원 확인/가입
        Member member = askMemberByPhoneOrRegister();
        if (member == null) {
            System.out.println("회원 확인/등록 실패");
            return;
        }

        // 2) 장바구니 구성
        Map<Long, Integer> cart = new LinkedHashMap<>();
        while (true) {
            System.out.println("\n상품을 장바구니에 담습니다. (0 입력 시 종료)");
            long prodId = askLong("상품 ID (0=완료): ");
            if (prodId == 0L) break;
            int qty = (int) askLong("수량: ");

            // 누적
            cart.merge(prodId, qty, Integer::sum);
            System.out.println("담김: prodId=" + prodId + ", qty=" + qty);
        }

        if (cart.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다. 주문을 종료합니다.");
            return;
        }

        // 3) 결제 시뮬레이션
        System.out.print("결제 승인? (Y=승인 / N=실패): ");
        boolean approved = "Y".equalsIgnoreCase(sc.nextLine().trim());

        // 4) 주문 실행
        // 컨트롤러 반환값은 아래 OrderResult 형태라고 가정
        KioskMenu.OrderResult result = orderController.placeOrder(member.getUserNo(), cart, approved);

        if (result == null) {
            System.out.println("주문 실패(결제 실패 또는 재고 부족 등)");
            return;
        }

        // 5) 영수증 출력
        printReceipt(result);
    }

    private Member askMemberByPhoneOrRegister() {
        System.out.print("전화번호 입력: ");
        String phone = sc.nextLine().trim();
        if (phone.isEmpty()) return null;

        Member m = memberController.findByPhone(phone);
        if (m != null) {
            System.out.printf("회원 확인: %s(%s), 포인트 %,d점%n", m.getName(), m.getPhone(), m.getPointBalance());
            return m;
        }

        System.out.println("등록된 회원이 없습니다. 회원가입을 진행합니다.");
        System.out.print("이름: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) return null;

        Member created = memberController.register(name, phone);
        if (created != null) {
            System.out.println("회원가입 완료!");
            return created;
        }
        return null;
    }

    private void printReceipt(OrderResult r) {
        System.out.println("\n====== 결제 영수증 ======");
        System.out.printf("주문번호: %d%n", r.orderId);
        System.out.printf("승인번호: %s%n", r.approvalCode);
        System.out.println("------------------------");
        long sum = 0L;
        if (r.items != null) {
            for (OrderItem it : r.items) {
                long line = it.getUnitPrice() * it.getQty();
                sum += line;
                System.out.printf("상품ID:%d  단가:%,d  수량:%d  금액:%,d%n",
                        it.getProdId(), it.getUnitPrice(), it.getQty(), line);
            }
        }
        System.out.println("------------------------");
        System.out.printf("합계: %,d원%n", r.totalAmount > 0 ? r.totalAmount : sum);
        System.out.println("========================");
    }

    // ========================= 주문 취소(환불) =========================
    private void cancelOrderFlow() {
        System.out.println("\n[주문 취소(환불)]");

        // 승인번호 기준 환불 시나리오를 반영: 사용자 인증 + 주문/승인정보 입력
        Member m = askMemberByPhoneOrRegister();
        if (m == null) {
            System.out.println("회원 확인 실패");
            return;
        }

        long orderId = askLong("주문번호 입력: ");
        System.out.print("승인번호(Approval Code) 입력: ");
        String approvalCode = sc.nextLine().trim();

        boolean ok = orderController.cancelOrder(orderId, approvalCode, m.getUserNo());
        System.out.println(ok ? "환불 처리 완료(재고 복구 & 포인트 회수)" : "환불 실패(조건 불일치/처리 불가)");
    }

    // ========================= 포인트 조회 =========================
    private void pointViewFlow() {
        System.out.println("\n[포인트 조회]");
        System.out.print("전화번호 입력: ");
        String phone = sc.nextLine().trim();

        Member m = pointController.findMemberByPhone(phone);
        if (m == null) {
            System.out.println("해당 전화번호의 회원이 없습니다.");
            return;
        }

        long balance = pointController.getPointBalanceByPhone(phone);
        System.out.printf("회원: %s(%s) | 보유 포인트: %,d점%n", m.getName(), m.getPhone(), balance);

        System.out.print("상세 이력도 보시겠습니까? (Y/N): ");
        if ("Y".equalsIgnoreCase(sc.nextLine().trim())) {
            List<PointHistory> list = pointController.getHistoryByUserNo(m.getUserNo());
            if (list == null || list.isEmpty()) {
                System.out.println("(이력이 없습니다)");
                return;
            }
            System.out.println("---- 포인트 이력 ----");
            for (PointHistory ph : list) {
                System.out.printf("[%s] 주문:%s, 변동: %+,d, 메모:%s%n",
                        ph.getRegDate(), ph.getOrderId() == null ? "-" : ph.getOrderId().toString(),
                        ph.getPointDiff(), ph.getMemo());
            }
        }
    }

    // ========================= 유틸 =========================
    private long askLong(String label) {
        while (true) {
            System.out.print(label);
            String s = sc.nextLine().trim();
            try {
                return Long.parseLong(s);
            } catch (Exception e) {
                System.out.println("정수를 입력하세요.");
            }
        }
    }

    private String yn(String s) {
        return "Y".equalsIgnoreCase(s) ? "Y" : "N";
    }

    private String nullIfEmpty(String s) {
        s = s.trim();
        return s.isEmpty() ? null : s;
    }
}
