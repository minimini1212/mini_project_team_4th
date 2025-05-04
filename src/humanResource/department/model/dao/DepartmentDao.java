package humanResource.department.model.dao;

import dbConn.CloseHelper;
import dbConn.ConnectionSingletonHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    /** 연결 **/
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
