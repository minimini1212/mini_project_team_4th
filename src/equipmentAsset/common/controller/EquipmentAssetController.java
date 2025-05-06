package equipmentAsset.common.controller;

import java.util.Scanner;

import equipmentAsset.common.view.EquipmentAssetView;
import equipmentAsset.equipment.controller.EquipmentController;
import equipmentAsset.history.controller.HistoryController;
import equipmentAsset.inspection.controller.InspectionController;
import equipmentAsset.repair.controller.RepairController;


public class EquipmentAssetController {
    
    private EquipmentAssetView equipmentAssetView = new EquipmentAssetView();
    private EquipmentController equipmentController = new EquipmentController();
    private InspectionController inspectionController = new InspectionController();
    private HistoryController historyController = new HistoryController();
    private RepairController repairController = new RepairController();
    /** =-=-=-=-=-=-=-=-=-=-=-= 최상위 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/
    // - 장비/자산 관리 메인 메뉴
    public void equipmentAssetMenu(Scanner sc, int rankOrder) {
        while (true) {
            equipmentAssetView.equipmentAssetMenu();
            switch (sc.nextLine()) {
            case "0": // - 이전 메뉴로 돌아가기
                return;
            case "1": // - 장비 정보 관리 메뉴로 이동
                if (rankOrder <= 2) {
                    equipmentController.equipmentAdminMenu();
                } else {
                    equipmentController.equipmentUserMenu();
                }
                break;
            case "2": // - 정기 점검 관리 메뉴로 이동
                inspectionController.inspectionMenu();
                break;
            case "3": // - 장비 수리/정비 관리 메뉴로 이동
                repairController.repairMenu();
                break;
            case "4": // - 자산 이력 관리 메뉴로 이동
                historyController.historyAdminMenu();
                break;
            default:
                System.out.println("잘못된 입력입니다");
                break;
            }
        }
    }
}