package equipmentAsset.equipment.view;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

public class EquipmentView {

	/** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 사용 메소드 =-=-=-=-=-=-=-=-=-=-=-= **/

	public void equipmentMenu() {
	    System.out.println("---- 장비 관리 ----");
	    System.out.println("0. 이전 메뉴 돌아가기");
	    System.out.println("1. 장비 정보 조회");
	    System.out.println("2. 장비 정보 등록");
	    System.out.println("3. 장비 정보 수정");
	    System.out.println("4. 장비 정보 삭제");
	    System.out.println("5. 장비 현황 대시보드");
	    System.out.println("6. 카테고리 관리");
	    System.out.print("번호 입력 : ");
	}
	
	public void findEquipmentMenu() {
		System.out.println("---- 장비 정보 조회 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 모든 장비 목록 조회");
		System.out.println("2. 특정 ID 장비 조회");
		System.out.println("3. 특정 상태 장비 조회");
		System.out.println("4. 특정 부서 장비 조회");
		System.out.println("5. 특정 카테고리 장비 조회");
		System.out.print("번호 입력 : ");
	}

	public void createEquipmentMenu() {
		System.out.println("1. 구매정보 입력");
		System.out.println("2. 카테고리 입력");
		System.out.println("3. 담당자 입력");
		System.out.println("4. 상태 및 추가 설명 입력");
		System.out.println("5. 나중에 입력하기");
		System.out.print("번호 입력 : ");
	}
	
	public void inputStatusAndDescriptionMenu() {
		System.out.println("1. 정상");
		System.out.println("2. 점검필요");
		System.out.println("3. 수리필요");
		System.out.println("4. 수리중");
		System.out.print("번호 입력 : ");
	}

	public void saveEquipmentMenu() {
		System.out.println("---- 장비 정보 등록 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 신규 장비 등록");
		System.out.print("번호 입력 : ");
	}
	
	public void updateEquipmentMenu() {
		System.out.println("---- 장비 정보 수정 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 장비 정보 수정");
		System.out.print("번호 입력 : ");
	}
	
	public void deleteEquipmentMenu() {
		System.out.println("---- 장비 정보 삭제 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 장비 삭제");
		System.out.print("번호 입력 : ");
	}

	public void showEquipmentDashboardMenu() {
		System.out.println("---- 장비 현황 조회 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 상태별 장비 개수 조회");
		System.out.println("2. 부서별 장비 개수 조회");
		System.out.println("3. 카테고리별 장비 개수 조회");
		System.out.println("4. 최근 수정된 장비 조회");
		System.out.print("번호 입력 : ");
	}

	public void manageCategoryMenu() {
		System.out.println("---- 카테고리 관리 ----");
		System.out.println("0. 이전 메뉴 돌아가기");
		System.out.println("1. 모든 카테고리 조회");
		System.out.println("2. 신규 카테고리 추가");
		System.out.println("3. 기존 카테고리 삭제");
		System.out.print("번호 입력 : ");
	}
	
	public void inputDepartment() {
		System.out.println("---- 전체 부서 목록 ----");
		System.out.println("1. 인사팀");
		System.out.println("2. 재무팀");
		System.out.println("3. 재고팀");
		System.out.println("4. 장비팀");
		System.out.print("번호 입력 : ");
	}
	
