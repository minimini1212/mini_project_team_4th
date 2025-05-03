package equipmentAsset.inspection.controller;

import java.util.Scanner;

import equipmentAsset.equipment.controller.EquipmentController;
import equipmentAsset.inspection.model.service.InspectionService;
import equipmentAsset.inspection.view.InspectionView;

public class InspectionController {
	InspectionView inspectionView = new InspectionView();
	InspectionService inspectionService = new InspectionService();
	EquipmentController equipmentController = new EquipmentController();
	Scanner sc = new Scanner(System.in);

	/** =-=-=-=-=-=-=-=-=-=-=-= 상위 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 일정 조회
	public void findInspectionScheduleMenu() {
		while (true) {
			inspectionView.findInspectionScheduleMenu();
			switch (sc.nextLine()) {
			case "0":
				return;
			case "1":
				inspectionService.findAllInspection();
				break;
			case "2":
				int equipmentId = equipmentController.getEquipmentId();
				inspectionService.findByIdInspection(equipmentId);
				break;
			case "3":
				int scheduleId = inputScheduleId();
				inspectionService.findByScheduleId(scheduleId);
				break;
			default:
				System.out.println("잘못된 입력입니다");
				break;
			}
		}
	}
	
	// - 일정 등록
	public void saveInspectionScheduleMenu() {
		while (true) {
			inspectionView.saveInspectionScheduleMenu();
			switch (sc.nextLine()) {
			case "0":
				return;
//			case "1":
//				if (!createEquipment())
//					return;
//				break;
			default:
				System.out.println("잘못된 입력입니다");
				break;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	/** =-=-=-=-=-=-=-=-=-=-=-= 재사용할거 모음 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 일정아이디 선택받고 반환
	public int inputScheduleId() {
		int scheduleId;
		while (true) {
			try {
				if (!inspectionService.findAllInspection()) {
					return -1;
				}
				System.out.println();
				System.out.println("---------------------");
				System.out.print("일정 번호 선택 : ");
				scheduleId = Integer.parseInt(sc.nextLine());

			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
			return scheduleId;
		}
	}

} // end class
