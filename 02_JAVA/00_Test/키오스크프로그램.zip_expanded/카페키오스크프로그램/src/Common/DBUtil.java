package Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	// static을 사용하여 객체를 생성하지 않고도 변수 접근이 가능하고 한번 설정한 값이 변경될 일이 없으므로 final을 써준다.
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "C##PROJECT1";
	private static final String PASSWORD = "PROJECT1";

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Oracle JDBC 드라이버를 로드
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 데이터베이스 연결(Connection 객체)을 만들어서 외부에 제공하는 역할을 하는 메소드이다.
	public static Connection getConnection() {

		Connection conn = null;

		try {
			// 생성해둔 사용자 정보(계정 아이디, 비번)를 이용해 DB의 해당위치(URL)에 연결한다.
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.err.println("DB 연결 실패: " + e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}

	// 데이터베이스나 파일 등의 자원을 사용 후 반드시 닫기 위해 사용하는 메소드이다.
	public static void close(AutoCloseable ac) {
		if (ac != null) {
			try {
				ac.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
