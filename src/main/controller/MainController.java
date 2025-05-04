package main.controller;

import common.SessionContext;
import humanResource.employee.model.entity.Employee;

import java.util.Scanner;

public class MainController {
    private final Scanner scanner;

    public MainController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        try {
            // 로그인 성공 시 세션 정보 확인
            if (!SessionContext.isLoggedIn()) {
                System.out.println("로그인 정보가 없습니다.");
                return;
            }

            Employee emp = SessionContext.getUser();
            int rankOrder = SessionContext.getRankOrder();
            int deptId = SessionContext.getDeptId();

            // 관리자로서 부서 선택
            if (rankOrder == 1) {
                while (true) {
                    showDepartmentMenu();
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice == 0) break;
                    handleDepartmentMenu(choice);
                }
            } else {
                handleUserDepartmentMenu(deptId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDepartmentMenu() {
        System.out.println("\n===== 부서 선택 =====");
        System.out.println("1. 인사 관리 부서");
        System.out.println("2. 예산/회계 관리 부서");
        System.out.println("3. 자산 관리 부서");
        System.out.print("선택: ");
    }

    private void handleDepartmentMenu(int choice) {
        switch (choice) {
            case 1 -> {
                // TODO: HRMenu 클래스를 생성하여 위임
                System.out.println("인사 관리 부서");
            }
            case 2 -> {
                // TODO: FinanceMenu 클래스를 생성하여 위임
                System.out.println("예산/회계 관리 부서");
            }
            case 3 -> {
                // TODO: 자산 관리 부서
                System.out.println("자산 관리 부서");
            }
            case 0 -> System.out.println("뒤로 가기를 선택했습니다.");
            default -> System.out.println("⚠ 알 수 없는 부서입니다.");
        }
    }

    private void handleUserDepartmentMenu(int deptId) {
        switch (deptId) {
            case 1 -> {
                // TODO: HRMenu 클래스를 생성하여 위임
                System.out.println("인사 관리 부서");
            }
            case 2 -> {
                // TODO: FinanceMenu 클래스를 생성하여 위임
                System.out.println("예산/회계 관리 부서");
            }
            case 3 -> {
                // TODO: 자산 관리 부서
                System.out.println("자산 관리 부서");
            }
            default -> System.out.println("⚠ 알 수 없는 부서입니다.");
        }
    }
}
