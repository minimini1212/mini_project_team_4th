package hr.view;

import dbConn.ConnectionSingletonHelper;
import hr.department.model.dao.DepartmentDao;
import hr.employee.model.dao.EmployeeDao;
import hr.employee.model.entity.Employee;
import hr.userAccount.model.dao.UserAccountDao;
import hr.userAccount.model.service.UserAccountService;

import java.sql.Connection;
import java.util.Scanner;

public class MainEntry {
    public static void main(String[] args) {
        try {
            Connection conn = ConnectionSingletonHelper.getConnection("oracle");

            EmployeeDao employeeDao = new EmployeeDao(conn);
            DepartmentDao departmentDao = new DepartmentDao(conn);
            UserAccountDao userAccountDao = new UserAccountDao(conn);
            UserAccountService userAccountService = new UserAccountService(userAccountDao, employeeDao, departmentDao);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n====== 병원 재고 시스템 로그인 ======");
                System.out.println("1. 로그인");
                System.out.println("2. 회원가입");
                System.out.println("0. 종료");
                System.out.print("선택: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> {
                        System.out.print("사번(empId): ");
                        String id = scanner.nextLine();
                        System.out.print("비밀번호: ");
                        String pw = scanner.nextLine();

                        Employee emp = userAccountService.login(id, pw);
                        if (emp != null) {
                            System.out.println("로그인 성공: " + emp.getName());
                            // TODO: 이후 시스템 메뉴로 이동
                        } else {
                            System.out.println("로그인 실패. 정보가 일치하지 않습니다.");
                        }
                    }
                    case 2 -> userAccountService.register();
                    case 0 -> {
                        System.out.println("프로그램 종료");
                        return;
                    }
                    default -> System.out.println("잘못된 입력입니다.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
