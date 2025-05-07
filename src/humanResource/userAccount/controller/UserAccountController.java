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

import common.view.HospitalBannerUtils;

@RequiredArgsConstructor
public class UserAccountController {

    Scanner scanner = new Scanner(System.in);
    UserAccountService userAccountService = new UserAccountService();
    EmployeeService employeeService = new EmployeeService();
    UserAccountView userAccountView = new UserAccountView();

    public boolean loginMenu() {
        while (true) {
            userAccountView.findUserAccountMenu();
            String input = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ 보기의 번호를 입력해주세요.");
                continue;
            }

            switch (choice) {
                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    return false;
                }
                case 1 -> {
                    login();
                    if (SessionContext.isLoggedIn()) {
                        return true;  // 로그인 성공 시 true 반환
                    }
                }
                case 2 -> createEmployee();
                default -> System.out.println("❌ 보기의 번호를 선택해주세요.");
            }
        }
    }


    private void login() {
        //System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        System.out.println();
        HospitalBannerUtils.printLoginBanner();
        System.out.println();
        System.out.print("사번: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호: ");
        String pw = scanner.nextLine();

        try {
            Employee emp = userAccountService.login(id, pw);
            if (emp != null) {
                System.out.println("\n✅ 로그인 성공! 안녕하세요, " + emp.getName() + "님.");
                System.out.println();
            } else {
                System.out.println("❌ 로그인 실패. 정보가 일치하지 않습니다.");
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void createEmployee() {
        try {
            System.out.println();
            HospitalBannerUtils.printRegisterBanner();
            System.out.println();
            if (!promptYesOrNo("🔙 회원가입을 진행하시겠습니까?")) {
                System.out.println();
                System.out.println("🔙 회원가입이 취소되었습니다. 로그인 메뉴로 돌아갑니다.");
                return;
            }

            Employee emp = new Employee();

            emp.setName(promptNonEmptyInput("이름: "));

            String phone = promptValidatedInput(
                    "전화번호(예: 010-1234-5678): ",
                    "\\d{3}-\\d{4}-\\d{4}",
                    "잘못된 전화번호 형식입니다. 010-1234-5678 형태로 입력해주세요."
            );
            emp.setPhone(phone);

            String hireStr = promptValidatedInput(
                    "입사일(예: yyyy-MM-dd): ",
                    "\\d{4}-\\d{2}-\\d{2}",
                    "잘못된 형식입니다. yyyy-MM-dd 형태로 입력해주세요."
            );
            Date hireDate = new SimpleDateFormat("yyyy-MM-dd").parse(hireStr);
            emp.setHireDate(hireDate);

            System.out.println("부서 선택 \n ① 인사 관리 부서 \n ② 예산・회계 관리 부서 \n ③ 자산 관리 부서");
            int deptChoice = promptIntInRange("선택: ", 1, 3);
            emp.setDepartmentId(deptChoice);

            System.out.println("직급 선택 \n ① 병원장 \n ② 부장 \n ③ 차장 \n ④ 과장 \n ⑤ 대리 \n ⑥ 사원 \n ⑦ 인턴");
            int positionChoice = promptIntInRange("선택: ", 1, 7);
            emp.setPositionId(positionChoice);

            System.out.println("근무 형태 선택 \n ① 계약직 \n ② 정규직");
            int empTypeChoice = promptIntInRange("선택: ", 1, 2);
            emp.setEmpType(empTypeChoice == 1 ? "계약직" : "정규직");

            String pw;
            while (true) {
                pw = promptNonEmptyInput("비밀번호 (8자 이상, 특수문자, 숫자 포함): ");
                if (isValidPassword(pw)) break;
                System.out.println("비밀번호가 조건을 만족하지 않습니다. 다시 입력해주세요.");
            }

            employeeService.createEmployee(emp, pw);
            System.out.println("✅ 회원가입이 완료되었습니다.");
            System.out.println("🔙 로그인 메뉴로 돌아갑니다.");

        } catch (Exception e) {
            System.out.println("❌ 회원가입 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String promptNonEmptyInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("입력은 필수입니다. 다시 입력해주세요.");
        }
    }

    private String promptValidatedInput(String prompt, String regex, String errorMsg) {
        while (true) {
            String input = promptNonEmptyInput(prompt);
            if (input.matches(regex)) return input;
            System.out.println(errorMsg);
        }
    }

    private int promptIntInRange(String prompt, int min, int max) {
        while (true) {
            String input = promptNonEmptyInput(prompt);
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) return value;
            } catch (NumberFormatException ignored) {}
            System.out.printf("잘못된 입력입니다. %d ~ %d 사이의 숫자를 입력해주세요.%n", min, max);
        }
    }

    public boolean promptYesOrNo(String message) {
        while (true) {
            System.out.print(message + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y")) return true;
            if (input.equals("n")) return false;

            System.out.println("❌ 잘못된 입력입니다. y 또는 n을 입력해주세요.");
        }
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[0-9].*") &&  // 숫자 포함
                password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");  // 특수문자 포함
    }

}
