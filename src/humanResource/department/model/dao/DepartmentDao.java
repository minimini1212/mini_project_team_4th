package humanResource.department.model.dao;

import dbConn.CloseHelper;
import dbConn.ConnectionSingletonHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDao {
    private Connection conn;

    // 연결 주입
    public void connect(Connection conn) throws SQLException {
        this.conn = conn;
        if (this.conn.isClosed()) {
            throw new SQLException("⚠ 연결이 이미 종료되었습니다.");
        }
        this.conn.setAutoCommit(false); // 트랜잭션 수동 처리
    }

    // 자원 해제
    public void close() {
        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String findDepartmentCodeById(int departmentId) throws SQLException {
        String sql = "SELECT department_code FROM department WHERE department_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, departmentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("department_code");
            }
        }
        return null;
    }
}
