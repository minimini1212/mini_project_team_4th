package equipmentAsset.repair.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import equipmentAsset.equipment.controller.EquipmentController;
import equipmentAsset.equipment.model.service.EquipmentService;
import equipmentAsset.repair.model.entity.RepairRequest;
import equipmentAsset.repair.model.entity.RepairResult;
import equipmentAsset.repair.model.service.RepairService;
import equipmentAsset.repair.view.RepairView;

public class RepairController {

	RepairView repairView = new RepairView();
	RepairService repairService = new RepairService();
	EquipmentController equipmentController = new EquipmentController();
	EquipmentService equipmentService = new EquipmentService();
	Scanner sc = new Scanner(System.in);

	/** =-=-=-=-=-=-=-=-=-=-=-= 최상위 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 최상위 수리 관리 메뉴
	public void repairMenu() {
		while (true) {
			repairView.repairMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴로 돌아가기
				return;
			case "1": // - 수리 요청 관리 메뉴로 이동
				repairRequestMenu();
				break;
			case "2": // - 수리 결과 관리 메뉴로 이동
				repairResultMenu();
				break;
			default:
				System.out.println("잘못된 입력입니다");
				break;
			}
		}
	}

	// - 수리 요청 관리 메뉴
	public void repairRequestMenu() {
		while (true) {
			repairView.repairRequestMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴로 돌아가기
				return;
			case "1": // - 수리 요청 등록 메뉴로 이동
				saveRepairRequestMenu();
				break;
			case "2": // - 수리 요청 조회 메뉴로 이동
				findRepairRequestMenu();
				break;
			case "3": // - 수리 요청 수정 메뉴로 이동
				updateRepairRequestMenu();
				break;
			case "4": // - 수리 요청 삭제 메뉴로 이동
				deleteRepairRequestMenu();
				break;
			default:
				System.out.println("잘못된 입력입니다");
				break;
			}
		}
	}

	// - 수리 결과 관리 메뉴
	public void repairResultMenu() {
		while (true) {
			repairView.repairResultMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴로 돌아가기
				return;
			case "1": // - 수리 결과 등록 메뉴로 이동
				saveRepairResultMenu();
				break;
			case "2": // - 수리 결과 조회 메뉴로 이동
				findRepairResultMenu();
				break;
			case "3": // - 수리 결과 수정 메뉴로 이동
				updateRepairResultMenu();
				break;
			case "4": // - 수리 결과 삭제 메뉴로 이동
				deleteRepairResultMenu();
				break;
			default:
				System.out.println("잘못된 입력입니다");
				break;
			}
		}
	}

	/** =-=-=-=-=-=-=-=-=-=-=-= 상위 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 요청 조회
	public void findRepairRequestMenu() {
		while (true) {
			repairView.findRepairRequestMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴 돌아가기
				return;
			case "1": // - 모든 수리 요청 조회
				if (!repairService.findAllRepairRequests()) {
					continue;
				}
				break;
			case "2": // - 장비 번호로 조회
				int equipmentId = equipmentController.getEquipmentId();
				if (equipmentId == -1 || !repairService.findRepairRequestByEquipmentId(equipmentId)) {
					continue;
				}
				break;
			case "3": // - 요청 번호로 조회
				int requestId = getRequestId();
				if (requestId == -1 || !repairService.findRepairRequestById(requestId)) {
					continue;
				}
				break;
			case "4": // - 상태별 조회
				if (!findRepairRequestByStatus()) {
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
	public void findRepairResultMenu() {
		while (true) {
			repairView.findRepairResultMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴로 돌아가기
				return;
			case "1": // - 모든 결과 조회
				if (!repairService.findAllRepairResults()) {
					continue;
				}
				break;
			case "2": // - 장비 번호로 조회
				int equipmentId = equipmentController.getEquipmentId();
				if (equipmentId == -1 || !repairService.findRepairResultByEquipmentId(equipmentId)) {
					continue;
				}
				break;
			case "3": // - 요청 번호로 조회
				int requestId = getRequestId();
				if (requestId == -1 || !repairService.findRepairResultByRequestId(requestId)) {
					continue;
				}
				break;
			case "4": // - 결과 유형별 조회
				if (!findRepairResultByType()) {
					continue;
				}
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
		}
	}

	// - 요청 등록
	public void saveRepairRequestMenu() {
		while (true) {
			repairView.saveRepairRequestMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴 돌아가기
				return;
			case "1": // - 요청 등록하기
				if (!createRepairRequest()) {
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
	public void saveRepairResultMenu() {
		while (true) {
			repairView.saveRepairResultMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴 돌아가기
				return;
			case "1": // - 결과 등록하기
				if (!createRepairResult()) {
					continue;
				}
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
		}
	}

	// - 요청 수정
	public void updateRepairRequestMenu() {
		while (true) {
			repairView.updateRepairRequestMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴 돌아가기
				return;
			case "1": // - 요청 수정하기
				if (!updateRepairRequest()) {
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
	public void updateRepairResultMenu() {
		while (true) {
			repairView.updateRepairResultMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴 돌아가기
				return;
			case "1": // - 결과 수정하기
				if (!updateRepairResult()) {
					continue;
				}
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
		}
	}

	// - 요청 삭제
	public void deleteRepairRequestMenu() {
		while (true) {
			repairView.deleteRepairRequestMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴 돌아가기
				return;
			case "1": // - 요청 삭제하기
				if (!deleteRepairRequest()) {
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
	public void deleteRepairResultMenu() {
		while (true) {
			repairView.deleteRepairResultMenu();
			switch (sc.nextLine()) {
			case "0": // - 이전 메뉴 돌아가기
				return;
			case "1": // - 결과 삭제하기
				if (!deleteRepairResult()) {
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

	// - 수리 요청 등록 메소드
	public boolean createRepairRequest() {
		// RepairRequest 객체 생성
		RepairRequest request = new RepairRequest();

		// 수리필요 상태의 장비만 선택
		System.out.println("---- 수리 필요 장비 목록 ----");
		if (!repairService.findRepairableEquipment()) {
			System.out.println("수리가 필요한 장비가 없습니다. 장비 상태를 먼저 '수리필요'로 변경해주세요.");
			return false;
		}

		System.out.print("\n수리할 장비 번호를 입력하세요 : ");
		int equipmentId;
		try {
			equipmentId = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("숫자만 입력해주세요.");
			return false;
		}

		request.setEquipmentId(equipmentId);

		System.out.println("---- 수리 요청 등록 ----");

		// 요청 일자 입력 - 날짜 정규표현식 (YYYY-MM-DD)
		String dateRegex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
		while (true) {
			System.out.print("요청 일자 입력 (YYYY-MM-DD) : ");
			String requestDateStr = sc.nextLine();

			if (requestDateStr.matches(dateRegex)) {
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date requestDate = dateFormat.parse(requestDateStr);
					request.setRequestDate(requestDate);
					break;
				} catch (ParseException e) {
					System.out.println("날짜 변환 중 오류가 발생했습니다.");
				}
			} else {
				System.out.println("날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력하세요.");
			}
		}

		// 고장 증상 입력
		System.out.print("고장 증상 입력 : ");
		request.setFailureSymptom(sc.nextLine());

		// 수리 요청 등록 서비스 호출
		if (!repairService.saveRepairRequest(request)) {
			return false;
		}

		System.out.println();
		System.out.println("수리 요청이 등록되었습니다.");
		return true;
	}

	// - 수리 결과 등록 메소드
	public boolean createRepairResult() {
		// RepairResult 객체 생성
		RepairResult result = new RepairResult();

		// 요청 선택 - 상태가 '예정'인 요청만 조회
		System.out.println("---- 결과 등록 가능한 수리 요청 ----");
		if (!repairService.findPendingRepairRequests()) {
			return false;
		}

		System.out.print("수리 요청 ID 선택: ");
		try {
			int requestId = Integer.parseInt(sc.nextLine());
			result.setRequestId(requestId);
		} catch (NumberFormatException e) {
			System.out.println("유효한 숫자를 입력해주세요.");
			return false;
		}

		System.out.println("---- 수리 결과 등록 ----");

		// 수리 내용 입력
		System.out.print("수리 내용 입력: ");
		result.setRepairContent(sc.nextLine());

		// 수리 비용 입력
		while (true) {
			System.out.print("수리 비용 입력: ");
			String costStr = sc.nextLine();

			try {
				int repairCost = Integer.parseInt(costStr.replaceAll("[^0-9]", ""));
				result.setRepairCost(repairCost);
				break;
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력해주세요.");
			}
		}

		// 수리 결과 입력
		repairView.getRepairResultType();
		String resultChoice = sc.nextLine();

		switch (resultChoice) {
		case "1":
			result.setResult("수리완료");
			break;
		case "2":
			result.setResult("수리불가");
			break;
		default:
			System.out.println("잘못된 입력입니다. 기본값 '수리완료'로 설정합니다");
			result.setResult("수리완료");
			break;
		}

		// 수리 결과 등록 서비스 호출
		if (!repairService.saveRepairResult(result)) {
			return false;
		}

		System.out.println();
		System.out.println("수리 결과가 등록되었습니다.");
		return true;
	}

	// 수리 요청 상태별 조회
	public boolean findRepairRequestByStatus() {
		String status;

		repairView.getRepairRequestStatus();

		switch (sc.nextLine()) {
		case "1":
			status = "예정";
			return repairService.findRepairRequestByStatus(status);
		case "2":
			status = "완료";
			return repairService.findRepairRequestByStatus(status);
		default:
			System.out.println("잘못된 입력입니다");
			return false;
		}
	}

	// 수리 결과 유형별 조회
	public boolean findRepairResultByType() {
		String resultType;

		repairView.getRepairResultType();

		switch (sc.nextLine()) {
		case "1":
			resultType = "수리완료";
			return repairService.findRepairResultByType(resultType);
		case "2":
			resultType = "수리불가";
			return repairService.findRepairResultByType(resultType);
		default:
			System.out.println("잘못된 입력입니다");
			return false;
		}
	}

	// 수리 요청 수정 메소드
	public boolean updateRepairRequest() {
		// 수정할 요청 선택
		int requestId = getRequestId();
		if (requestId == -1) {
			return false;
		}

		// 선택한 요청 정보 조회
		if (!repairService.findRepairRequestById(requestId)) {
			System.out.println("해당 요청을 찾을 수 없습니다.");
			return false;
		}

		// 수정 항목 선택 메뉴 반복
		boolean isCompleted = true;
		while (isCompleted) {
			System.out.println("---- 수리 요청 수정 ----");
			repairView.updateRepairRequestItemMenu();
			switch (sc.nextLine()) {
			case "0": // 이전 메뉴 돌아가기
				isCompleted = false;
				break;
			case "1": // 요청 일자 수정
				String dateRegex = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
				while (true) {
					System.out.print("요청 일자 입력 (YYYY-MM-DD) : ");
					String requestDateStr = sc.nextLine();

					if (requestDateStr.matches(dateRegex)) {
						if (!repairService.updateRequestDate(requestId, requestDateStr)) {
							break;
						}
						System.out.println("요청 일자가 수정되었습니다.");
						break;
					} else {
						System.out.println("날짜 형식이 올바르지 않습니다. YYYY-MM-DD 형식으로 입력하세요.");
					}
				}
				break;
			case "2": // 고장 증상 수정
				System.out.print("고장 증상 입력 : ");
				String failureSymptom = sc.nextLine();
				if (!repairService.updateFailureSymptom(requestId, failureSymptom)) {
					continue;
				}
				System.out.println("고장 증상이 수정되었습니다.");
				break;
			case "3": // 상태 수정
				String status = getRepairRequestStatus();
				if (!repairService.updateRequestStatus(requestId, status)) {
					continue;
				}
				System.out.println("상태가 수정되었습니다.");
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}

			// 수정 후 다시 조회
			if (isCompleted) {
				repairService.findRepairRequestById(requestId);
			}
		}
		return true;
	}

	// 수리 결과 수정 메소드
	public boolean updateRepairResult() {
		// 수정할 결과 선택
		int resultId = getResultId();
		if (resultId == -1) {
			return false;
		}

		// 수정 항목 선택 메뉴 반복
		boolean isCompleted = true;
		while (isCompleted) {
			System.out.println("---- 수리 결과 수정 ----");
			repairView.updateRepairResultItemMenu();
			switch (sc.nextLine()) {
			case "0": // 이전 메뉴 돌아가기
				isCompleted = false;
				break;
			case "1": // 수리 내용 수정
				System.out.print("수리 내용 입력: ");
				String repairContent = sc.nextLine();
				if (!repairService.updateRepairContent(resultId, repairContent)) {
					continue;
				}
				System.out.println("수리 내용이 수정되었습니다.");
				break;
			case "2": // 수리 비용 수정
				while (true) {
					System.out.print("수리 비용 입력: ");
					String costStr = sc.nextLine();

					try {
						int repairCost = Integer.parseInt(costStr.replaceAll("[^0-9]", ""));
						if (!repairService.updateRepairCost(resultId, repairCost)) {
							break;
						}
						System.out.println("수리 비용이 수정되었습니다.");
						break;
					} catch (NumberFormatException e) {
						System.out.println("숫자만 입력해주세요.");
					}
				}
				break;
			case "3": // 결과 유형 수정
				String repairResult = getRepairResultType();
				if (!repairService.updateRepairResultType(resultId, repairResult)) {
					continue;
				}
				System.out.println("결과 유형이 수정되었습니다.");
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
		}
		return true;
	}

	// 수리 요청 삭제 메소드
	public boolean deleteRepairRequest() {
	    // 삭제할 요청 선택
	    int requestId = getRequestId();
	    if (requestId == -1) {
	        return false;
	    }
	    
	    // 선택한 요청 정보 확인을 위해 조회
	    if (!repairService.findRepairRequestById(requestId)) {
	        System.out.println("해당 요청을 찾을 수 없습니다.");
	        return false;
	    }
	    
	    System.out.println("정말 삭제하시겠습니까? (Y/N)");
	    String confirm = sc.nextLine();
	    
	    if (!confirm.equalsIgnoreCase("Y")) {
	        System.out.println("삭제를 취소합니다.");
	        return false;
	    }
	    
	    // 삭제 서비스 호출
	    if (repairService.deleteRepairRequest(requestId)) {
	        System.out.println("수리 요청이 성공적으로 삭제되었습니다.");
	        return true;
	    } else {
	        // 오류 메시지는 DAO에서 이미 출력됨
	        return false;
	    }
	}

	// 수리 결과 삭제 메소드
	public boolean deleteRepairResult() {
	    // 삭제할 결과 선택
	    int resultId = getResultId();
	    if (resultId == -1) {
	        return false;
	    }
	    
	    System.out.println("정말 삭제하시겠습니까? (Y/N)");
	    String confirm = sc.nextLine();
	    
	    if (!confirm.equalsIgnoreCase("Y")) {
	        System.out.println("삭제를 취소합니다.");
	        return false;
	    }
	    
	    // 삭제 서비스 호출
	    if (repairService.deleteRepairResult(resultId)) {
	        System.out.println("수리 결과가 성공적으로 삭제되었습니다.");
	        return true;
	    } else {
	        // 오류 메시지는 DAO에서 이미 출력됨
	        return false;
	    }
	}

	/** =-=-=-=-=-=-=-=-=-=-=-= 재사용할거 모음 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 요청아이디 선택받고 반환
	public int getRequestId() {
		int requestId;

		if (!repairService.findAllRepairRequests()) {
			return -1;
		}

		while (true) {
			try {
				System.out.println();
				System.out.println("---------------------");
				System.out.print("요청 번호 선택 : ");
				requestId = Integer.parseInt(sc.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("숫자만 입력해주세요.");
			}
		}
		return requestId;
	}

	// - 결과아이디 선택받고 반환
	public int getResultId() {
		int resultId;

		if (!repairService.findAllRepairResults()) {
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

	// 수리 요청 상태 입력받아 반환해주는 메소드
	private String getRepairRequestStatus() {
		String status;
		while (true) {
			repairView.getRepairRequestStatus();
			switch (sc.nextLine()) {
			case "1":
				status = "예정";
				break;
			case "2":
				status = "완료";
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
			break;
		}
		return status;
	}

	// 수리 결과 유형 입력받아 반환해주는 메소드
	private String getRepairResultType() {
		String resultType;
		while (true) {
			repairView.getRepairResultType();
			switch (sc.nextLine()) {
			case "1":
				resultType = "수리완료";
				break;
			case "2":
				resultType = "수리불가";
				break;
			default:
				System.out.println("잘못된 입력입니다");
				continue;
			}
			break;
		}
		return resultType;
	}
}