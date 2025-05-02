package hr.employee.model.dao;

import hr.employee.model.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao {
    private final Connection conn;

    public EmployeeDao(Connection conn) {
        this.conn = conn;
    }

    public void insert(Employee emp) throws SQLException {
        String sql = "INSERT INTO employee (emp_number, name, phone, hire_date, department_id, position_id, status, emp_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, emp.getEmpNumber());
            pstmt.setString(2, emp.getName());
            pstmt.setString(3, emp.getPhone());
            pstmt.setDate(4, new java.sql.Date(emp.getHireDate().getTime()));
            pstmt.setInt(5, emp.getDepartmentId());
            pstmt.setInt(6, emp.getPositionId());
            pstmt.setString(7, emp.getStatus());
            pstmt.setString(8, emp.getEmpType());
            System.out.println("pstmt 실행 직전");
            pstmt.executeUpdate();  // ← 이 라인에서 멈춘다면 DB 응답 지연
            System.out.println("pstmt 실행 완료");
        }
    }

    public Employee findByEmpNumber(String empNumber) throws SQLException {
        String sql = "SELECT * FROM employee WHERE emp_number = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, empNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setName(rs.getString("name"));
                emp.setPhone(rs.getString("phone"));
                emp.setHireDate(rs.getDate("hire_date"));
                emp.setDepartmentId(rs.getInt("department_id"));
                emp.setPositionId(rs.getInt("position_id"));
                return emp;
            }
        }
        return null;
    }

    public int findIdByEmpNumber(String empNumber) throws SQLException {
        String sql = "SELECT employee_id FROM employee WHERE emp_number = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, empNumber);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("employee_id");
            }
        }
        throw new SQLException("해당 emp_number로 employee_id를 찾을 수 없습니다.");
    }
}
