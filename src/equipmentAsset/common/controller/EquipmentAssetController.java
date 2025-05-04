package equipmentAsset.common.controller;

import java.util.Scanner;

import equipmentAsset.common.view.EquipmentAssetView;
import equipmentAsset.equipment.controller.EquipmentController;
import equipmentAsset.inspection.controller.InspectionController;


public class EquipmentAssetController {
    
    private EquipmentAssetView equipmentAssetView = new EquipmentAssetView();
    private EquipmentController equipmentController = new EquipmentController();
    private InspectionController inspectionController = new InspectionController();
    
    /** =-=-=-=-=-=-=-=-=-=-=-= 최상위 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/
    
    // - 장비/자산 관리 메인 메뉴
    public void equipmentAssetMenu(Scanner sc) {
        while (true) {
            equipmentAssetView.equipmentAssetMenu();
            switch (sc.nextLine()) {
            case "0": // - 이전 메뉴로 돌아가기
                return;
            case "1": // - 장비 정보 관리 메뉴로 이동
                equipmentController.equipmentMenu();
                break;
            case "2": // - 정기 점검 관리 메뉴로 이동
                inspectionController.inspectionMenu();
                break;
            case "3": // - 장비 수리/정비 관리 메뉴로 이동 (미구현)
              
            case "4": // - 자산 이력 관리 메뉴로 이동 (미구현

                break;
            default:
                System.out.println("잘못된 입력입니다");
                break;
            }
        }
    }
}