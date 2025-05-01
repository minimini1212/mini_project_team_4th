package hr.userAccount.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccountDao {
    private final Connection conn;

    public UserAccountDao(Connection conn) {
        this.conn = conn;
    }

    public String findPasswordByEmpId(String empId) throws SQLException {
        String sql = "SELECT password FROM account WHERE employee_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, empId);
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
