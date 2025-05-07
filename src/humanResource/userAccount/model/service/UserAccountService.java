package humanResource.userAccount.model.service;

import common.PasswordEncoder;
import common.SessionContext;
import dbConn.ConnectionSingletonHelper;
import humanResource.employee.model.entity.Employee;
import humanResource.department.model.dao.DepartmentDao;
import humanResource.employee.model.dao.EmployeeDao;
import humanResource.position.model.dao.PositionDao;
import humanResource.userAccount.model.dao.UserAccountDao;

import java.sql.Connection;
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

    public Employee login(String userId, String password) throws SQLException {
        Connection conn = null;

        try {
            conn = ConnectionSingletonHelper.getConnection("oracle");
            conn.setAutoCommit(false);

            // DAO 객체 생성 (매번 새로 생성)
            UserAccountDao userAccountDao = new UserAccountDao();
            userAccountDao.connect(conn);

            EmployeeDao employeeDao = new EmployeeDao();
            employeeDao.connect(conn);

            PositionDao positionDao = new PositionDao();
            positionDao.connect(conn);

            DepartmentDao departmentDao = new DepartmentDao();
            departmentDao.connect(conn);


            // 테스트 기간동안만 암호화 검증 해제, 파일 백업 노션에다가.
            String encryptedPwFromDB = userAccountDao.findPasswordByUserId(userId);
            if (encryptedPwFromDB != null && (encryptedPwFromDB.equals(password) || PasswordEncoder.matches(password, encryptedPwFromDB))) {
                Employee emp = employeeDao.findByEmpNumber(userId);
                int rankOrder = positionDao.findRankOrderByPositionId(emp.getPositionId());
                String role = getRoleFromRank(rankOrder);

                SessionContext.set(emp, role);
                SessionContext.setRankOrder(rankOrder);
                SessionContext.setDeptId(emp.getDepartmentId());
                SessionContext.setEmpNumber(emp.getEmpNumber());

                return emp;
            }

            return null;

        } finally {
            // Connection만 닫아도 PreparedStatement는 try-with-resources로 자동 닫히는 구조임
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    private String getRoleFromRank(int rankOrder) {
        if (rankOrder == 1) return "master";
        else if (rankOrder >= 2 && rankOrder <= 4) return "admin";
        else return "basic";
    }
}
