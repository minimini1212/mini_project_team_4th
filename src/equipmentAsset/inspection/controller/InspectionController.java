package equipmentAsset.inspection.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import equipmentAsset.equipment.controller.EquipmentController;
import equipmentAsset.equipment.model.service.EquipmentService;
import equipmentAsset.inspection.model.entity.InspectionResult;
import equipmentAsset.inspection.model.entity.InspectionSchedule;
import equipmentAsset.inspection.model.service.InspectionService;
import equipmentAsset.inspection.view.InspectionView;

public class InspectionController {
	InspectionView inspectionView = new InspectionView();
	InspectionService inspectionService = new InspectionService();
	EquipmentController equipmentController = new EquipmentController();
	EquipmentService equipmentService = new EquipmentService();
	Scanner sc = new Scanner(System.in);
	
	/** =-=-=-=-=-=-=-=-=-=-=-= 최상위 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 최상위 점검 관리 메뉴
	public void inspectionMenu() {
	    while (true) {
	        inspectionView.inspectionMenu();
	        switch (sc.nextLine()) {
	        case "0": // - 이전 메뉴로 돌아가기
	            return;
	        case "1": // - 점검 일정 관리 메뉴로 이동
	            inspectionScheduleMenu();
	            break;
	        case "2": // - 점검 결과 관리 메뉴로 이동
	            inspectionResultMenu();
	            break;
	        default:
	            System.out.println("잘못된 입력입니다");
	            break;
	        }
	    }
	}

	// - 점검 일정 관리 메뉴
	public void inspectionScheduleMenu() {
	    while (true) {
	        inspectionView.inspectionScheduleMenu();
	        switch (sc.nextLine()) {
	        case "0": // - 이전 메뉴로 돌아가기
	            return;
	        case "1": // - 점검 일정 등록 메뉴로 이동
	            saveInspectionScheduleMenu();
	            break;
	        case "2": // - 점검 일정 조회 메뉴로 이동
	            findInspectionScheduleMenu();
	            break;
	        case "3": // - 점검 일정 수정 메뉴로 이동
	            updateInspectionScheduleMenu();
	            break;
	        case "4": // - 점검 일정 삭제 메뉴로 이동
	            deleteInspectionScheduleMenu();
	            break;
	        default:
	            System.out.println("잘못된 입력입니다");
	            break;
	        }
	    }
	}
	
	// - 점검 결과 관리 메뉴
	public void inspectionResultMenu() {
	    while (true) {
	        inspectionView.inspectionResultMenu();
	        switch (sc.nextLine()) {
	        case "0": // - 이전 메뉴로 돌아가기
	            return;
	        case "1": // - 점검 결과 등록 메뉴로 이동
	            saveInspectionResultMenu();
	            break;
	        case "2": // - 점검 결과 조회 메뉴로 이동
	            findInspectionResultMenu();
	            break;
	        case "3": // - 점검 결과 수정 메뉴로 이동
	            updateInspectionResultMenu();
	            break;
	        case "4": // - 점검 결과 삭제 메뉴로 이동
	            deleteInspectionResultMenu();
	            break;
	        default:
	            System.out.println("잘못된 입력입니다");
	            break;
	        }
	    }
	}
	
	/** =-=-=-=-=-=-=-=-=-=-=-= 상위 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 일정 조회
	public void findInspectionScheduleMenu() {
	    while (true) {
	        inspectionView.findInspectionScheduleMenu();
	        switch (sc.nextLine()) {
	        case "0": // - 이전 메뉴 돌아가기
	            return;
	        case "1": // - 모든 점검 일정 조회
	            if (!inspectionService.findAllInspection()) {
	                continue;
	            }
	            break;
	        case "2": // - 장비 번호로 조회"
	            int equipmentId = equipmentController.getEquipmentId();
	            if (equipmentId == -1 || !inspectionService.findByIdInspection(equipmentId)) {
	                continue;
	            }
	            break;
	        case "3": // - 일정 번호로 조회
	            int scheduleId = getScheduleId();
	            if (scheduleId == -1 || !inspectionService.findByScheduleId(scheduleId)) {
	                continue;
	            }
	            break;
	        case "4": // - 점검 상태별 조회
	            if (!findInspectionByStatus()) {
	                continue;
	            }
	            break;
	        case "5": // - 점검 주기별 조회
	            if (!findInspectionByCycle()) {
	                continue;
	            }
	            break;
	        case "6": // - 점검 유형별 조회
	            if (!findInspectionByType()) {
	                continue;
	            }
	            break;
	        default:
	            System.out.println("잘못된 입력입니다");
	            continue;
	        }
	    }
	}
	
	// - 결과 조회
	public void findInspectionResultMenu() {
	    while (true) {
	        inspectionView.findInspectionResultMenu();
	        switch (sc.nextLine()) {
	        case "0": // - 이전 메뉴로 돌아가기
	            return;
	        case "1": // - 모든 결과 조회
	            if (!inspectionService.findAllInspectionResult()) {
	                continue;
	            }
	            break;
	        case "2": // - 장비 번호로 조회
	            int equipmentId = equipmentController.getEquipmentId();
	            if (equipmentId == -1 || !inspectionService.findInspectionResultByEquipmentId(equipmentId)) {
	                continue;
	            }
	            break;
	        case "3": // - 결과 번호로 조회
	            int resultId = getResultId();
	            if (resultId == -1 || !inspectionService.findInspectionResultById(resultId)) {
	                continue;
	            }
	            break;
	        case "4": // - 점검 결과 유형별 조회 (양호, 수리필요)
	            if (!findInspectionByResultType()) {
	                continue;
	            }
	            break;
	        default:
	            System.out.println("잘못된 입력입니다");
	            continue;
	        }
	    }
	}

	// - 일정 등록
	public void saveInspectionScheduleMenu() {
		while (true) {
			inspectionView.saveInspectionScheduleMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴 돌아가기
				return;
			case "1": // - 일정 등록하기
				if (!createInspectionSchedule()) {
					continue;
				}
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
		}
	}

	// - 결과 등록
	public void saveInspectionResultMenu() {
		while (true) {
			inspectionView.saveInspectionResultMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴 돌아가기
				return;
			case "1": // - 결과 등록하기
				if (!createInspectionResult()) {
					continue;
				}
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
		}
	}

	// - 일정 수정
	public void updateInspectionScheduleMenu() {
		while (true) {
			inspectionView.updateInspectionScheduleMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴 돌아가기
				return;
			case "1": // - 일정 수정하기
				if (!updateInspectionSchedule()) {
					continue;
				}
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
		}
	}

	// - 결과 수정
	public void updateInspectionResultMenu() {
	    while (true) {
	        inspectionView.updateInspectionResultMenu();
	        switch (sc.nextLine()) {
	        case "0": // - 이전 메뉴 돌아가기
	            return;
	        case "1": // - 결과 수정하기
	            if (!updateInspectionResult()) {
	                continue;
	            }
	            break;
	        default:
	            System.out.println("잘못된 입력입니다");
	            continue;
	        }
	    }
	}
	
	// - 일정 삭제
	public void deleteInspectionScheduleMenu() {
		while (true) {
			inspectionView.deleteInspectionScheduleMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴 돌아가기
				return;
			case "1": // - 일정 삭제하기
				if (!deleteInspectionSchedule()) {
					continue;
				}
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
		}
	}

	// - 결과 삭제
	public void deleteInspectionResultMenu() {
	    while (true) {
	        inspectionView.deleteInspectionResultMenu();
	        switch (sc.nextLine()) {
	        case "0": // - 이전 메뉴 돌아가기
	            return;
	        case "1": // - 결과 삭제하기
	            if (!deleteInspectionResult()) {
	                continue;
	            }
	            break;
	        default:
	            System.out.println("잘못된 입력입니다");
	            continue;
	        }
	    }
	}
	
	/** =-=-=-=-=-=-=-=-=-=-=-=-= 하위 메뉴 =-=-=-=-=-=-=-=-=-=-=-=-= **/

