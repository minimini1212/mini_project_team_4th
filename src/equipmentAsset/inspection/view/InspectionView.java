package equipmentAsset.inspection.view;

import java.sql.Date;
import java.sql.ResultSet;

public class InspectionView {

	/** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 사용 메소드 =-=-=-=-=-=-=-=-=-=-=-= **/

	public void InspectionMenu() {
		System.out.println("---- 장비 점검 관리 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 점검 일정 관리");
		System.out.println("2. 점검 결과 관리");
		System.out.print("번호 입력 : ");
	}

	public void InspectionScheduleMenu() {
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

	public void InspectionResultMenu() {
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

	/** =-=-=-=-=-=-=-=-=-=-=-= DAO 사용 메소드 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 장비 정보 출력
	public void findAllInspection(ResultSet rs) {
		try {
			System.out.printf("%-6s\t%-22s\t%-12s\t%-10s\t%-12s\t%-8s\t%-10s\t%-8s\t%-30s\n", "일정ID", "장비명", "점검유형",
					"점검주기", "예정일자", "예상시간", "예상비용", "상태", "설명");
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------------------------------------");

			while (rs.next()) {
				int scheduleId = rs.getInt("schedule_id");
				String equipmentName = rs.getString("equipment_name");
				String inspectionType = rs.getString("inspection_type");
				String inspectionCycle = rs.getString("inspection_cycle");
				Date scheduledDate = rs.getDate("scheduled_date");
				int estimatedDuration = rs.getInt("estimated_duration");
				int estimatedCost = rs.getInt("estimated_cost");
				String status = rs.getString("status");
				String description = rs.getString("description");

				System.out.printf("%-6d\t%-15s\t%-12s\t%-10s\t%-12s\t%-4d분\t        %-3d만원\t        %-8s\t%-30s\n",
						scheduleId, equipmentName, inspectionType, inspectionCycle, scheduledDate, estimatedDuration,
						estimatedCost / 10000, status, description);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end findAllInspection()

} // end class
