package humanResource.userAccount.model.dao;

import dbConn.CloseHelper;
import dbConn.ConnectionSingletonHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public void connect() {
        try {
            conn = ConnectionSingletonHelper.getConnection("oracle");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            CloseHelper.close(rs);
            CloseHelper.close(pstmt);
            CloseHelper.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findPasswordByUserId(String userId) throws SQLException {
        String sql = "SELECT password FROM user_account WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        }
        return null;
    }

    public void insertAccount(int employeeId, String userId, String password) throws SQLException {
        String sql = "INSERT INTO user_account (employee_id, user_id, password) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            pstmt.setString(2, userId);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
        }
    }
}
