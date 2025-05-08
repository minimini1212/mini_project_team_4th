package humanResource.employee.controller;

import common.SessionContext;
import humanResource.common.util.EmployeeOptionMapper;
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
            employeeView.employeeMenu();
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
        try {
//            System.out.println("\n전체메뉴 > 인사관리 > 직원관리 > 직원수정");
            System.out.println("===== 직원 수정 =====");
            System.out.print("수정할 직원의 사번을 입력해주세요: ");
            String empNumber = scanner.nextLine().trim();

            Employee existing = employeeService.findByEmpNumber(empNumber);

            if (existing == null) {
                System.out.println("❌ 해당 사번의 직원이 존재하지 않습니다.");
                return;
            }

            System.out.println(existing);

//            System.out.println("\n📋 현재 직원 정보:");
//            System.out.printf("이름: %s | 주소: %s | 전화번호: %s | 이메일: %s%n",
//                    existing.getName(), existing.getAddress(), existing.getPhone(), existing.getEmail());
//            System.out.printf("재직 상태: %s | 직급: %s | 직무: %s%n",
//                    existing.getStatus(),
//                    EmployeeOptionMapper.getPositionName(existing.getPositionId()),
//                    EmployeeOptionMapper.getJobName(existing.getJobId()));

            String confirm;
            while (true) {
                System.out.print("\n이 직원을 수정하시겠습니까? (y/n): ");
                confirm = scanner.nextLine().trim().toLowerCase();

                if (confirm.equals("y")) break;         // 수정 진행
                if (confirm.equals("n")) {
                    System.out.println("🔙 직원 정보 수정을 취소했습니다.");
                    return;
                }

                System.out.println("❌ 잘못된 입력입니다. y 또는 n을 입력해주세요.");
            }

            System.out.print("이름 [" + existing.getName() + "]: ");
            String name = scanner.nextLine().trim();

            System.out.print("주소 [" + existing.getAddress() + "]: ");
            String address = scanner.nextLine().trim();

            String phone = "";
            while (true) {
                System.out.print("전화번호 [" + existing.getPhone() + "] (010-1234-5678 형식): ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) break;

                if (EmployeeService.isValidPhoneNumber(input)) {
                    phone = input;
                    break;
                }
                System.out.println("❌ 잘못된 전화번호 형식입니다. 예: 010-1234-5678");
            }

            String email = "";
            while (true) {
                System.out.print("이메일 [" + existing.getEmail() + "]: ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) break;

                if (EmployeeService.isValidEmail(input)) {
                    email = input;
                    break;
                }
                System.out.println("❌ 이메일 형식이 잘못되었습니다.");
            }

            // 재직 상태
            String status = existing.getStatus();
            while (true) {
                System.out.println("재직 상태 선택 (현재: " + existing.getStatus() + ")");
                EmployeeOptionMapper.printStatusOptions();
                System.out.print("선택 (빈칸 입력 시 유지): ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) break;
                if (input.matches("[1-3]")) {
                    status = EmployeeOptionMapper.STATUS_MAP.get(Integer.parseInt(input));
                    break;
                }
                System.out.println("❌ 1~3 중에서 선택해주세요.");
            }

            // 포지션
            String positionStr = "";
            while (true) {
                System.out.println("직급 선택 (현재: " + EmployeeOptionMapper.getPositionName(existing.getPositionId()) + ")");
                EmployeeOptionMapper.printPositionOptions();
                System.out.print("선택 (빈칸 입력 시 유지): ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) break;
                if (input.matches("[1-7]")) {
                    positionStr = input;
                    break;
                }
                System.out.println("❌ 1~7 사이의 숫자를 입력해주세요.");
            }

            // 직무
            String jobStr = "";
            while (true) {
                System.out.println("직무 선택 (현재: " + EmployeeOptionMapper.getJobName(existing.getJobId()) + ")");
                EmployeeOptionMapper.printJobOptions();
                System.out.print("선택 (빈칸 입력 시 유지): ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) break;
                if (input.matches("[1-8]")) {
                    jobStr = input;
                    break;
                }
                System.out.println("❌ 1~8 사이의 숫자를 입력해주세요.");
            }

            while (true) {
                System.out.print("\n저장하시겠습니까? (y/n): ");
                String saveConfirm = scanner.nextLine().trim().toLowerCase();

                if (saveConfirm.equals("y")) break;
                if (saveConfirm.equals("n")) {
                    System.out.println("🔙 변경 사항을 저장하지 않고 이전 메뉴로 돌아갑니다.");
                    return;
                }

                System.out.println("❌ 잘못된 입력입니다. y 또는 n을 입력해주세요.");
            }

            employeeService.updateEmployeeInfo(empNumber, name, address, phone, email, status, positionStr, jobStr);
            System.out.println("✅ 직원 정보가 성공적으로 수정되었습니다.");

        } catch (Exception e) {
            System.out.println("❌ 직원 정보 수정 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteEmployee() {
//        System.out.println("\n전체메뉴 > 인사관리 > 직원관리 > 직원삭제");
        System.out.println("===== 직원 삭제 =====");
        try {
            System.out.print("삭제할 직원의 사번을 입력하세요: ");
            String empNumber = scanner.nextLine();

            Employee e = employeeService.findByEmpNumber(empNumber);
            System.out.println(e);
            System.out.println();

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
                        System.out.print("이름: ");
                        String name = scanner.nextLine();
                        results = employeeService.findByName(name);
                    }
                    case 2 -> {
                        System.out.print("사번: ");
                        String empNumber = scanner.nextLine();
                        Employee emp = employeeService.findByEmpNumber(empNumber);
                        if (emp != null) results.add(emp);
                    }
                    case 3 -> {
                        System.out.print("부서\n ① 인사관리 \n ② 예산·회계관리 \n ③ 자산관리 \n입력: ");
                        int deptId = Integer.parseInt(scanner.nextLine());
                        switch (deptId){
                            case 1: {
                                results = employeeService.findByDepartmentId(2);
                                break;
                            }
                            case 2: {
                                results = employeeService.findByDepartmentId(3);
                                break;
                            }
                            case 3: {
                                results = employeeService.findByDepartmentId(4);
                                break;
                            }
                        }
                        results = employeeService.findByDepartmentId(deptId);
                    }
                    default -> {
                        System.out.println("잘못된 선택입니다. 1~3 사이의 번호를 입력해주세요.");
                        continue;
                    }
                }

                if (results.isEmpty()) {
                    System.out.println("━━━━━ 🔍 검색 결과가 없습니다 ━━━━━━");
                } else {
                    System.out.println("━━━━━━━━━  검색 결과 ━━━━━━━━━");
                    for (Employee e : results) {
                        // 부서 ID → 부서명 매핑
//                        String deptName = switch (e.getDepartmentId()) {
//                            case 2 -> "인사관리부서";
//                            case 3 -> "예산/회계관리부서";
//                            case 4 -> "자산관리부서";
//                            default -> "알 수 없음";
//                        };

                        // 직급, 직무, 상태는 Map 기반으로 변환
                        String positionName = EmployeeOptionMapper.getPositionName(e.getPositionId());
                        String jobName = EmployeeOptionMapper.getJobName(e.getJobId());
                        String statusName = EmployeeOptionMapper.getStatusName(e.getStatus());
                        String departmentName = EmployeeOptionMapper.getDepartmentName(e.getDepartmentId());


                        e.setDepartmentName(departmentName);
                        e.setPositionName(positionName);
                        e.setJobName(jobName);
                        e.setStatus(statusName);

                        System.out.println(e);

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
