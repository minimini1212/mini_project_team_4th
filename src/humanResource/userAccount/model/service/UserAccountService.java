package humanResource.userAccount.model.service;

import common.SessionContext;
import dbConn.ConnectionSingletonHelper;
import humanResource.common.util.EmpNumberGenerator;
import humanResource.department.model.dao.DepartmentDao;
import humanResource.employee.model.dao.EmployeeDao;
import humanResource.employee.model.entity.Employee;
import humanResource.position.model.dao.PositionDao;
import humanResource.userAccount.model.dao.UserAccountDao;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


@RequiredArgsConstructor
public class UserAccountService {

    private final Connection conn;
    private final UserAccountDao userAccountDao = new UserAccountDao(conn);
    private final EmployeeDao employeeDao = new EmployeeDao(conn);
    private final PositionDao positionDao = new PositionDao(conn);

    public Employee login(String userId, String password) throws SQLException {
        String pw = userAccountDao.findPasswordByUserId(userId);
        if (pw != null && pw.equals(password)) {
            Employee emp = employeeDao.findByEmpNumber(userId);
            int rankOrder = positionDao.findRankOrderByPositionId(emp.getPositionId());

            String role = getRoleFromRank(rankOrder);
            SessionContext.set(emp, role);
            return emp;
        }

        return null;
    }

    private String getRoleFromRank(int rankOrder) {
        if (rankOrder == 1) return "master";
        else if (rankOrder >= 2 && rankOrder <= 4) return "admin";
        else return "basic";
    }

    public void register(Employee emp, String pw) {
        Connection conn = null;
        try {
            conn = ConnectionSingletonHelper.getConnection("oracle");
            conn.setAutoCommit(false);

            String empNumber = EmpNumberGenerator.generateEmpNumber(emp.getHireDate(), emp.getDepartmentId(), emp.getPhone());
            emp.setEmpNumber(empNumber);
            emp.setStatus("재직"); // 기본 상태

            employeeDao.insert(emp);
            int employeeId = employeeDao.findIdByEmpNumber(emp.getEmpNumber());
            userAccountDao.insertAccount(employeeId, empNumber, pw);

            conn.commit();
            System.out.println("회원가입 완료! 생성된 사번(empId): " + empNumber);
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException se) {
                System.out.println("롤백 중 오류: " + se.getMessage());
            }
            System.out.println("회원가입 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
