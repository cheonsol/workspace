package DAO;

import java.sql.*;

import VO.Member;

public class MemberDao {

	// 회원 조회
	public Member selectMember(Connection conn, String phoneNumber) {
		String sql = "SELECT phone_number, stamp_count, has_coupon FROM member WHERE phone_number = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, phoneNumber);
			try (ResultSet rs = pstmt.executeQuery()) {
				//rs.next() : ResultSet에 대해 다음 행이 있으면 true를 반환한다.
				if (rs.next()) {
					Member m = new Member(rs.getString("phone_number"), rs.getInt("stamp_count"),
							"Y".equals(rs.getString("has_coupon")));
					return m;
				}
			}
		} catch (SQLException e) {
			System.err.println("회원 조회 실패");
			e.printStackTrace();
		}
		return null; // 회원 없으면 null
	}

	// 회원 삽입
	public int insertMember(Connection conn, Member member) {
		String sql = "INSERT INTO member(phone_number, stamp_count, has_coupon) VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, member.getPhoneNumber());
			pstmt.setInt(2, member.getStampCount());
			pstmt.setString(3, member.getHasCouponAsString());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("회원 삽입 실패!");
			e.printStackTrace();
		}
		return 0;
	}

	// 회원 업데이트 (도장, 쿠폰)
	public int updateMember(Connection conn, Member member) {
		String sql = "UPDATE member SET stamp_count = ?, has_coupon = ? WHERE phone_number = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, member.getStampCount());
			pstmt.setString(2, member.getHasCouponAsString());
			pstmt.setString(3, member.getPhoneNumber());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("회원 업데이트 실패!");
			e.printStackTrace();
		}
		return 0;
	}
}
