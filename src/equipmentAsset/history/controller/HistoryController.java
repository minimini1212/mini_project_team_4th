package equipmentAsset.history.controller;

import java.util.Scanner;

import equipmentAsset.equipment.controller.EquipmentController;
import equipmentAsset.history.model.service.HistoryService;
import equipmentAsset.history.view.HistoryView;

public class HistoryController {
    HistoryView historyView = new HistoryView();
    HistoryService historyService = new HistoryService();
    EquipmentController equipmentController = new EquipmentController();
    Scanner sc = new Scanner(System.in);

    /** =-=-=-=-=-=-=-=-=-=-=-= 최상위 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

    // - 이력 관리 메인 메뉴
    public void historyAdminMenu() {
        while (true) {
            historyView.historyAdminMenu();
            switch (sc.nextLine()) {
                case "0": // - 이전 메뉴로 돌아가기
                    return;
                case "1": // - 이력 통합 조회 메뉴로 이동
                    integratedHistoryMenu();
                    break;
                case "2": // - 점검 이력 조회 메뉴로 이동
                    inspectionHistoryMenu();
                    break;
                case "3": // - 수리 이력 조회 메뉴로 이동
                    repairHistoryMenu();
                    break;
                case "4": // - 폐기 이력 조회 메뉴로 이동
                    disposalHistoryMenu();
                    break;
                default:
                    System.out.println("잘못된 입력입니다");
                    break;
            }
        }
    }

    // - 이력 통합 조회 메뉴
    public void integratedHistoryMenu() {
        while (true) {
            historyView.integratedHistoryMenu();
            switch (sc.nextLine()) {
                case "0": // - 이전 메뉴로 돌아가기
                    return;
                case "1": // - 모든 이력 조회
                    if (!historyService.findAllHistory()) {
                        continue;
                    }
                    break;
                case "2": // - 장비 번호로 조회
                    int equipmentId = equipmentController.getEquipmentId();
                    if (equipmentId == -1 || !historyService.findHistoryByEquipmentId(equipmentId)) {
                        continue;
                    }
                    break;
                default:
                    System.out.println("잘못된 입력입니다");
                    continue;
            }
        }
    }

    // - 점검 이력 조회 메뉴
    public void inspectionHistoryMenu() {
        while (true) {
            historyView.inspectionHistoryMenu();
            switch (sc.nextLine()) {
                case "0": // - 이전 메뉴로 돌아가기
                    return;
                case "1": // - 모든 점검 이력 조회
                    if (!historyService.findInspectionHistory()) {
                        continue;
                    }
                    break;
                case "2": // - 장비 번호로 조회
                    int equipmentId = equipmentController.getEquipmentId();
                    if (equipmentId == -1 || !historyService.findInspectionHistoryByEquipmentId(equipmentId)) {
                        continue;
                    }
                    break;
                case "3": // - 점검 결과별 조회
                    if (!findInspectionHistoryByResult()) {
                        continue;
                    }
                    break;
                default:
                    System.out.println("잘못된 입력입니다");
                    continue;
            }
        }
    }

    // - 수리 이력 조회 메뉴
    public void repairHistoryMenu() {
        while (true) {
            historyView.repairHistoryMenu();
            switch (sc.nextLine()) {
                case "0": // - 이전 메뉴로 돌아가기
                    return;
                case "1": // - 모든 수리 이력 조회
                    if (!historyService.findRepairHistory()) {
                        continue;
                    }
                    break;
                case "2": // - 장비 번호로 조회
                    int equipmentId = equipmentController.getEquipmentId();
                    if (equipmentId == -1 || !historyService.findRepairHistoryByEquipmentId(equipmentId)) {
                        continue;
                    }
                    break;
                case "3": // - 수리 결과별 조회
                    if (!findRepairHistoryByResult()) {
                        continue;
                    }
                    break;
                default:
                    System.out.println("잘못된 입력입니다");
                    continue;
            }
        }
    }

    // - 폐기 이력 조회 메뉴
    public void disposalHistoryMenu() {
        while (true) {
            historyView.disposalHistoryMenu();
            switch (sc.nextLine()) {
                case "0": // - 이전 메뉴로 돌아가기
                    return;
                case "1": // - 모든 폐기 이력 조회
                    if (!historyService.findDisposalHistory()) {
                        continue;
                    }
                    break;
                case "2": // - 장비 번호로 조회
                    int equipmentId = equipmentController.getEquipmentId();
                    if (equipmentId == -1 || !historyService.findDisposalHistoryByEquipmentId(equipmentId)) {
                        continue;
                    }
                    break;
                default:
                    System.out.println("잘못된 입력입니다");
                    continue;
            }
        }
    }

    /** =-=-=-=-=-=-=-=-=-=-=-=-= 하위 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/

    // - 점검 결과별 이력 조회 메소드
    public boolean findInspectionHistoryByResult() {
        String result;

        historyView.getInspectionResultTypeMenu();

        switch (sc.nextLine()) {
            case "1":
                result = "양호";
                return historyService.findInspectionHistoryByResult(result);
            case "2":
                result = "조치필요";
                return historyService.findInspectionHistoryByResult(result);
            default:
                System.out.println("잘못된 입력입니다");
                return false;
        }
    }

    // - 수리 결과별 이력 조회 메소드
    public boolean findRepairHistoryByResult() {
        String result;

        historyView.getRepairResultTypeMenu();

        switch (sc.nextLine()) {
            case "1":
                result = "수리완료";
                return historyService.findRepairHistoryByResult(result);
            case "2":
                result = "수리불가";
                return historyService.findRepairHistoryByResult(result);
            default:
                System.out.println("잘못된 입력입니다");
                return false;
        }
    }
}