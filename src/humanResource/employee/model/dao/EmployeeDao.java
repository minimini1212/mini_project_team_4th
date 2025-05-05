package humanResource.employee.model.dao;

import dbConn.CloseHelper;
import dbConn.ConnectionSingletonHelper;
import humanResource.employee.model.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public void connect(Connection conn) throws SQLException {
        this.conn = conn;
        if (this.conn == null || this.conn.isClosed()) {
            throw new SQLException("⚠ 연결이 유효하지 않습니다.");
        }
        this.conn.setAutoCommit(false);
    }

    public void close() {
        CloseHelper.close(rs);
        CloseHelper.close(pstmt);
    }

    public void insert(Employee emp) throws SQLException {
        String sql = "INSERT INTO employee (emp_number, name, phone, hire_date, department_id, position_id, status, emp_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, emp.getEmpNumber());
        pstmt.setString(2, emp.getName());
        pstmt.setString(3, emp.getPhone());
        pstmt.setDate(4, new java.sql.Date(emp.getHireDate().getTime()));
        pstmt.setInt(5, emp.getDepartmentId());
        pstmt.setInt(6, emp.getPositionId());
        pstmt.setString(7, emp.getStatus());
        pstmt.setString(8, emp.getEmpType());
        System.out.println("pstmt 실행 직전");
        pstmt.executeUpdate();
        System.out.println("pstmt 실행 완료");
    }

    public Employee findByEmpNumber(String empNumber) throws SQLException {
        String sql = "SELECT * FROM employee WHERE emp_number = ? AND del_yn = 'N'";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, empNumber);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            return mapRowToEmployee(rs);
        }
        return null;
    }

    public int findIdByEmpNumber(String empNumber) throws SQLException {
        String sql = "SELECT employee_id FROM employee WHERE emp_number = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, empNumber);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("employee_id");
        }
        throw new SQLException("해당 emp_number로 employee_id를 찾을 수 없습니다.");
    }

    public void update(Employee emp) throws SQLException {
        String sql = """
        UPDATE employee
        SET name = ?, address = ?, phone = ?, email = ?, del_yn = ?
        WHERE employee_id = (
            SELECT employee_id FROM employee WHERE emp_number = ?
        )
        """;

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, emp.getName());
        pstmt.setString(2, emp.getAddress());
        pstmt.setString(3, emp.getPhone());
        pstmt.setString(4, emp.getEmail());
        pstmt.setString(5, emp.getDelYn());
        pstmt.setString(6, emp.getEmpNumber());

        pstmt.executeUpdate();
    }

    public void deleteByEmpNumber(String empNumber) throws SQLException {
        String sql = """
        UPDATE employee
        SET del_yn = 'Y'
        WHERE emp_number = ?
        """;

        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, empNumber);
        int updated = pstmt.executeUpdate();
        if (updated == 0) {
            throw new SQLException("삭제할 직원이 존재하지 않습니다.");
        }
    }

    public List<Employee> findByName(String name) throws SQLException {
        String sql = "SELECT * FROM employee WHERE name LIKE ? AND del_yn = 'N'";
        List<Employee> list = new ArrayList<>();
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "%" + name + "%");
        rs = pstmt.executeQuery();
        while (rs.next()) {
            list.add(mapRowToEmployee(rs));
        }
        return list;
    }

    public List<Employee> findByDepartmentId(int deptId) throws SQLException {
        String sql = "SELECT * FROM employee WHERE department_id = ? AND del_yn = 'N'";
        List<Employee> list = new ArrayList<>();
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, deptId);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            list.add(mapRowToEmployee(rs));
        }
        return list;
    }

    private Employee mapRowToEmployee(ResultSet rs) throws SQLException {
        Employee emp = new Employee();
        emp.setEmployeeId(rs.getInt("employee_id"));
        emp.setEmpNumber(rs.getString("emp_number"));
        emp.setName(rs.getString("name"));
        emp.setPhone(rs.getString("phone"));
        emp.setHireDate(rs.getDate("hire_date"));
        emp.setDepartmentId(rs.getInt("department_id"));
        emp.setPositionId(rs.getInt("position_id"));
        emp.setEmpType(rs.getString("emp_type"));
        emp.setDelYn(rs.getString("del_yn"));
        return emp;
    }
}
