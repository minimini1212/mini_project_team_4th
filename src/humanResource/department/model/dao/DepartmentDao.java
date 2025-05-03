package humanResource.department.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDao {
    private final Connection conn;

    public DepartmentDao(Connection conn) {
        this.conn = conn;
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
