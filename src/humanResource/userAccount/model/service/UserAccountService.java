package humanResource.userAccount.model.service;

import common.PasswordEncoder;
import common.SessionContext;
import humanResource.employee.model.entity.Employee;
import humanResource.department.model.dao.DepartmentDao;
import humanResource.employee.model.dao.EmployeeDao;
import humanResource.position.model.dao.PositionDao;
import humanResource.userAccount.model.dao.UserAccountDao;

import java.sql.SQLException;

public class UserAccountService {

    private final UserAccountDao userAccountDao;
    private final EmployeeDao employeeDao;
    private final PositionDao positionDao;
    private final DepartmentDao departmentDao;

    public UserAccountService() {
        this.userAccountDao = new UserAccountDao();
        this.employeeDao = new EmployeeDao();
        this.positionDao = new PositionDao();
        this.departmentDao = new DepartmentDao();
    }

    public Employee login(String userId, String password) {
        try {
            // 1. 연결
            userAccountDao.connect();
            employeeDao.connect();
            positionDao.connect();
            departmentDao.connect();

            // 2. 비밀번호 확인
            String encryptedPwFromDB = userAccountDao.findPasswordByUserId(userId);
            if (encryptedPwFromDB != null && PasswordEncoder.matches(password, encryptedPwFromDB)) {
                Employee emp = employeeDao.findByEmpNumber(userId);
                int rankOrder = positionDao.findRankOrderByPositionId(emp.getPositionId());
                String role = getRoleFromRank(rankOrder);

                // 세션 설정
                SessionContext.set(emp, role);
                SessionContext.setRankOrder(rankOrder);
                SessionContext.setDeptId(emp.getDepartmentId());

                return emp;
            }
            return null;

        } catch (SQLException e) {
            System.out.println("❌ 로그인 중 오류 발생: " + e.getMessage());
            return null;

        } finally {
            // 3. 연결 해제
            userAccountDao.close();
            employeeDao.close();
            positionDao.close();
            departmentDao.close();
        }
    }

    private String getRoleFromRank(int rankOrder) {
        if (rankOrder == 1) return "master";
        else if (rankOrder >= 2 && rankOrder <= 4) return "admin";
        else return "basic";
    }
}
