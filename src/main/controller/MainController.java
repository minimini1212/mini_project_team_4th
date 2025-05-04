package main.controller;

import common.SessionContext;
import humanResource.employee.model.entity.Employee;
import main.view.MainView;

import java.util.Scanner;

public class MainController {
    private final Scanner scanner;
    private final MainView mainView = new MainView();

    public MainController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        try {
            if (!SessionContext.isLoggedIn()) {
                System.out.println("로그인 정보가 없습니다.");
                return;
            }

            Employee emp = SessionContext.getUser();
            int rankOrder = SessionContext.getRankOrder();
            int deptId = SessionContext.getDeptId();

            if (rankOrder == 1) {
                // 관리자용 부서 선택 메뉴 반복
                while (true) {
                    mainView.showDepartmentMenu();
                    String input = scanner.nextLine();

                    int choice;
                    try {
                        choice = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ 숫자를 입력해주세요.");
                        continue;
                    }

                    if (choice == 0) {
                        if (confirmLogout()) {
                            SessionContext.clear();
                            System.out.println("✅ 로그아웃되었습니다. 로그인 메뉴로 돌아갑니다.");
                            return;
                        } else {
                            continue; // 로그아웃 취소 → 메뉴 반복
                        }
                    }

                    if (choice >= 1 && choice <= 3) {
                        handleDepartmentMenu(choice);
                    } else {
                        System.out.println("⚠ 잘못된 선택입니다. 0~3 사이의 번호를 입력해주세요.");
                    }
                }
            } else {
                // 일반 사용자 → 자신의 부서로 자동 진입
                handleUserDepartmentMenu(deptId);
            }

        } catch (Exception e) {
            System.out.println("❌ 시스템 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleDepartmentMenu(int choice) {
        switch (choice) {
            case 1 -> {
                // TODO: HRMenu 클래스를 생성하여 위임
                System.out.println("🔹 인사 관리 부서 진입");
            }
            case 2 -> {
                // TODO: FinanceMenu 클래스를 생성하여 위임
                System.out.println("🔹 예산/회계 관리 부서 진입");
            }
            case 3 -> {
                // TODO: AssetMenu 클래스를 생성하여 위임
                System.out.println("🔹 자산 관리 부서 진입");
            }
        }
    }

    private void handleUserDepartmentMenu(int deptId) {
        switch (deptId) {
            case 1 -> {
                // TODO: HRMenu 클래스를 생성하여 위임
                System.out.println("🔸 인사 관리 부서 진입");
            }
            case 2 -> {
                // TODO: FinanceMenu 클래스를 생성하여 위임
                System.out.println("🔸 예산/회계 관리 부서 진입");
            }
            case 3 -> {
                // TODO: AssetMenu 클래스를 생성하여 위임
                System.out.println("🔸 자산 관리 부서 진입");
            }
            default -> System.out.println("⚠ 알 수 없는 부서입니다.");
        }
    }

    private boolean confirmLogout() {
        while (true) {
            System.out.print("로그아웃 하시겠습니까? (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("y")) return true;
            if (input.equals("n")) return false;

            System.out.println("❌ 잘못된 입력입니다. y 또는 n을 입력해주세요.");
        }
    }
}
