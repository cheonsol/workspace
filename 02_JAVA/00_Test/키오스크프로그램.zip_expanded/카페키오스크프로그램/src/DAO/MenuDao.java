package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import VO.Menu;

public class MenuDao {

	// 지금 DB에 저장되있는 메뉴들의 정보를 id순으로 조회해서 List에 menu객체들을 저장해 주문자에게 보여주기 위한것이다.
	public List<Menu> selectAllMenus(Connection conn) {
		List<Menu> list = new ArrayList<>();
		String sql = "SELECT id, name, category, price FROM menu ORDER BY id";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Menu m = new Menu(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getInt("price"));
				list.add(m);
			}
		} catch (SQLException e) {
			System.err.println("Menu 정보 불러오기 실패!!");
			e.printStackTrace();
		}

		return list;
	}

}
