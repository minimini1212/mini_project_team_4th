package humanResource.userAccount.controller;

import common.SessionContext;
import humanResource.employee.model.entity.Employee;
import humanResource.employee.model.service.EmployeeService;
import humanResource.userAccount.model.service.UserAccountService;
import humanResource.userAccount.view.UserAccountView;
import lombok.RequiredArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@RequiredArgsConstructor
public class UserAccountController {

    Scanner scanner = new Scanner(System.in);
    UserAccountService userAccountService = new UserAccountService();
    EmployeeService employeeService = new EmployeeService();
    UserAccountView userAccountView = new UserAccountView();

    public boolean loginMenu() {
        while (true) {
            userAccountView.findUserAccountMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    login();
                    if (SessionContext.isLoggedIn()) {
                        return true;  // 로그인 성공 시 true 반환
                    }
                }

                case 2 -> createEmployee();
                case 0 -> {
                    System.out.println("프로그램 종료");
                    return false;
                }
                default -> System.out.println("보기의 번호를 선택해주세요.");
            }
        }
    }

    private void login() {
        System.out.println("===== 로그인 =====");
        System.out.print("사번: ");
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

    private void createEmployee() {
        try {
            System.out.println("===== 회원가입 =====");
            Employee emp = new Employee();

            System.out.print("이름: ");
            emp.setName(scanner.nextLine());

            System.out.print("전화번호(예: 010-1234-5678): ");
            String phone = scanner.nextLine();
            while (!phone.matches("\\d{3}-\\d{4}-\\d{4}")) {
                System.out.println("잘못된 전화번호 형식입니다. 하이픈(-) 포함하여 010-1234-1234 형태로 입력해주세요.");
                System.out.print("전화번호 (예: 010-1234-1234): ");
                phone = scanner.nextLine();
            }
            emp.setPhone(phone);

            System.out.print("입사일(예: yyyy-MM-dd): ");
            String hireStr = scanner.nextLine();
            while (!hireStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("잘못된 생일 형식입니다. 하이픈(-) 포함하여 yyyy-MM-dd 형태로 입력해주세요.");
                System.out.print("입사일 (yyyy-MM-dd): ");
                hireStr = scanner.nextLine();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date hireDate = sdf.parse(hireStr);
            emp.setHireDate(hireDate);

            System.out.println("부서 선택");
            System.out.println("1. 인사 관리 부서 / 2. 회계 관리 부서 / 3. 자산 관리 부서");
            System.out.print("선택: ");
            int deptChoice = Integer.parseInt(scanner.nextLine());
            switch (deptChoice) {
                case 1 -> emp.setDepartmentId(1);
                case 2 -> emp.setDepartmentId(2);
                case 3 -> emp.setDepartmentId(3);
                default -> {
                    System.out.println("잘못된 부서 선택입니다. 기본값으로 인사 관리 부서로 설정됩니다.");
                    emp.setDepartmentId(1);
                }
            }

            System.out.println("직급 선택");
            System.out.println("1. 병원장 / 2. 진료부장 / 3. 행정부장 / 4. 과장 / 5. 전문의");
            System.out.println("6. 주임 / 7. 레지던트 / 8. 대리 / 9. 인턴 / 10. 사원");
            int positionChoice = 0;
            while (true) {
                System.out.print("선택: ");
                positionChoice = Integer.parseInt(scanner.nextLine());
                if (positionChoice >= 1 && positionChoice <= 10) break;
                else System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
            emp.setPositionId(positionChoice);

            System.out.println("근무 형태 선택");
            System.out.println("1. 계약직 / 2. 정규직");
            int empTypeChoice = 0;
            while (true) {
                System.out.print("선택: ");
                empTypeChoice = Integer.parseInt(scanner.nextLine());
                if (empTypeChoice == 1 || empTypeChoice == 2) break;
                else System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
            emp.setEmpType(empTypeChoice == 1 ? "계약직" : "정규직");

            String pw = "";
            while (true) {
                System.out.print("비밀번호 (8자 이상, 특수문자, 숫자 포함): ");
                pw = scanner.nextLine();
                if (isValidPassword(pw)) break;
                else System.out.println("비밀번호가 조건을 만족하지 않습니다. 다시 시도하세요.");
            }

            employeeService.createEmployee(emp, pw);

            System.out.println("✅ 회원가입이 완료되었습니다.");
            System.out.println("🔙 로그인 메뉴로 돌아갑니다.");

        } catch (Exception e) {
            System.out.println("❌ 회원가입 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }



    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[0-9].*") &&  // 숫자 포함
                password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");  // 특수문자 포함
    }

//    public void logout() {
//        if (!SessionContext.isLoggedIn()) {
//            System.out.println("⚠️ 현재 로그인된 사용자가 없습니다.");
//            return;
//        }
//
//        String name = SessionContext.getUser().getName();
//        SessionContext.clear();
//        System.out.println("✅ " + name + " 님이 로그아웃되었습니다.");
//    }


}
