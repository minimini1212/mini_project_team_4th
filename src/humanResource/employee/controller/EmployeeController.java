package humanResource.employee.controller;

import common.SessionContext;
import humanResource.employee.model.entity.Employee;
import humanResource.employee.model.service.EmployeeService;
import humanResource.employee.view.EmployeeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeController {
    Scanner scanner = new Scanner(System.in);
    EmployeeService employeeService = new EmployeeService();
    EmployeeView employeeView = new EmployeeView();


    public void run() {
        while (true) {
            System.out.println("\n===== 직원 관리 메뉴 =====");
            System.out.println("0. 뒤로 가기");
            System.out.println("1. 직원 조회");
            System.out.println("2. 직원 정보 수정");
            System.out.println("3. 직원 삭제");
            System.out.print("선택: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 0 -> {
                        System.out.println("🔙 이전 메뉴로 돌아갑니다.");
                        return;
                    }
                    case 1 -> searchEmployee();
                    case 2 -> updateEmployeeInfo();
                    case 3 -> deleteEmployee();
                    default -> System.out.println("❌ 잘못된 선택입니다. 0~4 사이의 번호를 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ 숫자만 입력해주세요.");
            } catch (Exception e) {
                System.out.println("❌ 직원 관리 중 오류 발생: " + e.getMessage());
            }
        }
    }

    public void updateEmployeeInfo() {
        if (!SessionContext.isLoggedIn()) {
            System.out.println("로그인이 필요합니다.");
            return;
        }

        Employee current = SessionContext.getUser();
        System.out.println("[계정 정보 수정]");

        Employee updated = new Employee();
        updated.setEmpNumber(current.getEmpNumber());

        System.out.print("이름 수정 (현재: " + current.getName() + "): ");
        String name = scanner.nextLine();
        updated.setName(name.isBlank() ? current.getName() : name);

        System.out.print("주소 수정 (현재: " + current.getAddress() + "): ");
        String address = scanner.nextLine();
        updated.setAddress(address.isBlank() ? current.getAddress() : address);

        System.out.print("전화번호 수정 (현재: " + current.getPhone() + "): ");
        String phone = scanner.nextLine();
        updated.setPhone(phone.isBlank() ? current.getPhone() : phone);

        System.out.print("이메일 수정 (현재: " + current.getEmail() + "): ");
        String email = scanner.nextLine();
        updated.setEmail(email.isBlank() ? current.getEmail() : email);

        try {
            employeeService.updateEmployeeInfo(updated);
            System.out.println("✅ 계정 정보가 수정되었습니다.");
        } catch (Exception e) {
            System.out.println("❌ 계정 정보 수정 중 오류: " + e.getMessage());
        }
    }


    public void deleteEmployee() {
        System.out.println("===== 직원 삭제 =====");
        try {
            System.out.print("삭제할 직원의 사번(empNumber)을 입력하세요: ");
            String empNumber = scanner.nextLine();

            String confirm;
            while (true) {
                System.out.print("정말 삭제하시겠습니까? (y/n): ");
                confirm = scanner.nextLine().trim().toLowerCase();

                if (confirm.equals("y")) {
                    break;  // 삭제 진행
                } else if (confirm.equals("n")) {
                    System.out.println("삭제가 취소되었습니다.");
                    return;
                } else {
                    System.out.println("❌ 잘못된 입력입니다. y 또는 n을 입력해주세요.");
                }
            }

            employeeService.deleteEmployee(empNumber);
            System.out.println("✅ 직원이 삭제(비활성화)되었습니다.");

        } catch (Exception e) {
            System.out.println("❌ 직원 삭제 중 오류: " + e.getMessage());
        }
    }

    public void searchEmployee() {
        while (true) {
            employeeView.searchEmployeeMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                List<Employee> results = new ArrayList<>();

                switch (choice) {
                    case 0 -> {
                        System.out.println("🔙 이전 메뉴로 돌아갑니다.");
                        return;
                    }
                    case 1 -> {
                        System.out.print("이름을 입력하세요: ");
                        String name = scanner.nextLine();
                        results = employeeService.findByName(name);
                    }
                    case 2 -> {
                        System.out.print("사번을 입력하세요: ");
                        String empNumber = scanner.nextLine();
                        Employee emp = employeeService.findByEmpNumber(empNumber);
                        if (emp != null) results.add(emp);
                    }
                    case 3 -> {
                        System.out.print("부서 ID를 입력하세요: ");
                        int deptId = Integer.parseInt(scanner.nextLine());
                        results = employeeService.findByDepartmentId(deptId);
                    }
                    default -> {
                        System.out.println("잘못된 선택입니다. 0~3 사이의 번호를 입력해주세요.");
                        continue;
                    }
                }

                if (results.isEmpty()) {
                    System.out.println("🔍 검색 결과가 없습니다.");
                } else {
                    System.out.println("===== 검색 결과 =====");
                    for (Employee e : results) {
                        System.out.printf("사번: %s | 이름: %s | 부서ID: %d | 전화번호: %s | 입사일: %s%n",
                                e.getEmpNumber(), e.getName(), e.getDepartmentId(), e.getPhone(),
                                new SimpleDateFormat("yyyy-MM-dd").format(e.getHireDate()));
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ 숫자를 입력해주세요.");
            } catch (Exception e) {
                System.out.println("❌ 직원 검색 중 오류 발생: " + e.getMessage());
            }
        }
    }



}
