package equipmentAsset.inspection.view;

import java.sql.Date;
import java.sql.ResultSet;

public class InspectionView {

	/** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 사용 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

	public void inspectionMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌   𝙄 𝙉 𝙎 𝙋 𝙀 𝘾 𝙏 𝙄 𝙊 𝙉  ▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 점검 일정 관리");
		System.out.println("2️⃣ 점검 결과 관리");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void inspectionScheduleMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌    𝙎 𝘾 𝙃 𝙀 𝘿 𝙐 𝙇 𝙀     ▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 점검 일정 등록");
		System.out.println("2️⃣ 점검 일정 조회");
		System.out.println("3️⃣ 점검 일정 수정");
		System.out.println("4️⃣ 점검 일정 삭제");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void saveInspectionScheduleMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝙍 𝙀 𝙂 𝙄 𝙎 𝙏   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 점검 일정 등록");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void findInspectionScheduleMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝙎 𝙀 𝘼 𝙍 𝘾 𝙃   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 모든 점검 일정 조회");
		System.out.println("2️⃣ 장비 번호로 조회");
		System.out.println("3️⃣ 일정 번호로 조회");
		System.out.println("4️⃣ 점검 상태별 조회");
		System.out.println("5️⃣ 점검 주기별 조회");
		System.out.println("6️⃣ 점검 유형별 조회");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void updateInspectionScheduleMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝙐 𝙋 𝘿 𝘼 𝙏 𝙀   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 점검 일정 수정");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void deleteInspectionScheduleMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝘿 𝙀 𝙇 𝙀 𝙏 𝙀   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 점검 일정 삭제");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void inspectionResultMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌  𝙍 𝙀 𝙎 𝙐 𝙇 𝙏 𝙎  ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 점검 결과 등록");
		System.out.println("2️⃣ 점검 결과 조회");
		System.out.println("3️⃣ 점검 결과 수정");
		System.out.println("4️⃣ 점검 결과 삭제");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void saveInspectionResultMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝙍 𝙀 𝙂 𝙄 𝙎 𝙏   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 점검 결과 등록");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void findInspectionResultMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝙎 𝙀 𝘼 𝙍 𝘾 𝙃   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 모든 점검 결과 조회");
		System.out.println("2️⃣ 장비 번호로 조회");
		System.out.println("3️⃣ 결과 번호로 조회");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void updateInspectionResultMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝙐 𝙋 𝘿 𝘼 𝙏 𝙀   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 점검 결과 수정");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void deleteInspectionResultMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝘿 𝙀 𝙇 𝙀 𝙏 𝙀   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 점검 결과 삭제");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void updateInspectionScheduleItemMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝙐 𝙋 𝘿 𝘼 𝙏 𝙀   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 점검 유형 수정");
		System.out.println("2️⃣ 점검 주기 수정");
		System.out.println("3️⃣ 예정 일자 수정");
		System.out.println("4️⃣ 상태 수정");
		System.out.println("5️⃣ 설명 수정");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void updateInspectionResultItemMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝙐 𝙋 𝘿 𝘼 𝙏 𝙀   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 점검 일자 수정");
		System.out.println("2️⃣ 점검 결과 유형 수정");
		System.out.println("3️⃣ 점검 내용 수정");
		System.out.println("4️⃣ 특이사항 수정");
		System.out.println();
		System.out.print("⏩ ");
	}

	/** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 안내 출력 =-=-=-=-=-=-=-=-=-=-=-= **/

	public void getInspectionStatus() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝙎 𝙏 𝘼 𝙏 𝙐 𝙎   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("1️⃣ 예정");
		System.out.println("2️⃣ 완료");
		System.out.println("3️⃣ 직접 입력하기");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void getInspectionCycle() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌    𝘾 𝙔 𝘾 𝙇 𝙀    ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("1️⃣ 월간");
		System.out.println("2️⃣ 분기");
		System.out.println("3️⃣ 반기");
		System.out.println("4️⃣ 연간");
		System.out.println("5️⃣ 비정기");
		System.out.println("6️⃣ 기타");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void getInspectionType() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌     𝙏 𝙔 𝙋 𝙀     ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("1️⃣ 초기점검");
		System.out.println("2️⃣ 정기점검");
		System.out.println("3️⃣ 긴급점검");
		System.out.println("4️⃣ 기타");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void getInspectionResultType() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝙍 𝙀 𝙎 𝙐 𝙇 𝙏   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("1️⃣ 양호");
		System.out.println("2️⃣ 수리필요");
		System.out.println();
		System.out.print("⏩ ");
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
