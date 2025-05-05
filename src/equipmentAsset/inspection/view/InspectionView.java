package equipmentAsset.inspection.view;

import java.sql.Date;
import java.sql.ResultSet;

public class InspectionView {

	/** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 사용 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

	public void inspectionMenu() {
		System.out.println("---- 장비 점검 관리 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 점검 일정 관리");
		System.out.println("2. 점검 결과 관리");
		System.out.print("번호 입력 : ");
	}

	public void inspectionScheduleMenu() {
		System.out.println("---- 점검 일정 관리 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 점검 일정 등록");
		System.out.println("2. 점검 일정 조회");
		System.out.println("3. 점검 일정 수정");
		System.out.println("4. 점검 일정 삭제");
		System.out.print("번호 입력 : ");
	}

	public void saveInspectionScheduleMenu() {
		System.out.println("---- 점검 일정 등록 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 점검 일정 등록");
		System.out.print("번호 입력 : ");
	}

	public void findInspectionScheduleMenu() {
		System.out.println("---- 점검 일정 조회 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 모든 점검 일정 조회");
		System.out.println("2. 장비 번호로 조회");
		System.out.println("3. 일정 번호로 조회");
		System.out.println("4. 점검 상태별 조회");
		System.out.println("5. 점검 주기별 조회");
		System.out.println("6. 점검 유형별 조회");
		System.out.print("번호 입력 : ");
	}

	public void updateInspectionScheduleMenu() {
		System.out.println("---- 점검 일정 수정 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 점검 일정 수정");
		System.out.print("번호 입력 : ");
	}

	public void deleteInspectionScheduleMenu() {
		System.out.println("---- 점검 일정 삭제 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 점검 일정 삭제");
		System.out.print("번호 입력 : ");
	}

	public void inspectionResultMenu() {
		System.out.println("---- 점검 결과 관리 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 점검 결과 등록");
		System.out.println("2. 점검 결과 조회");
		System.out.println("3. 점검 결과 수정");
		System.out.println("4. 점검 결과 삭제");
		System.out.print("번호 입력 : ");
	}

	public void saveInspectionResultMenu() {
		System.out.println("---- 점검 결과 등록 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 점검 결과 등록");
		System.out.print("번호 입력 : ");
	}

	public void findInspectionResultMenu() {
		System.out.println("---- 점검 결과 조회 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 모든 점검 결과 조회");
		System.out.println("2. 장비 번호로 조회");
		System.out.println("3. 결과 번호로 조회");
		System.out.print("번호 입력 : ");
	}

	public void updateInspectionResultMenu() {
		System.out.println("---- 점검 결과 수정 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 점검 결과 수정");
		System.out.print("번호 입력 : ");
	}

	public void deleteInspectionResultMenu() {
		System.out.println("---- 점검 결과 삭제 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 점검 결과 삭제");
		System.out.print("번호 입력 : ");
	}
	
	public void updateInspectionScheduleItemMenu() {
		System.out.println("---- 점검 일정 수정 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 점검 유형 수정");
		System.out.println("2. 점검 주기 수정");
		System.out.println("3. 예정 일자 수정");
		System.out.println("4. 상태 수정");
		System.out.println("5. 설명 수정");
		System.out.print("번호 입력 : ");
	}
	
	public void updateInspectionResultItemMenu() {
		System.out.println("---- 점검 결과 수정 ----");
	    System.out.println("0. 이전 메뉴 돌아가기");
	    System.out.println("1. 점검 일자 수정");
	    System.out.println("2. 점검 결과 유형 수정");
	    System.out.println("3. 점검 내용 수정");
	    System.out.println("4. 특이사항 수정");
	    System.out.print("번호 입력 : ");
	}
	
	/** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 안내 출력 =-=-=-=-=-=-=-=-=-=-=-= **/
	
	public void getInspectionStatus() {
		System.out.println("---- 점검 상태 선택 ----");
		System.out.println("1. 예정");
		System.out.println("2. 완료");
		System.out.println("3. 직접 입력하기");
		System.out.print("번호 입력 : ");
	}
	
	public void getInspectionCycle() {
		System.out.println("---- 점검 주기 선택 ----");
		System.out.println("1. 월간");
		System.out.println("2. 분기");
		System.out.println("3. 반기");
		System.out.println("4. 연간");
		System.out.println("5. 비정기");
		System.out.println("6. 기타");
		System.out.print("번호 입력 : ");
	}
	
	public void getInspectionType() {
		System.out.println("---- 점검 유형 선택 ----");
		System.out.println("1. 초기점검");
		System.out.println("2. 정기점검");
		System.out.println("3. 긴급점검");
        System.out.println("4. 기타");
		System.out.print("번호 입력 : ");
	}
	
	public void getInspectionResultType() {
		System.out.println("---- 점검 결과 선택 ----");
        System.out.println("1. 양호");
        System.out.println("2. 수리필요");
        System.out.print("번호 입력: ");
	}
	
	/** =-=-=-=-=-=-=-=-=-=-=-= DAO 사용 메소드 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 장비 정보 출력
	public void findAllInspection(ResultSet rs) {
		try {
	        System.out.printf("%-6s\t%-22s\t%-12s\t%-10s\t%-12s\t%-8s\t%-30s\n", "일정ID", "장비명", "점검유형",
	                "점검주기", "예정일자", "상태", "설명");
	        System.out.println(
	                "------------------------------------------------------------------------------------------------------------------------------------------------------------");

	        while (rs.next()) {
	            int scheduleId = rs.getInt("schedule_id");
	            String equipmentName = rs.getString("equipment_name");
	            String inspectionType = rs.getString("inspection_type");
	            String inspectionCycle = rs.getString("inspection_cycle");
	            Date scheduledDate = rs.getDate("scheduled_date");
	            String status = rs.getString("status");
	            String description = rs.getString("description");

	            System.out.printf("%-6d\t%-15s\t%-12s\t%-10s\t%-12s\t%-8s\t%-30s\n",
	                    scheduleId, equipmentName, inspectionType, inspectionCycle, scheduledDate, status, description);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	} // end findAllInspection()
	
	// - 점검 결과 정보 출력
	public void findAllInspectionResult(ResultSet rs) {
		 try {
		        System.out.printf("%-6s\t%-20s\t%-12s\t%-12s\t%-12s\t%-40s\t%-30s\n", 
		                "결과ID", "장비명", "일정ID", "점검일자", "점검결과", "점검내용", "특이사항");
		        System.out.println(
		                "------------------------------------------------------------------------------------------------------------------------------------------------------------");

		        while (rs.next()) {
		            int resultId = rs.getInt("result_id");
		            String equipmentName = rs.getString("equipment_name");
		            int scheduleId = rs.getInt("schedule_id");
		            Date inspectionDate = rs.getDate("inspection_date");
		            String inspectionResult = rs.getString("inspection_result");
		            String inspectionContent = rs.getString("inspection_content");
		            String specialNote = rs.getString("special_note");

		            System.out.printf("%-6d\t%-20s\t%-12d\t%-12s\t%-12s\t%-40s\t%-30s\n",
		                    resultId, equipmentName, scheduleId, inspectionDate, inspectionResult, 
		                    inspectionContent, specialNote);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	} // end findAllInspectionResult()

} // end class
