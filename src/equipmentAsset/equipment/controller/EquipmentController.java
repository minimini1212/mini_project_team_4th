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

    /**
     * =-=-=-=-=-=-=-=-=-=-=-= 최상위 메뉴 =-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 장비 관리 최상위 메뉴
    public void equipmentAdminMenu() {
        while (true) {
            equipmentView.equipmentAdminMenu();
            switch (sc.nextLine()) {
                case "0": // - 이전 메뉴로 돌아가기
                    return;
                case "1": // - 장비 정보 조회 메뉴로 이동
                    findEquipmentMenu();
                    break;
                case "2": // - 장비 등록 메뉴로 이동
                    saveEquipmentMenu();
                    break;
                case "3": // - 장비 정보 수정 메뉴로 이동
                    updateEquipmentMenu();
                    break;
                case "4": // - 장비 정보 삭제 메뉴로 이동
                    deleteEquipmentMenu();
                    break;
                case "5": // - 장비 현황 대시보드 메뉴로 이동
                    showEquipmentDashboardMenu();
                    break;
                case "6": // - 카테고리 관리 메뉴로 이동
                    manageCategoryMenu();
                    break;
                case "7": // - 장비 폐기 메뉴로 이동 (추가)
                    disposeEquipmentMenu();
                    break;
                default:
                    System.out.println("잘못된 입력입니다");
                    break;
            }
        }
    }

    public void equipmentUserMenu() {
        while (true) {
            equipmentView.equipmentUserMenu();
            switch (sc.nextLine()) {
                case "0": // - 이전 메뉴로 돌아가기
                    return;
                case "1": // - 장비 정보 조회 메뉴로 이동
                    findEquipmentMenu();
                    break;
                case "2": // - 장비 정보 수정 메뉴로 이동
                    updateEquipmentMenu();
                    break;
                case "3": // - 장비 현황 대시보드 메뉴로 이동
                    showEquipmentDashboardMenu();
                    break;
                default:
                    System.out.println("잘못된 입력입니다");
                    break;
            }
        }
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-= 상위 메뉴 =-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 장비 조회
    public void findEquipmentMenu() {
        while (true) {
            equipmentView.findEquipmentMenu();
            switch (sc.nextLine()) {
                case "0": // - 이전 메뉴 돌아가기
                    return;
                case "1": // - 모든 장비 목록 조회
                    if (!equipmentService.findAllEquipment()) {
                        continue;
                    }
                    break;
                case "2": // - 특정 ID 장비 조회
                    int equipmentId = getEquipmentId();
                    if (equipmentId != -1) {
                        equipmentService.findByIdEquipment(equipmentId);
                    }
                    break;
                case "3": // - 특정 상태 장비 조회
                    String status = getEquipmentStatus();
                    equipmentService.findByStatusEquipment(status);
                    break;
                case "4": // - 특정 부서 장비 조회
                    String department = getEquipmentDepartment();
                    equipmentService.findByDepartmentEquipment(department);
                    break;
                case "5": // - 특정 카테고리 장비 조회
                    int categoryId = getEquipmentCategoryId();
                    if (categoryId != -1) {
                        String categoryName = equipmentService.getCategoryNameById(categoryId);
                        if (categoryName != null) {
                            equipmentService.findByCategoryEquipment(categoryName);
                        }
                    }
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
                case "0": // - 이전 메뉴 돌아가기
                    return;
                case "1": // - 신규 장비 등록
                    createEquipment();
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
                case "0": // - 이전 메뉴 돌아가기
                    return;
                case "1": // - 기존 장비 수정
                    updateEquipment();
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
            equipmentView.deleteEquipmentMenu();
            switch (sc.nextLine()) {
                case "0": // - 이전 메뉴 돌아가기
                    return;
                case "1": // - 장비 삭제
                    deleteEquipment();
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
                case "0": // - 이전 메뉴 돌아가기
                    return;
                case "1": // - 상태별 장비 개수 조회
                    equipmentService.countByStatus();
                    break;
                case "2": // - 부서별 장비 개수 조회
                    equipmentService.countByDepartment();
                    break;
                case "3": // - 카테고리별 장비 개수 조회
                    equipmentService.countByCategory();
                    break;
                case "4": // - 최근 수정된 장비 조회
                    equipmentService.getRecentlyUpdatedEquipments();
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
                case "0": // - 이전 메뉴 돌아가기
                    return;
                case "1": // - 모든 카테고리 조회
                    equipmentService.findAllCategories();
                    break;
                case "2": // - 신규 카테고리 추가
                    createCategory();
                    break;
                case "3": // - 기존 카테고리 삭제
                    int categoryId = getEquipmentCategoryId();
                    if (categoryId != -1) {
                        equipmentService.deleteCategory(categoryId);
                    }
                    break;
                default:
                    System.out.println("잘못된 입력입니다");
                    break;
            }
        }
    }

    // - 장비 폐기 메뉴
    public void disposeEquipmentMenu() {
        while (true) {
            equipmentView.disposeEquipmentMenu();
            switch (sc.nextLine()) {
                case "0": // - 이전 메뉴 돌아가기
                    return;
                case "1": // - 폐기할 장비 선택
                    disposeEquipment();
                    break;
                default:
                    System.out.println("잘못된 입력입니다");
                    break;
            }
        }
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-= 하위 메뉴 =-=-=-=-=-=-=-=-=-=-=-=
     **/

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
                    inputPurchaseInfo(equipment);
                    System.out.println("입력이 완료되었습니다");
                    break;
                case "2":
                    selectEquipmentCategory(equipment);
                    System.out.println("입력이 완료되었습니다");
                    break;
                case "3":
                    selectEquipmentManager(equipment);
                    System.out.println("입력이 완료되었습니다");
                    break;
                case "4":
                    inputStatusAndDescription(equipment);
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
    }

    // - 카테고리 생성
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
    } // end createCategory

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
                break;
            } else {
                System.out.println("날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력하세요.");
            }
        }

        while (true) {
            System.out.print("구매 가격 입력 : ");
            String priceStr = sc.nextLine();

            if (priceStr.isEmpty()) {
                System.out.println("금액을 입력해주세요");
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
        int categoryId = getEquipmentCategoryId();
        if (categoryId == -1) {
            return false;
        }

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
                break;
            } catch (Exception e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
        return true;
    }

    // - 상태 및 추가 정보 입력
    public boolean inputStatusAndDescription(Equipment equipment) {
        String description, status = getEquipmentStatus();

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
        if (equipmentId == -1) {
            return false;
        }
        equipment.setEquipmentId(equipmentId);

        while (isCompleted) {
            System.out.println("---- 장비 정보 수정 ----");
            equipmentView.createEquipmentMenu();
            switch (sc.nextLine()) {
                case "1":
                    System.out.println();
                    if (equipmentService.findByIdEquipment(equipmentId)) {
                        inputPurchaseInfo(equipment);
                        System.out.println("입력이 완료되었습니다");
                    }
                    break;
                case "2":
                    System.out.println();
                    if (equipmentService.findByIdEquipment(equipmentId)) {
                        selectEquipmentCategory(equipment);
                        System.out.println("입력이 완료되었습니다");
                    }
                    break;
                case "3":
                    System.out.println();
                    if (equipmentService.findByIdEquipment(equipmentId)) {
                        selectEquipmentManager(equipment);
                        System.out.println("입력이 완료되었습니다");
                    }
                    break;
                case "4":
                    System.out.println();
                    if (equipmentService.findByIdEquipment(equipmentId)) {
                        inputStatusAndDescription(equipment);
                        System.out.println("입력이 완료되었습니다");
                    }
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
        if (equipmentId == -1) {
            return false;
        }

        System.out.println("정말 삭제하시겠습니까? 취소하시면 이전 메뉴로 돌아갑니다");
        System.out.print("Y / N : ");

        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("n")) {
                System.out.println("삭제를 취소합니다.");
                return false;
            }
            if (input.equalsIgnoreCase("y"))
                break;
            System.out.println("Y / N 을 입력해주세요");
        }

        if (!equipmentService.deleteEquipment(equipmentId)) {
            return false;
        } else {
            System.out.println("삭제가 완료되었습니다");
            return true;
        }
    } // end deleteEquipment

    // - 장비 폐기
    public boolean disposeEquipment() {
        // 폐기예정 상태의 장비만 조회
        System.out.println("---- 폐기 가능한 장비 목록 ----");
        if (!equipmentService.findByStatusEquipment("폐기예정")) {
            System.out.println("폐기 가능한 장비가 없습니다.");
            return false;
        }

        // 장비 ID 입력 받기
        System.out.print("\n폐기할 장비 번호를 입력하세요 : ");
        int equipmentId;
        try {
            equipmentId = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력해주세요.");
            return false;
        }

        // 선택한 장비가 폐기예정 상태인지 확인
        if (!equipmentService.isEquipmentStatus(equipmentId, "폐기예정")) {
            System.out.println("선택한 장비는 폐기예정 상태가 아닙니다.");
            return false;
        }

        // 폐기 사유 입력 받기
        System.out.print("폐기 사유 입력 : ");
        String disposeReason = sc.nextLine();

        // 확인 메시지
        System.out.println("정말 폐기하시겠습니까? 폐기하면 상태가 '폐기완료'로 변경되고 이력에 기록됩니다.");
        System.out.print("Y / N : ");

        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("n")) {
                System.out.println("폐기를 취소합니다.");
                return false;
            }
            if (input.equalsIgnoreCase("y"))
                break;
            System.out.println("Y / N 을 입력해주세요");
        }

        // 폐기 처리
        if (!equipmentService.disposeEquipment(equipmentId, disposeReason)) {
            return false;
        }

        System.out.println("장비가 성공적으로 폐기되었습니다.");
        return true;
    }

    /**
     * =-=-=-=-=-=-=-=-=-=-=-= 재사용할거 모음 =-=-=-=-=-=-=-=-=-=-=-=
     **/

    // - 장비 번호 얻어주는 메소드
    public int getEquipmentId() {
        int equipmentId;

        if (!equipmentService.findAllEquipment()) {
            System.out.println("장비 목록을 불러올 수 없습니다. 다시 시도해주세요.");
            return -1;
        }

        while (true) {
            try {
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
    public String getEquipmentStatus() {
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
                    status = "수리필요";
                    break;
                case "4":
                    status = "수리중";
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
    public String getEquipmentDepartment() {
        String department;
        while (true) {
            equipmentView.inputDepartment();
            switch (sc.nextLine()) {
                case "1":
                    department = "인사 관리 부서";
                    break;
                case "2":
                    department = "예산/회계 관리 부서";
                    break;
                case "3":
                    department = "자산 관리 부서";
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
    public int getEquipmentCategoryId() {
        int categoryId;

        if (!equipmentService.findAllCategories()) {
            return -1;
        }

        while (true) {
            try {
                System.out.println();
                System.out.print("카테고리 번호 선택 : ");
                categoryId = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
        return categoryId;
    }

} // end class
