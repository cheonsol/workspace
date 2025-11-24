package Service;

import static Common.DBUtil.close;
import static Common.DBUtil.getConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DAO.MemberDao;
import DAO.OrderDAO;
import VO.Cart;
import VO.CartItem;
import VO.Member;
import VO.Order;

public class MemberService {
	private MemberDao memberDao = new MemberDao();
	private OrderDAO orderDAO = new OrderDAO();

	// 전화번호로 회원 조회, 없으면 신규 회원 생성 후 반환
	public Member getOrCreateMember(String phoneNumber) throws SQLException {
		Connection conn = getConnection();
		Member member = memberDao.selectMember(conn, phoneNumber);

		if (member == null) { // 신규 회원 생성
			member = new Member(phoneNumber, 0, false);
			memberDao.insertMember(conn, member);
		}
		close(conn);
		return member;
	}

	// 회원에게 도장 추가, 8개 도장 시 쿠폰 지급 및 도장 초기화
	public void stampMember(String phoneNumber) throws SQLException {
		Connection conn = getConnection();
		Member member = memberDao.selectMember(conn, phoneNumber);

		if (member != null && !member.hasCoupon()) {
			member.setStampCount(member.getStampCount() + 1);

			if (member.getStampCount() >= 8) {
				member.setStampCount(0);
				member.setHasCoupon(true);
				System.out.println(" *^.^* 도장 8개 모음! 무료 음료 쿠폰이 지급되었습니다! ");
			}
			memberDao.updateMember(conn, member);
		}
		close(conn);
	}

	// 쿠폰 사용 (사용 성공 시 true 반환, 실패 시 false)
	public boolean useCoupon(String phoneNumber) throws SQLException {

		Connection conn = getConnection();

		Member member = memberDao.selectMember(conn, phoneNumber);

		if (member != null) {

			member.setHasCoupon(false);
			memberDao.updateMember(conn, member);
			close(conn);
			return true;

		} else {

			close(conn);
			return false;

		}
	}

	// 주문자의 주문 내역을 판매자가 확인할수 있도록 주문 테이블에 저장한다.
	public void processPayment(String phone, List<CartItem> items) {

		Connection conn = getConnection();

		Cart cart = new Cart();

		// 주문 객체 생성
		Order order = new Order(phone, new java.util.Date(), items.toString(), cart.getTotalPrice(), "주문완료");

		// 주문 DB 저장
		try {
			orderDAO.insertOrder(conn, order);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// 주문 완료시 주문번호 출력
	public void selectOrderId(String phone) {

		Connection conn = getConnection();

		orderDAO.selectOrderId(conn, phone);

	}

	// JFrame 객체에서 결제버튼을 클릭했을때 해당 정보를 orderDAO에 전달한다.

	public void selectOrderIdBybtn(String phone) {

		Connection conn = getConnection();

		orderDAO.selectOrderIdBybtn(conn, phone);

	}

}
