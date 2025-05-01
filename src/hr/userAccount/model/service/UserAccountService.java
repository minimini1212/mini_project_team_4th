package hr.userAccount.model.service;

import dbConn.ConnectionSingletonHelper;
import hr.common.util.EmpNumberGenerator;
import hr.department.model.dao.DepartmentDao;
import hr.employee.model.dao.EmployeeDao;
import hr.employee.model.entity.Employee;
import hr.userAccount.model.dao.UserAccountDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class UserAccountService {
    private final UserAccountDao userAccountDao;
    private final EmployeeDao employeeDao;
    private final DepartmentDao departmentDao;

    public UserAccountService(UserAccountDao userAccountDao, EmployeeDao employeeDao, DepartmentDao departmentDao) {
        this.userAccountDao = userAccountDao;
        this.employeeDao = employeeDao;
        this.departmentDao = departmentDao;
    }

    public Employee login(String userId, String password) throws SQLException {
        String pw = userAccountDao.findPasswordByUserId(userId);
        if (pw != null && pw.equals(password)) {
            return employeeDao.findByEmpNumber(userId);     // Employee.EmpNumber = UserAccount.UserId
        }
        return null;
    }

    public void register(){
        Scanner sc = new Scanner(System.in);
        Connection conn = null;

        try {
            conn = ConnectionSingletonHelper.getConnection("oracle");
            conn.setAutoCommit(false);

            System.out.println("===== 회원가입 =====");
            Employee emp = new Employee();

            System.out.print("이름: ");
            emp.setName(sc.nextLine());

            System.out.print("전화번호 (예: 010-1234-5678): ");
            emp.setPhone(sc.nextLine());

            System.out.print("입사일 (yyyy-MM-dd): ");
            String hireStr = sc.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date hireDate = sdf.parse(hireStr);
            emp.setHireDate(hireDate);

            System.out.print("부서 ID: ");
            emp.setDepartmentId(Integer.parseInt(sc.nextLine()));

            System.out.print("직급 ID: ");
            emp.setPositionId(Integer.parseInt(sc.nextLine()));

            System.out.println("근무 형태(계약직 or 정규직): ");
            emp.setEmpType(sc.nextLine());

            System.out.print("비밀번호: ");
            String pw = sc.nextLine();


//            String deptCode = departmentDao.findDepartmentCodeById(emp.getDepartmentId());
            String empNumber = EmpNumberGenerator.generateEmpNumber(emp.getHireDate(), emp.getDepartmentId(), emp.getPhone());
            System.out.println("empNumber: " + empNumber);
            emp.setEmpNumber(empNumber);
            emp.setStatus("재직");        // 기본 재직 상태

            employeeDao.insert(emp);
            int employeeId = employeeDao.findIdByEmpNumber(emp.getEmpNumber());
            userAccountDao.insertAccount(employeeId, empNumber, pw);

            conn.commit();  // 모든 insert 완료 후 커밋
            System.out.println("회원가입 완료! 생성된 사번(empId): " + empNumber);

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback(); // 실패 시 롤백
            } catch (SQLException se) {
                System.out.println("롤백 중 오류: " + se.getMessage());
            }
            System.out.println("회원가입 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true); // 트랜잭션 원복
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
