package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ButtonDialog.QuantityDialog;
import Service.MemberService;
import Service.MenuService;
import VO.Cart;
import VO.CartItem;
import VO.Member;
import VO.Menu;

/*
 * Controller : View를 통해서 사용자가 요청한 기능에 대해 처리하는 객체
 *              해당 메서드로 전달된 데이터를 가공한 후 dao로 전달하여 기능을 수행.
 *              dao로부터 반환받은 결과에 따라서 성공/실해 여부를 판단해서 응답화면 결정 -> View호출
 * */
public class Controller {
	private Member currentMember = null;

	private MemberService ms = new MemberService();
	private MenuService mn = new MenuService();
	private Cart cart = new Cart();

	Scanner sc = new Scanner(System.in);

	public Controller() {
		super();
	}

	// ViewMenu에서 받은 PhoneNumber을 이용해 신규 회원을 등록하고 도장 개수와 쿠폰 여부를 출력
	public void memberLogin(String phoneNumber) throws SQLException {

		currentMember = ms.getOrCreateMember(phoneNumber);
		cart.clearCart();
		System.out.println("환영합니다! 현재 도장 개수: " + currentMember.getStampCount() + ", 쿠폰 보유: "
				+ (currentMember.hasCoupon() ? "있음" : "없음"));
	}

	// 주문자가 고른 메뉴들을 출력하여 보여주고 JFrame 객체를 생성하여 UI 레이아웃안에 메뉴들을 버튼 형태롤 불러와서
	// 출력하고 해당 메뉴룰 선택하여 수량 조절을 할수 있는 기능을 구현하였다.
	public void showMenus() {
		if (currentMember == null) {
			System.out.println("회원 로그인이 필요합니다. 메뉴를 보기 전에 1번을 선택해주세요.");
			return;
		}

		List<Menu> menus = mn.getAllMenus();

		JFrame frame = new JFrame("=== 메뉴 목록 ===");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 600);