	// - 점검 일정 등록 메소드
	public boolean createInspectionSchedule() {
		 // InspectionSchedule 객체 생성
	    InspectionSchedule schedule = new InspectionSchedule();

	    // 점검필요 상태의 장비만 선택
	    System.out.println("---- 점검 필요 장비 목록 ----");
	    if (!equipmentService.findByStatusEquipment("점검필요")) {
	        System.out.println("점검이 필요한 장비가 없습니다. 장비 상태를 먼저 '점검필요'로 변경해주세요.");
	        return false;
	    }
	    
	    System.out.print("\n점검할 장비 번호를 입력하세요 : ");
	    int equipmentId;
	    try {
	        equipmentId = Integer.parseInt(sc.nextLine());
	        // 입력한 장비가 실제로 '점검필요' 상태인지 추가 확인이 필요하면 여기서 검증 로직 추가
	    } catch (NumberFormatException e) {
	        System.out.println("숫자만 입력해주세요.");
	        return false;
	    }
	    
	    schedule.setEquipmentId(equipmentId);

	    System.out.println("---- 점검 일정 등록 ----");

	    // 점검 유형 입력
	    schedule.setInspectionType(getInspectionType());

	    // 점검 주기 입력
	    schedule.setInspectionCycle(getInspectionCycle());

	    // 예정 날짜 입력 - 날짜 정규표현식 (YYYY-MM-DD)
	    String dateRegex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
	    while (true) {
	        System.out.print("점검 예정 날짜 입력 (YYYY-MM-DD) : ");
	        String scheduledDateStr = sc.nextLine();

	        if (scheduledDateStr.matches(dateRegex)) {
	            try {
	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                Date scheduledDate = dateFormat.parse(scheduledDateStr);
	                schedule.setScheduledDate(scheduledDate);
	                break;
	            } catch (ParseException e) {
	                System.out.println("날짜 변환 중 오류가 발생했습니다.");
	            }
	        } else {
	            System.out.println("날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력하세요.");
	        }
	    }

	    // 상태 입력
	    schedule.setStatus(getInspectionStatus());

	    // 설명 입력
	    System.out.print("점검 설명 입력 : ");
	    schedule.setDescription(sc.nextLine());

	    // 점검 일정 등록 서비스 호출
	    if (!inspectionService.saveInspectionSchedule(schedule)) {
	        return false;
	    }

	    System.out.println();
	    System.out.println("점검 일정이 등록되었습니다.");
	    return true;
	}

