package equipmentAsset.equipment.controller;

import java.util.Scanner;

import equipmentAsset.equipment.model.entity.Equipment;
import equipmentAsset.equipment.model.entity.EquipmentCategory;
import equipmentAsset.equipment.model.service.EquipmentService;
import equipmentAsset.equipment.view.EquipmentView;

public class EquipmentController {

	EquipmentView equipmentView = new EquipmentView();
	EquipmentService equipmentService = new EquipmentService();
	Scanner sc = new Scanner(System.in);

	/** =-=-=-=-=-=-=-=-=-=-=-= 상위 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 장비 조회
	public void findEquipmentMenu() {
		while (true) {
			equipmentView.findEquipmentMenu();
			switch (sc.nextLine()) {
			case "0":
				return;
			case "1":
				equipmentService.findAllEquipment();
				break;
			case "2":
				int equipmentId = getEquipmentId();
				equipmentService.findByIdEquipment(equipmentId);
				break;
			case "3":
				String status = inputStatus();
				equipmentService.findByStatusEquipment(status);
				break;
			case "4":
				String department = inputDepartment();
				equipmentService.findByDepartmentEquipment(department);
				break;
			case "5":
				int categoryId = inputCategory();
				String categoryName = equipmentService.getCategoryNameById(categoryId);
				if(categoryName == null) return;
				equipmentService.findByCategoryEquipment(categoryName);
				break;
			default:
				System.out.println("잘못된 입력입니다");
				break;
			}
		}
	}

	// - 장비 등록
	public void saveEquipmentMenu() {
		while (true) {
			equipmentView.saveEquipmentMenu();
			switch (sc.nextLine()) {
			case "0":
				return;
			case "1":
				if (!createEquipment())
					return;
				break;
			default:
				System.out.println("잘못된 입력입니다");
				break;
			}
		}
	}

	// - 장비 수정
	public void updateEquipmentMenu() {
		while (true) {
			equipmentView.updateEquipmentMenu();
			switch (sc.nextLine()) {
			case "0":
				return;
			case "1":
				if (!updateEquipment())
					return;
				break;
			default:
				System.out.println("잘못된 입력입니다");
				break;
			}
		}
	}

	// - 장비 삭제
	public void deleteEquipmentMenu() {
		while (true) {
			equipmentView.updateEquipmentMenu();
			switch (sc.nextLine()) {
			case "0":
				return;
			case "1":
				if (!deleteEquipment())
					return;
				break;
			default:
				System.out.println("잘못된 입력입니다");
				break;
			}
		}
	}

	// - 장비 현황 조회
	public void showEquipmentDashboardMenu() {
		while (true) {
			equipmentView.showEquipmentDashboardMenu();
			switch (sc.nextLine()) {
			case "0":
				return;
			case "1":
				if (!equipmentService.countByStatus())
					return;
				break;
			case "2":
				if (!equipmentService.countByDepartment())
					return;
				break;
			case "3":
				if (!equipmentService.countByCategory())
					return;
				break;
			case "4":
				if (!equipmentService.getRecentlyUpdatedEquipments())
					return;
				break;
			default:
				System.out.println("잘못된 입력입니다");
				break;
			}
		}
	}
	
	// - 카테고리 관리
	public void manageCategoryMenu() {
		while (true) {
			equipmentView.manageCategoryMenu();
			switch (sc.nextLine()) {
			case "0":
				return;
			case "1":
				equipmentService.findAllCategories();
				break;
			case "2":
				createCategory();
				break;
			case "3":
				int categoryId = inputCategory();
				equipmentService.deleteCategory(categoryId);
				break;
			default:
				System.out.println("잘못된 입력입니다");
				break;
			}
		}	
	}

	/** =-=-=-=-=-=-=-=-=-=-=-= 하위 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 장비 생성
	public boolean createEquipment() {
		Equipment equipment = new Equipment();

		System.out.println("---- 장비 정보 등록 ----");
		System.out.print("장비 이름 입력 : ");
		equipment.setEquipmentName(sc.nextLine());
		System.out.print("장비 모델 입력 : ");
		equipment.setModelName(sc.nextLine());
		System.out.print("장비 제조사 입력 : ");
		equipment.setManufacturer(sc.nextLine());
		System.out.print("시리얼번호 입력 : ");
		equipment.setSerialNumber(sc.nextLine());

		if (!equipmentService.saveEquipment(equipment)) {
			return false;
		}

		System.out.println();
		System.out.println("장비가 등록되었습니다");

		boolean isCompleted = true;
		while (isCompleted) {
			System.out.println("---- 추가 정보 입력 ----");
			equipmentView.createEquipmentMenu();
			switch (sc.nextLine()) {
			case "1":
				if (!inputPurchaseInfo(equipment))
					return false;
				System.out.println("입력이 완료되었습니다");
				break;
			case "2":
				if (!selectEquipmentCategory(equipment))
					return false;
				System.out.println("입력이 완료되었습니다");
				break;
			case "3":
				if (!selectEquipmentManager(equipment))
					return false;
				System.out.println("입력이 완료되었습니다");
				break;
			case "4":
				if (!inputStatusAndDescription(equipment))
					return false;
				System.out.println("입력이 완료되었습니다");
				break;
			case "5":
				System.out.println("입력을 취소합니다");
				isCompleted = false;
				break;
			default:
				System.out.println("잘못된 입력입니다");
				System.out.println();
				break;
			}
		} // end while

		return true;
	} // end createEquipment
	
	public boolean createCategory() {
		EquipmentCategory category = new EquipmentCategory();
		
		System.out.println("--- 카테고리 정보 등록 ---");
		System.out.print("카테고리 이름 입력 : ");
		category.setCategoryName(sc.nextLine());
		System.out.print("카테고리 코드 입력 : ");
		category.setCategoryCode(sc.nextLine());
	
		if (!equipmentService.saveCategory(category)) {
			return false;
		}

		System.out.println();
		System.out.println("카테고리가 추가되었습니다");

		return true;
	} // end createEquipment
	
	
	// - 구매 정보 입력
	public boolean inputPurchaseInfo(Equipment equipment) {
		// 날짜 정규표현식 (YYYY-MM-DD)
		String dateRegex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";

		if (!equipmentService.findByIdEquipment(equipment.getEquipmentId())) {
			return false;
		}
		System.out.println();
		System.out.println("---- 구매 정보 입력 ----");

		while (true) {
			System.out.print("구매 날짜 입력 (YYYY-MM-DD) : ");
			String dateStr = sc.nextLine();

			if (dateStr.matches(dateRegex)) {
				if (!equipmentService.updatePurchaseDate(equipment.getEquipmentId(), dateStr)) {
					return false;
				}
			} else {
				System.out.println("날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력하세요.");
				continue;
			}
			break;
		}
		while (true) {
			System.out.print("구매 가격 입력 : ");
			String priceStr = sc.nextLine();

			if (priceStr.isEmpty()) {
				System.out.println("금액을 입력해주세요");
				;
				continue;
			}

			priceStr = priceStr.replaceAll("[^0-9]", "");

			if (!equipmentService.updatePurchasePrice(equipment.getEquipmentId(), Integer.parseInt(priceStr))) {
				return false;
			}
			break;
		}

		return true;
	} // end inputPurchaseInfo

	// - 카테고리 선택
	public boolean selectEquipmentCategory(Equipment equipment) {
		int categoryId = inputCategory();

		if (!equipmentService.updateEquipmentCategory(equipment.getEquipmentId(), categoryId)) {
			return false;
		}
		return true;
	}
	
	// - 담당자 선택
	public boolean selectEquipmentManager(Equipment equipment) {

		while (true) {
			try {
				if (!equipmentService.findAllManager()) {
					return false;
				}
				System.out.println();
				System.out.println("---------------------");
				System.out.print("매니저 번호 선택 : ");

				int managerId = Integer.parseInt(sc.nextLine());

				if (!equipmentService.updateEquipmentManager(equipment.getEquipmentId(), managerId)) {
					return false;
				}
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
				continue;
			}
			break;
		}
		return true;
	}

	// - 상태 및 추가 정보 입력
	public boolean inputStatusAndDescription(Equipment equipment) {
		String description, status = inputStatus();

		System.out.println();

		if (!equipmentService.updateEquipmentStatus(equipment.getEquipmentId(), status)) {
			return false;
		}

		System.out.print("장비 추가 설명 입력 : ");
		description = sc.nextLine();

		if (!equipmentService.updateEquipmentDescription(equipment.getEquipmentId(), description)) {
			return false;
		}

		return true;
	} // end inputStatusAndDescription

	// - 장비 수정
	public boolean updateEquipment() {
		boolean isCompleted = true;
		Equipment equipment = new Equipment();

		int equipmentId = getEquipmentId();
		equipment.setEquipmentId(equipmentId);

		while (isCompleted) {
			System.out.println("---- 장비 정보 수정 ----");
			equipmentView.createEquipmentMenu();
			switch (sc.nextLine()) {
			case "1":
				System.out.println();
				if (!equipmentService.findByIdEquipment(equipmentId))
					return false;
				if (!inputPurchaseInfo(equipment))
					return false;
				System.out.println("입력이 완료되었습니다");
				break;
			case "2":
				System.out.println();
				if (!equipmentService.findByIdEquipment(equipmentId))
					return false;
				if (!selectEquipmentCategory(equipment))
					return false;
				System.out.println("입력이 완료되었습니다");
				break;
			case "3":
				System.out.println();
				if (!equipmentService.findByIdEquipment(equipmentId))
					return false;
				if (!selectEquipmentManager(equipment))
					return false;
				System.out.println("입력이 완료되었습니다");
				break;
			case "4":
				System.out.println();
				if (!equipmentService.findByIdEquipment(equipmentId))
					return false;
				if (!inputStatusAndDescription(equipment))
					return false;
				System.out.println("입력이 완료되었습니다");
				break;
			case "5":
				System.out.println("입력을 취소합니다");
				isCompleted = false;
				break;
			default:
				System.out.println("잘못된 입력입니다");
				System.out.println();
				break;
			}
		} // end while
		return true;
	} // end updateEquipment

	// - 장비 삭제
	public boolean deleteEquipment() {
		int equipmentId = getEquipmentId();

		System.out.println("정말 삭제하시겠습니까? 취소하시면 이전 메뉴로 돌아갑니다");
		System.out.print("Y / N : ");

		while (true) {
			String input = sc.nextLine();
			if (input.equalsIgnoreCase("n"))
				return false;
			if (input.equalsIgnoreCase("y"))
				break;
			System.out.println("Y / N 을 입력해주세요");
		}

		if (!equipmentService.deleteEquipment(equipmentId))
			return false;

		System.out.println("삭제가 완료되었습니다");
		return true;
	} // end deleteEquipment

	/** =-=-=-=-=-=-=-=-=-=-=-= 재사용할거 모음 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 장비 번호 얻어주는 메소드
	public int getEquipmentId() {
		int equipmentId;
		while (true) {
			try {
				if (!equipmentService.findAllEquipment())
					return -1;

				System.out.println("");
				System.out.print("장비 번호를 입력하세요 : ");
				equipmentId = Integer.parseInt(sc.nextLine());

				if (!equipmentService.findByIdEquipment(equipmentId)) {
					System.out.println("정확한 번호를 입력하세요");
					continue;
				}
				break;

			} catch (Exception e) {
				System.out.println("번호만 입력해주세요");
			}
		}
		return equipmentId;
	}

	// - 상태 정보 입력받아 반환해주는 메소드
	public String inputStatus() {
		String status;
		while (true) {
			System.out.println("---- 입력 가능 상태 ----");
			equipmentView.inputStatusAndDescriptionMenu();
			switch (sc.nextLine()) {
			case "1":
				status = "정상";
				break;
			case "2":
				status = "점검필요";
				break;
			case "3":
				status = "수리중";
				break;
			case "4":
				status = "폐기예정";
				break;
			case "5":
				status = "폐기완료";
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
			break;
		}
		return status;
	}

	// - 부서 이름 입력받아 반환해주는 메소드
	public String inputDepartment() {
		String department;
		while (true) {
			equipmentView.inputDepartment();
			switch (sc.nextLine()) {
			case "1":
				department = "인사팀";
				break;
			case "2":
				department = "재무팀";
				break;
			case "3":
				department = "재고팀";
				break;
			case "4":
				department = "장비팀";
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
			break;
		}
		return department;
	}
	
	// - 카테고리 입력받고 반환
		public int inputCategory() {
			int categoryId;
			while (true) {
				try {
					if (!equipmentService.findAllCategories()) {
						return -1;
					}
					System.out.println();
					System.out.println("---------------------");
					System.out.print("카테고리 번호 선택 : ");
					categoryId = Integer.parseInt(sc.nextLine());

				} catch (Exception e) {
					System.out.println("숫자만 입력해주세요.");
					continue;
				}
				return categoryId;
			}
		}

} // end class
