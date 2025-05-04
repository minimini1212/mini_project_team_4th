package humanResource.userAccount.controller;

import common.SessionContext;
import humanResource.employee.model.entity.Employee;
import humanResource.userAccount.model.service.UserAccountService;
import humanResource.userAccount.view.UserAccountView;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@RequiredArgsConstructor
public class UserAccountController {

    private final Scanner scanner;
    private final UserAccountService userAccountService;
    private final UserAccountView userAccountView = new UserAccountView();

    public void menu() {
        userAccountView.findUserAccountMenu();
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> login();
            case 2 -> register();
            case 0 -> {
                System.out.println("프로그램 종료");
                return;
            }
            default -> System.out.println("잘못된 입력입니다.");
        }
    }

    private void login() {
        System.out.print("사번(empId): ");
        String id = scanner.nextLine();
        System.out.print("비밀번호: ");
        String pw = scanner.nextLine();

        try {
            Employee emp = userAccountService.login(id, pw);
            if (emp != null) {
                System.out.println("✅ 로그인 성공! 안녕하세요, " + emp.getName() + "님.");
            } else {
                System.out.println("❌ 로그인 실패. 정보가 일치하지 않습니다.");
            }
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void register() {
        try {
            System.out.println("===== 회원가입 =====");
            Employee emp = new Employee();

            System.out.print("이름: ");
            emp.setName(scanner.nextLine());

            System.out.print("전화번호 (예: 010-1234-5678): ");
            emp.setPhone(scanner.nextLine());

            System.out.print("입사일 (yyyy-MM-dd): ");
            String hireStr = scanner.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date hireDate = sdf.parse(hireStr);
            emp.setHireDate(hireDate);

            System.out.print("부서 ID: ");
            emp.setDepartmentId(Integer.parseInt(scanner.nextLine()));

            System.out.print("직급 ID: ");
            emp.setPositionId(Integer.parseInt(scanner.nextLine()));

            System.out.print("근무 형태(계약직 or 정규직): ");
            emp.setEmpType(scanner.nextLine());

            System.out.print("비밀번호: ");
            String pw = scanner.nextLine();

            userAccountService.register(emp, pw);

        } catch (Exception e) {
            System.out.println("회원가입 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Employee getLoggedInUser() {
        return SessionContext.getUser();
    }

    public String getUserRole() {
        return SessionContext.getRole();
    }

}