	// - 점검 결과 등록 메소드
	public boolean createInspectionResult() {
		  // InspectionResult 객체 생성
	    InspectionResult result = new InspectionResult();

	    // 일정 선택 - 상태가 '예정'인 일정만 조회
	    System.out.println("---- 결과 등록 가능한 점검 일정 ----");
	    if (!inspectionService.findInspectionByStatus("예정")) {
	        return false;
	    }

	    System.out.print("점검 일정 ID 선택: ");
	    try {
	        int scheduleId = Integer.parseInt(sc.nextLine());
	        result.setScheduleId(scheduleId);
	    } catch (NumberFormatException e) {
	        System.out.println("유효한 숫자를 입력해주세요.");
	        return false;
	    }

	    System.out.println("---- 점검 결과 등록 ----");

	    // 점검 일자 입력 - 날짜 정규표현식 (YYYY-MM-DD)
	    String dateRegex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
	    while (true) {
	        System.out.print("점검 실시 날짜 입력 (YYYY-MM-DD) : ");
	        String inspectionDateStr = sc.nextLine();

	        if (inspectionDateStr.matches(dateRegex)) {
	            try {
	                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                Date inspectionDate = dateFormat.parse(inspectionDateStr);
	                result.setInspectionDate(inspectionDate);
	                break;
	            } catch (ParseException e) {
	                System.out.println("날짜 변환 중 오류가 발생했습니다.");
	            }
	        } else {
	            System.out.println("날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력하세요.");
	        }
	    }

	    // 점검 결과 입력
	    inspectionView.getInspectionResultType();
	    String resultChoice = sc.nextLine();

	    switch (resultChoice) {
	    case "1":
	        result.setInspectionResult("양호");
	        break;
	    case "2":
	        result.setInspectionResult("수리필요");
	        break;
	    default:
	        System.out.println("잘못된 입력입니다. 기본값 '양호'로 설정합니다");
	        result.setInspectionResult("양호");
	        break;
	    }

	    // 점검 내용 입력
	    System.out.print("점검 내용 입력: ");
	    result.setInspectionContent(sc.nextLine());

	    // 특이사항 입력
	    System.out.print("특이사항 입력: ");
	    result.setSpecialNote(sc.nextLine());

	    // 점검 결과 등록 서비스 호출
	    if (!inspectionService.saveInspectionResult(result)) {
	        return false;
	    }

	    System.out.println();
	    System.out.println("점검 결과가 등록되었습니다.");
	    return true;
	}
	
