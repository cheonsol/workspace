package Service;

import static Common.DBUtil.getConnection;

import java.sql.Connection;

import java.util.List;

import DAO.MenuDao;
import VO.Menu;

public class MenuService {

	private MenuDao dao = new MenuDao();

	// MenuDAO의 selectAllMenus를 호출하여 연결 객체를 전달한다.
	public List<Menu> getAllMenus() {

		Connection conn = getConnection();
		return dao.selectAllMenus(conn);
	}

}
