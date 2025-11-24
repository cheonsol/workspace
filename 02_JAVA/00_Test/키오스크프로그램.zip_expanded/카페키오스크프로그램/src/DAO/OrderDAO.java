// OrderDAO.java
package DAO;

import java.sql.*;

import javax.swing.JOptionPane;

import VO.Order;

public class OrderDAO {

	// 주문 저장
	public void insertOrder(Connection conn, Order order) throws SQLException {

		String sql = "INSERT INTO orders (order_id, phone, order_date, show_order,total_price, status) VALUES (?, ?, SYSDATE ,? , ?, ?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, null);
		pstmt.setString(2, order.getPhone());
		pstmt.setString(3, order.getShowOrder());
		pstmt.setDouble(4, order.getTotalPrice());
		pstmt.setString(5, order.getStatus());
		pstmt.executeUpdate();
		pstmt.close();

	}

	// 주문 번호를 출력한다.
	public void selectOrderId(Connection conn, String phone) {
		String sql = "SELECT order_id FROM ORDERS WHERE phone = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, phone);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int orderId = rs.getInt("order_id");

					System.out.println("\n주문 번호: " + orderId);

				}
			}
		} catch (SQLException e) {
			System.err.println("주문번호 불러오기 실패!");
			e.printStackTrace();
		}

	}

	// JFrame에서 결제버튼을 클릭했을때 호출된다.
	public void selectOrderIdBybtn(Connection conn, String phone) {
		String sql = "SELECT order_id FROM ORDERS WHERE phone = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, phone);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int orderId = rs.getInt("order_id");

					// 메시지창으로 출력
					JOptionPane.showMessageDialog(null, "주문 번호: " + orderId);

				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "주문번호 불러오기 실패!");
			e.printStackTrace();
		}

	}
}