	/** =-=-=-=-=-=-=-=-=-=-=-= DAO 사용 메소드 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 장비 정보 출력
	public void findAllEquipment(ResultSet rs) {
		try {
			System.out.printf("%-7s %-25s\t%-20s\t%-18s\t%-17s\t%-12s\t%-12s\t%-10s\t%-15s\t%-15s\t%-15s\t%-15s\n",
					"장비ID", "장비명", "모델명", "제조사", "시리얼번호", "구매일", "구매가격", "상태", "담당자", "부서", "직급", "직무");
			System.out.println(
					"----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			
			while (rs.next()) {
				int equipmentId = rs.getInt("equipment_id");
				String categoryName = rs.getString("category_name");
				String equipmentName = rs.getString("equipment_name");
				String modelName = rs.getString("model_name");
				String manufacturer = rs.getString("manufacturer");
				String serialNumber = rs.getString("serial_number");
				Date purchaseDate = rs.getDate("purchase_date");
				int purchasePrice = rs.getInt("purchase_price");
				String status = rs.getString("status");
				String managerName = rs.getString("manager_name");
				String departmentName = rs.getString("department_name");
				String positionName = rs.getString("position_name");
				String jobName = rs.getString("job_name");

				System.out.printf("%-7d %-25s\t%-20s\t%-18s\t%-17s\t%-8s\t%6d만원\t%-10s\t%-15s\t%-15s\t%-15s\t%-15s\n",
						equipmentId, equipmentName, modelName, manufacturer, serialNumber, purchaseDate,
						purchasePrice / 10000, status, managerName, departmentName, positionName, jobName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end displayEquipmentResults()

	// - 담당자 명단 출력
	public void findAllManager(ResultSet rs) {
		try {
			System.out.printf("%-10s %-15s\t%-15s\t%-15s\n", "담당자ID", "부서명", "직무", "담당자명");
			System.out.println("-------------------------------------------------------------");

			while (rs.next()) {
				int employeeId = rs.getInt("EMPLOYEE_ID");
				String departmentName = rs.getString("DEPARTMENT_NAME");
				String jobName = rs.getString("JOB_NAME");
				String employeeName = rs.getString("EMPLOYEE_NAME");

				System.out.printf("%-10d %-15s\t%-15s\t%-15s\n", employeeId, departmentName, jobName, employeeName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end findAllManager

	// - 상태별 장비 개수 출력
	public void countByStatus(ResultSet rs) {
		try {
			System.out.println("상태\t  개수");
			System.out.println("-------------");
			while (rs.next()) {
				String status = rs.getString("status");
				int count = rs.getInt("count(*)");
				System.out.printf("%s\t  %d개\n", status, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end countByStatus

	// - 카테고리별 장비 개수 출력
	public void countByCategory(ResultSet rs) {
		try {
			System.out.println("카테고리\t  개수");
			System.out.println("-------------");
			while (rs.next()) {
				String category = rs.getString("CATEGORY_ID");
				int count = rs.getInt("count(*)");
				System.out.printf("%s\t  %d개\n", category, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end countByCategory

	// - 부서별 장비 개수 출력
	public void countByDepartment(ResultSet rs) {
		try {
			System.out.println("부서이름\t  개수");
			System.out.println("-------------");
			while (rs.next()) {
				String departmentId = rs.getString("department_id");
				int count = rs.getInt("count(*)");
				System.out.printf("%s\t  %d개\n", departmentId, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end countByDepartment

	// - 카테고리별 장비 금액 합계
	public void sumPurchasePriceByCategory(ResultSet rs) {
		try {
			System.out.println("카테고리\t     가격");
			System.out.println("----------------");
			while (rs.next()) {
				String category = rs.getString("CATEGORY_ID");
				int sum = rs.getInt("SUM(PURCHASE_PRICE)");
				System.out.printf("%s\t%5d만원\n", category, sum / 10000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end sumPurchasePriceByCategory

	// - 최근 수정된 장비 5개 정보 출력
	public void getRecentlyUpdatedEquipments(ResultSet rs) {
		try {
			System.out.println("장비ID\t이름\t               담당자\t상태\t   최종수정일");
			System.out.println("---------------------------------------------------------------");
			while (rs.next()) {
				int equipmentId = rs.getInt("equipment_id");
				String equipmentName = rs.getString("equipment_name");
				int manager = rs.getInt("manager_id");
				String status = rs.getString("status");
				Date lastUpdatedDate = rs.getDate("last_updated_date");

				System.out.printf("%-7d\t%-15s\t%d\t%-5s\t   %s\n", equipmentId, equipmentName, manager, status,
						lastUpdatedDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end getRecentlyUpdatedEquipments
	
	// - 모든 카테고리 출력
	public void findAllCategories(ResultSet rs) {
		try {
			System.out.println("카테고리번호     카테고리이름\t        코드");
			System.out.println("-----------------------------------");
			while (rs.next()) {
				int categoryId = rs.getInt("CATEGORY_ID");
				String categoryName = rs.getString("CATEGORY_NAME");
				String categoryCode = rs.getString("CATEGORY_CODE");

				System.out.printf("%-5d\t      %-10s\t%s\n", categoryId, categoryName, categoryCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end findAllCategories
}