	// 점검 결과 유형별 조회
	public boolean findInspectionByResultType() {
	    String resultType;

	    inspectionView.getInspectionResultType();

	    switch (sc.nextLine()) {
	    case "1":
	        resultType = "양호";
	        return inspectionService.findInspectionResultByType(resultType);
	    case "2":
	        resultType = "수리필요";
	        return inspectionService.findInspectionResultByType(resultType);
	    default:
	        System.out.println("잘못된 입력입니다");
	        return false;
	    }
	}
	
	// - 결과 아이디 선택받고 반환
	public int getResultId() {
	    int resultId;

	    if (!inspectionService.findAllInspectionResult()) {
	        return -1;
	    }

	    while (true) {
	        try {
	            System.out.println();
	            System.out.println("---------------------");
	            System.out.print("결과 번호 선택 : ");
	            resultId = Integer.parseInt(sc.nextLine());
	            break;
	        } catch (Exception e) {
	            System.out.println("숫자만 입력해주세요.");
	        }
	    }
	    return resultId;
	}

	// 상태별 점검 일정 조회
	public boolean findInspectionByStatus() {
		String status;

		inspectionView.getInspectionStatus();

		switch (sc.nextLine()) {
		case "1":
			status = "예정";
			return inspectionService.findInspectionByStatus(status);
		case "2":
			status = "완료";
			return inspectionService.findInspectionByStatus(status);
		case "3":
			// 기타 상태 조회 (예정, 완료가 아닌 모든 상태)
			return inspectionService.findInspectionByStatusOthers();
		default:
			System.out.println("잘못된 입력입니다");
			return false;
		}
	}

	// 주기별 점검 일정 조회 수정
	public boolean findInspectionByCycle() {
		String cycle;

		inspectionView.getInspectionCycle();

		switch (sc.nextLine()) {
		case "1":
			cycle = "월간";
			return inspectionService.findInspectionByCycle(cycle);
		case "2":
			cycle = "분기";
			return inspectionService.findInspectionByCycle(cycle);
		case "3":
			cycle = "반기";
			return inspectionService.findInspectionByCycle(cycle);
		case "4":
			cycle = "연간";
			return inspectionService.findInspectionByCycle(cycle);
		case "5":
			cycle = "비정기";
			return inspectionService.findInspectionByCycle(cycle);
		case "6":
			// 기타 주기 조회 (기본 주기 외 모든 주기)
			return inspectionService.findInspectionByCycleOthers();
		default:
			System.out.println("잘못된 입력입니다");
			return false;
		}
	}

	// 유형별 점검 일정 조회 수정
	public boolean findInspectionByType() {
		String type;

		inspectionView.getInspectionType();

		switch (sc.nextLine()) {
		case "1":
			type = "초기점검";
			return inspectionService.findInspectionByType(type);
		case "2":
			type = "정기점검";
			return inspectionService.findInspectionByType(type);
		case "3":
			type = "긴급점검";
			return inspectionService.findInspectionByType(type);
		case "4":
			// 기타 유형 조회 (기본 유형 외 모든 유형)
			return inspectionService.findInspectionByTypeOthers();
		default:
			System.out.println("잘못된 입력입니다");
			return false;
		}
	}

