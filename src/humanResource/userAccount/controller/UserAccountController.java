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
        while (true) {
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
            boolean continueRegistration = true;
            while (continueRegistration) {
                System.out.println("===== 회원가입 =====");
                Employee emp = new Employee();

                System.out.print("이름: ");
                emp.setName(scanner.nextLine());

                System.out.print("전화번호(예: 010-1234-5678): ");
                String phone = scanner.nextLine();
                while (!phone.matches("\\d{3}-\\d{4}-\\d{4}")) {  // 010-1234-1234 형식만 허용
                    System.out.println("잘못된 전화번호 형식입니다. 하이픈(-) 포함하여 010-1234-1234 형태로 입력해주세요.");
                    System.out.print("전화번호 (예: 010-1234-1234): ");
                    phone = scanner.nextLine();
                }
                emp.setPhone(phone);

                System.out.print("입사일(예: yyyy-MM-dd): ");
                String hireStr = scanner.nextLine();
                while (!hireStr.matches("\\d{4}-\\d{2}-\\d{2}")) {  // yyyy-MM-dd 형식만 허용
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
                    case 1 -> emp.setDepartmentId(1);  // 인사 관리 부서
                    case 2 -> emp.setDepartmentId(2);  // 회계 관리 부서
                    case 3 -> emp.setDepartmentId(3);  // 자산 관리 부서
                    default -> {
                        System.out.println("잘못된 부서 선택입니다. 기본값으로 인사 관리 부서로 설정됩니다.");
                        emp.setDepartmentId(1);  // 기본값으로 인사 관리 부서
                    }
                }

                System.out.println("직급 선택");
                System.out.println("1. 병원장 / 2. 진료부장 / 3. 행정부장 / 4. 과장 / 5. 전문의");
                System.out.println("6. 주임 / 7. 레지던트 / 8. 대리 / 9. 인턴 / 10. 사원");
                int positionChoice = 0;
                while (true) {
                    System.out.print("선택: ");
                    positionChoice = Integer.parseInt(scanner.nextLine());
                    if (positionChoice >= 1 && positionChoice <= 10) {
                        break;  // 유효한 선택일 경우 루프 종료
                    } else {
                        System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
                    }
                }
                emp.setPositionId(positionChoice);

                System.out.println("근무 형태 선택");
                System.out.println("1. 계약직 / 2. 정규직");
                int empTypeChoice = 0;
                while (true) {
                    System.out.print("선택: ");
                    empTypeChoice = Integer.parseInt(scanner.nextLine());
                    if (empTypeChoice == 1 || empTypeChoice == 2) {
                        break;  // 유효한 선택일 경우 루프 종료
                    } else {
                        System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
                    }
                }
                emp.setEmpType(empTypeChoice == 1 ? "계약직" : "정규직");

                String pw = "";
                while (true) {
                    System.out.print("비밀번호 (8자 이상, 특수문자, 숫자 포함): ");
                    pw = scanner.nextLine();
                    if (isValidPassword(pw)) {
                        break;
                    } else {
                        System.out.println("비밀번호가 조건을 만족하지 않습니다. 다시 시도하세요.");
                    }
                }

                userAccountService.register(emp, pw);
            }
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

    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[0-9].*") &&  // 숫자 포함
                password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");  // 특수문자 포함
    }

}
