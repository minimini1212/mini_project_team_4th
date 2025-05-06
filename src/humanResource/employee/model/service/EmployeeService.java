package humanResource.employee.model.service;

import common.PasswordEncoder;
import dbConn.ConnectionSingletonHelper;
import humanResource.common.util.EmpNumberGenerator;
import humanResource.employee.model.dao.EmployeeDao;
import humanResource.employee.model.entity.Employee;
import humanResource.userAccount.model.dao.UserAccountDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final EmployeeDao employeeDao = new EmployeeDao();
    private final UserAccountDao userAccountDao = new UserAccountDao();

    // 직원 + 계정 등록
    public void createEmployee(Employee emp, String rawPassword) {
        Connection conn = null;

        try {
            conn = ConnectionSingletonHelper.getConnection("oracle");
            if (conn == null || conn.isClosed()) {
                throw new SQLException("⚠ 유효하지 않은 DB 연결입니다.");
            }

            conn.setAutoCommit(false);

            // DAO에 커넥션 전달
            employeeDao.connect(conn);
            userAccountDao.connect(conn);

            String empNumber = EmpNumberGenerator.generateEmpNumber(emp.getHireDate(), emp.getDepartmentId(), emp.getPhone());
            emp.setEmpNumber(empNumber);
            emp.setStatus("재직");

            employeeDao.insert(emp);
            int employeeId = employeeDao.findIdByEmpNumber(emp.getEmpNumber());

            String encryptedPw = PasswordEncoder.encode(rawPassword);
            userAccountDao.insertAccount(employeeId, empNumber, encryptedPw);

            conn.commit();
            System.out.println("✅ 회원가입 완료! 생성된 사번(empNumber): " + empNumber);

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
                System.out.println("❌ 롤백 완료");
            } catch (SQLException se) {
                System.out.println("❌ 롤백 중 오류: " + se.getMessage());
            }
            System.out.println("❌ 회원가입 중 오류: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("❌ 커넥션 닫는 중 오류: " + e.getMessage());
            }
        }
    }



    // 직원 정보 수정
    public void updateEmployeeInfo(String empNumber, String name, String address, String phone,
                                   String email, String status, String positionStr, String jobStr) throws SQLException {
        Connection conn = ConnectionSingletonHelper.getConnection("oracle");
        try {
            employeeDao.connect(conn);
            Employee existing = employeeDao.findByEmpNumber(empNumber);

            if (existing == null) {
                throw new IllegalArgumentException("해당 사번의 직원이 존재하지 않습니다.");
            }

            if (!name.isEmpty()) existing.setName(name);
            if (!address.isEmpty()) existing.setAddress(address);
            if (!phone.isEmpty()) existing.setPhone(phone);
            if (!email.isEmpty()) existing.setEmail(email);
            if (!status.isEmpty()) existing.setStatus(status);
            if (!positionStr.isEmpty()) existing.setPositionId(Integer.parseInt(positionStr));
            if (!jobStr.isEmpty()) existing.setJobId(Integer.parseInt(jobStr));

            employeeDao.update(existing);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            employeeDao.close();
        }
    }


    // 직원 삭제
    public void deleteEmployee(String empNumber) throws Exception {
        Connection conn = ConnectionSingletonHelper.getConnection("oracle");
        try {
            employeeDao.connect(conn);
            Employee emp = employeeDao.findByEmpNumber(empNumber);
            if (emp == null) {
                throw new Exception("해당 사번의 직원이 존재하지 않습니다.");
            }
            emp.setDelYn("Y");
            employeeDao.update(emp);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            employeeDao.close();
        }
    }

    // 이름으로 검색
    public List<Employee> findByName(String name) throws SQLException {
        Connection conn = ConnectionSingletonHelper.getConnection("oracle");
        try {
            employeeDao.connect(conn);
            return employeeDao.findByName(name);
        } finally {
            employeeDao.close();
        }
    }

    // 사번으로 검색
    public Employee findByEmpNumber(String empNumber) throws SQLException {
        Connection conn = ConnectionSingletonHelper.getConnection("oracle");
        try {
            employeeDao.connect(conn);
            return employeeDao.findByEmpNumber(empNumber);
        } finally {
            employeeDao.close();
        }
    }

    // 부서 ID로 검색
    public List<Employee> findByDepartmentId(int deptId) throws SQLException {
        Connection conn = ConnectionSingletonHelper.getConnection("oracle");
        try {
            employeeDao.connect(conn);
            return employeeDao.findByDepartmentId(deptId);
        } finally {
            employeeDao.close();
        }
    }

    public static boolean isValidPhoneNumber(String phone) {
        return phone.matches("^010-\\d{4}-\\d{4}$");
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
}