	// 점검 일정 수정 메소드
	public boolean updateInspectionSchedule() {
		// 수정할 일정 선택
		int scheduleId = getScheduleId();
		if (scheduleId == -1) {
			return false;
		}

		// 선택한 일정 정보 조회
		if (!inspectionService.findByScheduleId(scheduleId)) {
			System.out.println("해당 일정을 찾을 수 없습니다.");
			return false;
		}

		// 수정 항목 선택 메뉴 반복
		boolean isCompleted = true;
		while (isCompleted) {
			System.out.println("---- 점검 일정 수정 ----");
			inspectionView.updateInspectionScheduleItemMenu();
			switch (sc.nextLine()) {
			case "0": // 이전 메뉴 돌아가기
				isCompleted = false;
				break;
			case "1": // 점검 유형 수정
				String inspectionType = getInspectionType();
				if (!inspectionService.updateInspectionType(scheduleId, inspectionType)) {
					continue;
				}
				System.out.println("점검 유형이 수정되었습니다.");
				break;
			case "2": // 점검 주기 수정
				String inspectionCycle = getInspectionCycle();
				if (!inspectionService.updateInspectionCycle(scheduleId, inspectionCycle)) {
					continue;
				}
				System.out.println("점검 주기가 수정되었습니다.");
				break;
			case "3": // 예정 일자 수정
				String dateRegex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
				while (true) {
					System.out.print("점검 예정 날짜 입력 (YYYY-MM-DD) : ");
					String scheduledDateStr = sc.nextLine();

					if (scheduledDateStr.matches(dateRegex)) {
						if (!inspectionService.updateScheduledDate(scheduleId, scheduledDateStr)) {
							break;
						}
						System.out.println("예정 일자가 수정되었습니다.");
						break;
					} else {
						System.out.println("날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력하세요.");
					}
				}
				break;
			case "4": // 상태 수정
				String status = getInspectionStatus();
				if (!inspectionService.updateStatus(scheduleId, status)) {
					continue;
				}
				System.out.println("상태가 수정되었습니다.");
				break;
			case "5": // 설명 수정
				System.out.print("점검 설명 입력 : ");
				String description = sc.nextLine();
				if (!inspectionService.updateDescription(scheduleId, description)) {
					continue;
				}
				System.out.println("설명이 수정되었습니다.");
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
			// 수정 후 다시 조회
			if (isCompleted) {
				inspectionService.findByScheduleId(scheduleId);
			}
		}
		return true;
	}
	
	// - 점검 결과 수정
	public boolean updateInspectionResult() {
	    // 수정할 결과 선택
	    int resultId = getResultId();
	    if (resultId == -1) {
	        return false;
	    }

	    // 선택한 결과 정보 조회
	    if (!inspectionService.findInspectionResultById(resultId)) {
	        System.out.println("해당 결과를 찾을 수 없습니다.");
	        return false;
	    }

	    // 수정 항목 선택 메뉴 반복
	    boolean isCompleted = true;
	    while (isCompleted) {
	        System.out.println("---- 점검 결과 수정 ----");
	        inspectionView.updateInspectionResultItemMenu();
	        switch (sc.nextLine()) {
	        case "0": // 이전 메뉴 돌아가기
	            isCompleted = false;
	            break;
	        case "1": // 점검 일자 수정
	            String dateRegex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
	            while (true) {
	                System.out.print("점검 실시 날짜 입력 (YYYY-MM-DD) : ");
	                String inspectionDateStr = sc.nextLine();

	                if (inspectionDateStr.matches(dateRegex)) {
	                    if (!inspectionService.updateInspectionDate(resultId, inspectionDateStr)) {
	                        break;
	                    }
	                    System.out.println("점검 일자가 수정되었습니다.");
	                    break;
	                } else {
	                    System.out.println("날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력하세요.");
	                }
	            }
	            break;
	        case "2": // 점검 결과 유형 수정
	            inspectionView.getInspectionResultType();
	            String resultChoice = sc.nextLine();
	            String inspectionResult = "";
	            
	            switch (resultChoice) {
	            case "1":
	                inspectionResult = "양호";
	                break;
	            case "2":
	                inspectionResult = "수리필요";
	                break;
	            default:
	                System.out.println("잘못된 입력입니다.");
	                continue;
	            }
	            
	            if (!inspectionService.updateInspectionResult(resultId, inspectionResult)) {
	                continue;
	            }
	            System.out.println("점검 결과가 수정되었습니다.");
	            break;
	        case "3": // 점검 내용 수정
	            System.out.print("점검 내용 입력: ");
	            String inspectionContent = sc.nextLine();
	            if (!inspectionService.updateInspectionContent(resultId, inspectionContent)) {
	                continue;
	            }
	            System.out.println("점검 내용이 수정되었습니다.");
	            break;
	        case "4": // 특이사항 수정
	            System.out.print("특이사항 입력: ");
	            String specialNote = sc.nextLine();
	            if (!inspectionService.updateSpecialNote(resultId, specialNote)) {
	                continue;
	            }
	            System.out.println("특이사항이 수정되었습니다.");
	            break;
	        default:
	            System.out.println("잘못된 입력입니다");
	            continue;
	        }

	        // 수정 후 다시 조회
	        if (isCompleted) {
	            inspectionService.findInspectionResultById(resultId);
	        }
	    }
	    return true;
	}

	// 점검 일정 삭제 메소드
	public boolean deleteInspectionSchedule() {
		// 삭제할 일정 선택
		int scheduleId = getScheduleId();
		if (scheduleId == -1) {
			return false;
		}

		// 선택한 일정 정보 확인을 위해 조회
		if (!inspectionService.findByScheduleId(scheduleId)) {
			System.out.println("해당 일정을 찾을 수 없습니다.");
			return false;
		}

		System.out.println("정말 삭제하시겠습니까? (Y/N)");
		String confirm = sc.nextLine();

		if (!confirm.equalsIgnoreCase("Y")) {
			System.out.println("삭제를 취소합니다.");
			return false;
		}

		// 삭제 서비스 호출
		if (!inspectionService.deleteInspectionSchedule(scheduleId)) {
			return false;
		}

		System.out.println("점검 일정이 성공적으로 삭제되었습니다.");
		return true;
	}

	// - 점검 결과 삭제
	public boolean deleteInspectionResult() {
	    // 삭제할 결과 선택
	    int resultId = getResultId();
	    if (resultId == -1) {
	        return false;
	    }

	    // 선택한 결과 정보 확인을 위해 조회
	    if (!inspectionService.findInspectionResultById(resultId)) {
	        System.out.println("해당 결과를 찾을 수 없습니다.");
	        return false;
	    }

	    System.out.println("정말 삭제하시겠습니까? (Y/N)");
	    String confirm = sc.nextLine();

	    if (!confirm.equalsIgnoreCase("Y")) {
	        System.out.println("삭제를 취소합니다.");
	        return false;
	    }

	    // 삭제 서비스 호출
	    if (!inspectionService.deleteInspectionResult(resultId)) {
	        return false;
	    }

	    System.out.println("점검 결과가 성공적으로 삭제되었습니다.");
	    return true;
	}
	
	/** =-=-=-=-=-=-=-=-=-=-=-= 재사용할거 모음 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 일정아이디 선택받고 반환
	public int getScheduleId() {
		int scheduleId;

		if (!inspectionService.findAllInspection()) {
			return -1;
		}

		while (true) {
			try {
				System.out.println();
				System.out.println("---------------------");
				System.out.print("일정 번호 선택 : ");
				scheduleId = Integer.parseInt(sc.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
			}
		}
		return scheduleId;
	}

	// 점검 유형 입력받아 반환해주는 메소드
	private String getInspectionType() {
		String inspectionType;
		while (true) {
			inspectionView.getInspectionType();
			switch (sc.nextLine()) {
			case "1":
				inspectionType = "초기점검";
				break;
			case "2":
				inspectionType = "정기점검";
				break;
			case "3":
				inspectionType = "긴급점검";
				break;
			case "4":
				System.out.print("점검 유형 직접 입력 : ");
				inspectionType = sc.nextLine();
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
			break;
		}
		return inspectionType;
	}

	// 점검 주기 입력받아 반환해주는 메소드
	private String getInspectionCycle() {
		String inspectionCycle;
		while (true) {
			inspectionView.getInspectionCycle();
			switch (sc.nextLine()) {
			case "1":
				inspectionCycle = "월간";
				break;
			case "2":
				inspectionCycle = "분기";
				break;
			case "3":
				inspectionCycle = "반기";
				break;
			case "4":
				inspectionCycle = "연간";
				break;
			case "5":
				inspectionCycle = "비정기";
				break;
			case "6":
				System.out.print("점검 주기 직접 입력 : ");
				inspectionCycle = sc.nextLine();
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
			break;
		}
		return inspectionCycle;
	}

	// 점검 상태 입력받아 반환해주는 메소드
	private String getInspectionStatus() {
		String status;
		while (true) {
			inspectionView.getInspectionStatus();
			switch (sc.nextLine()) {
			case "1":
				status = "예정";
				break;
			case "2":
				status = "완료";
				break;
			case "3":
				System.out.print("점검 상태 직접 입력 : ");
				status = sc.nextLine();
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
			break;
		}
		return status;
	}
}