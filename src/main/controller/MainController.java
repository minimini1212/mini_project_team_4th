package main.controller;

import common.SessionContext;
import equipmentAsset.common.controller.EquipmentAssetController;
import humanResource.common.controller.HumanResourceController;
import humanResource.employee.model.entity.Employee;
import main.view.MainEntry;
import main.view.MainView;

import java.util.Scanner;

public class MainController {
    private final Scanner scanner;
    private final MainView mainView = new MainView();
    private EquipmentAssetController equipmentAssetController = new EquipmentAssetController();

    public MainController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        try {
            if (!SessionContext.isLoggedIn()) {
                System.out.println("⚠️ 로그인 정보가 없습니다.");
                return;
            }

            Employee emp = SessionContext.getUser();
            int rankOrder = SessionContext.getRankOrder();
            int deptId = SessionContext.getDeptId();

            // 관리자라면 부서 선택 메뉴 보여주기
            if (rankOrder == 1) {
                while (true) {
                    mainView.showDepartmentMenu();
                    int choice = promptIntInRange("선택: ", 0, 4);

                    if (choice == 0) {
                        if (confirmLogout()) {
                            SessionContext.clear();
                            System.out.println("✅ 로그아웃되었습니다. 로그인 메뉴로 돌아갑니다.\n");
                            MainEntry.main(null);
                            return;
                        }
                        continue;
                    }

                    // rankOrder = 1 유저 진입
                    handleDepartmentMenu(choice, rankOrder);
                }

            } else {
                // rankOrder <= 2 유저 진입
                handleUserDepartmentMenu(deptId, rankOrder);
            }

        } catch (Exception e) {
            System.out.println("❌ 시스템 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleDepartmentMenu(int choice, int rankOrder) {
        switch (choice) {
            case 1 -> {
                // TODO: 병원장실
                System.out.println("🔹 병원장실 진입이 아직 연결되지 않음");
            }
            case 2 -> {
                new HumanResourceController().humanResourceMenu(scanner, rankOrder);
            }
            case 3 -> {
                // TODO: FinanceMenu 클래스를 생성하여 위임
                System.out.println("🔹 예산/회계 관리 부서 진입이 아직 연결되지 않음");
            }
            case 4 -> {
                equipmentAssetController.equipmentAssetMenu(scanner, rankOrder);
            }

        }
    }

    private void handleUserDepartmentMenu(int deptId, int rankOrder) {
        switch (deptId) {
            case 1 -> {
                // TODO: 병원장실
                System.out.println("🔹 병원장실 진입이 아직 연결되지 않음");
            }
            case 2 -> {
                new HumanResourceController().humanResourceMenu(scanner, rankOrder);
            }
            case 3 -> {
                // TODO: FinanceMenu 클래스를 생성하여 위임
                System.out.println("🔸 예산/회계 관리 부서 진입이 아직 연결되지 않음");
            }
            case 4 -> {
                equipmentAssetController.equipmentAssetMenu(scanner, rankOrder);
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

    private int promptIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.printf("❌ 유효한 메뉴 번호(%d~%d)를 입력해주세요.%n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ 숫자를 입력해주세요.");
            }
        }
    }
}
