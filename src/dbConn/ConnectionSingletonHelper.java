package dbConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
	ConnectionHelper 크래스의 문제.
	매번 드라이버 로드...
	Connection 생성
	
	어차피 하나의 프로세스에서 하나만 만들어서 재사용하면 될텐데...
	
	해결방법 >> 공유자원(싱글톤)을 쓰겠다.
*/

public class ConnectionSingletonHelper {
	// 하나의 프로세스에서 공통으로 사용할 수 있는 공용자원(static)
	private static Connection conn;

	private ConnectionSingletonHelper() {
	}

	public static Connection getConnection(String dsn) {
		try {
			// 이미 커넥션이 존재하더라도 닫혀있으면 새로 생성
			if (conn != null && !conn.isClosed()) {
				return conn;
			}

			if ("mysql".equals(dsn)) {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kosaDB", "his", "mysql");
			} else if ("oracle".equals(dsn)) {
				Class.forName("oracle.jdbc.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "his", "oracle");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close() throws SQLException {
		if (conn != null) {
			if (!conn.isClosed()) {
				conn.close();
			}
		}
	}

}
