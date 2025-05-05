package humanResource.userAccount.model.dao;

import dbConn.CloseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public void connect(Connection conn) throws SQLException {
        this.conn = conn;
        if (this.conn.isClosed()) {
            throw new SQLException("⚠ 연결이 이미 종료되었습니다.");
        }
        this.conn.setAutoCommit(false);  // 트랜잭션 수동 관리
    }

    // 자원 해제
    public void close() {
        CloseHelper.close(rs);
        CloseHelper.close(pstmt);
        // conn.close()는 서비스 계층에서 처리
    }

    public String findPasswordByUserId(String userId) throws SQLException {
        String sql = "SELECT password FROM user_account WHERE user_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userId);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getString("password");
        }
        return null;
    }

    public void insertAccount(int employeeId, String userId, String password) throws SQLException {
        String sql = "INSERT INTO user_account (account_id, employee_id, user_id, password) VALUES (account_id_seq.NEXTVAL, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, employeeId);
        pstmt.setString(2, userId);
        pstmt.setString(3, password);
        pstmt.executeUpdate();
    }
}