		// 레이아웃 설정
		frame.setLayout(new BorderLayout()); // BorderLatour 5개의 영역에 컴포넌트를 배치할수 있다.

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new GridLayout(6, 3, 10, 10));

		for (Menu m : menus) {
			JButton btnmenu = new JButton(m.toString());
			btnmenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					QuantityDialog qDialog = new QuantityDialog(menuPanel, m.getName());
					qDialog.setVisible(true);

					int quantity = qDialog.getQuantity();
					if (quantity > 0) {
						cart.addItem(m, quantity);
						JOptionPane.showMessageDialog(menuPanel, m.getName() + " x " + quantity + " 담았습니다!");
					} else {
						// 취소하거나 유효하지 않은 경우
						JOptionPane.showMessageDialog(menuPanel, "수량 선택이 취소되었습니다.");
					}
				}
			});

			menuPanel.add(btnmenu);
			frame.add(menuPanel, BorderLayout.CENTER);
		}

		// FlowLayout : 왼쪽에서 오른쪽으로 흐르듯이 컴포넌트를 배치한다.
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
		JButton btnback = new JButton("뒤로가기");
		btnback.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		btnback.setFont(new Font("굴림", Font.BOLD, 16));

		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); // 창 닫기

			}
		});

		bottomPanel.add(btnback);
		frame.add(bottomPanel, BorderLayout.SOUTH);

		JButton btnPay = new JButton("결제하기");
		btnPay.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
		btnPay.setFont(new Font("굴림", Font.BOLD, 16));
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose(); // 창 닫기
				checkoutBybtnPay();
				showMenus();
			}
		});

		bottomPanel.add(btnPay);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.setVisible(true);

	}

	// 주문자가 선택한 메뉴들을 출력하여 촣 결제할 금액과 함께 확인할 수 있다.
	public void showCart() {
		if (cart.isEmpty()) {
			System.out.println("장바구니가 비어 있습니다.");
			return;
		}

		List<CartItem> items = cart.getItems();

		System.out.println("\n========== ++ 장바구니 ++ ==========");
		System.out.printf("%-5s %-15s %-8s %-8s\n", "No", "메뉴명", "수량", "금액");
		for (int i = 0; i < items.size(); i++) {
			CartItem item = items.get(i);
			System.out.printf("%-5d %-15s %-8d %d 원 \n", i + 1, item.getMenu().getName(), item.getQuantity(),
					item.getTotalPrice());

		}
		System.out.println("------------------------------");
		System.out.printf("총 금액: %,d원\n", cart.getTotalPrice());
		System.out.println("==============================\n");

		System.out.print("\n삭제할 항목 번호 입력 (0: 취소): ");
		int input = Integer.parseInt(sc.nextLine());

		if (input == 0) {
			System.out.println("삭제 취소");
			return;
		}

		if (input < 1 || input > items.size()) {
			System.out.println("잘못된 번호입니다.");
			return;
		}

		CartItem removed = items.get(input - 1);
		cart.removeItem(input - 1);
		System.out.printf("[%s] 삭제되었습니다.\n", removed.getMenu().getName());
	}

	// 결제할때 쿠폰을 사용하거나 결제시 주문번호를 만들어주는 기능을 구현하였다.
	public void checkout() {
		if (currentMember == null) {
			System.out.println("회원 로그인이 필요합니다.");
			return;
		}

		if (cart.isEmpty()) {
			System.out.println("장바구니가 비어 있습니다.");
			return;
		}

		if (currentMember.hasCoupon()) {
			System.out.print("쿠폰을 사용하시겠습니까? (y/n): ");
			String answer = sc.nextLine();

			if (answer.equalsIgnoreCase("y")) {
				try {
					// 쿠폰 사용 처리
					boolean used = ms.useCoupon(currentMember.getPhoneNumber());

					if (used && cart.getTotalPrice() > 5000) {
						// 최종액이 5000초과일떄 쿠폰을 사용했다면 5000원 할인으로 설정
						cart.clearCart(); // 장바구니 비우기
						System.out.printf("@@ 쿠폰을 사용했습니다! 할인된 총 결제액은 %d입니다!\n", cart.getTotalPrice() - 5000);

					}

					else if (used && cart.getTotalPrice() <= 5000) {
						System.out.println("@@ 쿠폰을 사용했습니다! 무료 음료를 즐기세요!");

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				// 쿠폰 사용하지 않으면 정상 결제 금액 출력

				System.out.printf("결제 금액: %d원\n", cart.getTotalPrice());
			}
		}

		try {
			ms.stampMember(currentMember.getPhoneNumber());
		} catch (SQLException e) {
			System.err.println("도장 추가에 실패했습니다!");
			e.printStackTrace();
		}

		// 업데이트된 새 도장 개수, 쿠폰 여부 다시 불러오기 (DB 반영 위해)
		try {
			currentMember = ms.getOrCreateMember(currentMember.getPhoneNumber());
		} catch (SQLException e) {

			e.printStackTrace();
		}

		System.out.println();
		System.out.println("결제를 완료했습니다!! 감사합니다!");

		ms.processPayment(currentMember.getPhoneNumber(), cart.getItems());
		ms.selectOrderId(currentMember.getPhoneNumber());
		System.out.println(currentMember);

		cart.clearCart();
	}

	public void checkoutBybtnPay() {

		if (currentMember == null) {
			JOptionPane.showMessageDialog(null, "회원 로그인이 필요합니다.");
			return;
		}

		if (cart.isEmpty()) {
			JOptionPane.showMessageDialog(null, "장바구니가 비어 있습니다.");
			return;
		}

		boolean usedCoupon = false;

		// 쿠폰이 있는 경우만 사용 여부 확인
		if (currentMember.hasCoupon()) {
			int result = JOptionPane.showConfirmDialog(null, "쿠폰을 사용하시겠습니까?", "쿠폰 사용", JOptionPane.YES_NO_OPTION);

			if (result == JOptionPane.YES_OPTION) {
				try {
					boolean used = ms.useCoupon(currentMember.getPhoneNumber());

					if (used) {
						usedCoupon = true;

						if (cart.getTotalPrice() > 5000) {
							JOptionPane.showMessageDialog(null,
									String.format("@@ 쿠폰 사용 완료! 할인된 총 결제액은 %,d원입니다!", cart.getTotalPrice() - 5000));
						} else {
							JOptionPane.showMessageDialog(null, "@@ 쿠폰 사용 완료! 무료 음료를 즐기세요!");
						}

						// 장바구니 비우기 (쿠폰 사용 시 결제 완료)
						cart.clearCart();
					} else {
						JOptionPane.showMessageDialog(null, "쿠폰을 사용할 수 없습니다.");
					}
				} catch (SQLException e) {
					System.err.println("쿠폰 처리중 오류발생!");
					e.printStackTrace();
				}
			}
		}

		// 쿠폰을 사용하지 않은 경우 일반 결제 처리
		if (!usedCoupon) {
			JOptionPane.showMessageDialog(null, String.format("결제 금액: %,d원", cart.getTotalPrice()));
		}

		try {
			ms.stampMember(currentMember.getPhoneNumber());
			// 업데이트한 정보 갱신
			currentMember = ms.getOrCreateMember(currentMember.getPhoneNumber());
			ms.processPayment(currentMember.getPhoneNumber(), cart.getItems());
			ms.selectOrderIdBybtn(currentMember.getPhoneNumber());

			JOptionPane.showMessageDialog(null, "결제를 완료했습니다! 감사합니다!");
			JOptionPane.showMessageDialog(null, currentMember);

			cart.clearCart(); // 장바구니 초기화

		} catch (SQLException e) {
			System.out.println("결제 진행중 문제 발생!");
			e.printStackTrace();
		}
	}

}
