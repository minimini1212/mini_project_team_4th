package humanResource.employee.model.service;

import common.PasswordEncoder;
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
        employeeDao.connect();
        userAccountDao.connect();

        try {
            Connection conn = employeeDao.getConn();  // 동일 커넥션 사용 전제
            conn.setAutoCommit(false);

            String empNumber = EmpNumberGenerator.generateEmpNumber(emp.getHireDate(), emp.getDepartmentId(), emp.getPhone());
            emp.setEmpNumber(empNumber);
            emp.setStatus("재직");

            employeeDao.insert(emp);

            int employeeId = employeeDao.findIdByEmpNumber(empNumber);
            String encryptedPw = PasswordEncoder.encode(rawPassword);
            userAccountDao.insertAccount(employeeId, empNumber, encryptedPw);

            conn.commit();
            System.out.println("✅ 회원가입 완료! 생성된 사번(empNumber): " + empNumber);

        } catch (Exception e) {
            try {
                if (employeeDao.getConn() != null) {
                    employeeDao.getConn().rollback();
                    System.out.println("⚠ 트랜잭션 롤백 완료");
                }
            } catch (SQLException se) {
                System.out.println("❌ 롤백 중 오류 발생: " + se.getMessage());
            }
            System.out.println("❌ 회원가입 중 오류: " + e.getMessage());
            e.printStackTrace();

        } finally {
            employeeDao.close();
            userAccountDao.close();
        }
    }

    // 직원 정보 수정
    public void updateEmployeeInfo(Employee newData) throws SQLException {
        employeeDao.connect();
        try {
            Employee existing = employeeDao.findByEmpNumber(newData.getEmpNumber());
            if (existing == null) {
                throw new IllegalArgumentException("❌ 해당 사번의 직원이 존재하지 않습니다.");
            }

            if (newData.getName() != null) existing.setName(newData.getName());
            if (newData.getPhone() != null) existing.setPhone(newData.getPhone());
            if (newData.getAddress() != null) existing.setAddress(newData.getAddress());
            if (newData.getEmail() != null) existing.setEmail(newData.getEmail());

            employeeDao.update(existing);

        } finally {
            employeeDao.close();
        }
    }

    // 직원 삭제
    public void deleteEmployee(String empNumber) throws Exception {
        employeeDao.connect();
        try {
            Employee emp = employeeDao.findByEmpNumber(empNumber);
            if (emp == null) {
                throw new Exception("❌ 해당 사번의 직원이 존재하지 않습니다.");
            }

            emp.setDelYn("Y");
            employeeDao.update(emp);

        } finally {
            employeeDao.close();
        }
    }

    // 이름으로 검색
    public List<Employee> findByName(String name) throws SQLException {
        employeeDao.connect();
        try {
            return employeeDao.findByName(name);
        } finally {
            employeeDao.close();
        }
    }

    // 사번으로 검색
    public Employee findByEmpNumber(String empNumber) throws SQLException {
        employeeDao.connect();
        try {
            return employeeDao.findByEmpNumber(empNumber);
        } finally {
            employeeDao.close();
        }
    }

    // 부서 ID로 검색
    public List<Employee> findByDepartmentId(int deptId) throws SQLException {
        employeeDao.connect();
        try {
            return employeeDao.findByDepartmentId(deptId);
        } finally {
            employeeDao.close();
        }
    }
}
