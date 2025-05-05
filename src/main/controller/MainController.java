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
                    int choice;

                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("❌ 숫자를 입력해주세요.");
                        continue;
                    }

                    if (choice == 0) {
                        if (confirmLogout()) {
                            SessionContext.clear();
                            System.out.println("✅ 로그아웃되었습니다. 로그인 메뉴로 돌아갑니다.\n");
                            // 로그인 메뉴로 돌아감
                            MainEntry.main(null);
                            return;
                        } else {
                            continue;
                        }
                    }

                    if (choice < 0 || choice > 3) {
                        System.out.println("❌ 유효한 메뉴 번호(0~3)를 입력해주세요.");
                        continue;
                    }

                    handleDepartmentMenu(choice, rankOrder);
                }

            } else {
                // 일반 사용자는 자신의 부서 메뉴로 바로 진입
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
                // TODO: HRMenu 클래스를 생성하여 위임
                System.out.println("🔹 인사 관리 부서 진입");
                new HumanResourceController().humanResourceMenu(scanner);
            }
            case 2 -> {
                // TODO: FinanceMenu 클래스를 생성하여 위임
                System.out.println("🔹 예산/회계 관리 부서 진입");
            }
            case 3 -> {
                // TODO: AssetMenu 클래스를 생성하여 위임
                System.out.println("🔹 자산 관리 부서 진입");
                equipmentAssetController.equipmentAssetMenu(scanner, rankOrder);
            }
        }
    }

    private void handleUserDepartmentMenu(int deptId, int rankOrder) {
        switch (deptId) {
            case 1 -> {
                // TODO: HRMenu 클래스를 생성하여 위임
                System.out.println("🔸 인사 관리 부서 진입");
                new HumanResourceController().humanResourceMenu(scanner);
            }
            case 2 -> {
                // TODO: FinanceMenu 클래스를 생성하여 위임
                System.out.println("🔸 예산/회계 관리 부서 진입");
            }
            case 3 -> {
                // TODO: AssetMenu 클래스를 생성하여 위임
                System.out.println("🔸 자산 관리 부서 진입");
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
}
