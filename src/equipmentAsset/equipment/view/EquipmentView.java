package equipmentAsset.equipment.view;

import java.sql.Date;
import java.sql.ResultSet;

public class EquipmentView {

	/** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 사용 메소드 =-=-=-=-=-=-=-=-=-=-=-= **/

	public void equipmentAdminMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌  𝙈 𝘼 𝙉 𝘼 𝙂 𝙀  ▌║▌║█│ ▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 장비 정보 조회");
		System.out.println("2️⃣ 장비 정보 등록");
		System.out.println("3️⃣ 장비 정보 수정");
		System.out.println("4️⃣ 장비 정보 삭제");
		System.out.println("5️⃣ 장비 현황 대시보드");
		System.out.println("6️⃣ 카테고리 관리");
		System.out.println("7️⃣ 장비 폐기");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void equipmentUserMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌  𝙈 𝘼 𝙉 𝘼 𝙂 𝙀  ▌║▌║█│ ▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 장비 정보 조회");
		System.out.println("2️⃣ 장비 정보 수정");
		System.out.println("3️⃣ 장비 현황 대시보드");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void findEquipmentMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝙎 𝙀 𝘼 𝙍 𝘾 𝙃   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 모든 장비 목록 조회");
		System.out.println("2️⃣ 특정 ID 장비 조회");
		System.out.println("3️⃣ 특정 상태 장비 조회");
		System.out.println("4️⃣ 특정 부서 장비 조회");
		System.out.println("5️⃣ 특정 카테고리 장비 조회");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void createEquipmentMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌    𝙄 𝙉 𝙋 𝙐 𝙏    ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("1️⃣ 구매정보 입력");
		System.out.println("2️⃣ 카테고리 입력");
		System.out.println("3️⃣ 담당자 입력");
		System.out.println("4️⃣ 상태 및 추가 설명 입력");
		System.out.println("5️⃣ 나중에 입력하기");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void inputStatusAndDescriptionMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝙎 𝙏 𝘼 𝙏 𝙐 𝙎   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("1️⃣ 정상");
		System.out.println("2️⃣ 점검필요");
		System.out.println("3️⃣ 수리필요");
		System.out.println("4️⃣ 수리중");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void saveEquipmentMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝙍 𝙀 𝙂 𝙄 𝙎 𝙏   ▌ ║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 신규 장비 등록");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void updateEquipmentMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝙐 𝙋 𝘿 𝘼 𝙏 𝙀   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 장비 정보 수정");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void deleteEquipmentMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝘿 𝙀 𝙇 𝙀 𝙏 𝙀   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 장비 삭제");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void showEquipmentDashboardMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌   𝘿𝘼𝙎𝙃𝘽𝙊𝘼𝙍𝘿   ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 상태별 장비 개수 조회");
		System.out.println("2️⃣ 부서별 장비 개수 조회");
		System.out.println("3️⃣ 카테고리별 장비 개수 조회");
		System.out.println("4️⃣ 최근 수정된 장비 조회");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void manageCategoryMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌ 𝘾 𝘼 𝙏 𝙀 𝙂 𝙊 𝙍 𝙔 ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 모든 카테고리 조회");
		System.out.println("2️⃣ 신규 카테고리 추가");
		System.out.println("3️⃣ 기존 카테고리 삭제");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void inputDepartment() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌     𝘿 𝙀 𝙋 𝙏     ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("1️⃣ 인사 관리 부서");
		System.out.println("2️⃣ 예산/회계 관리 부서");
		System.out.println("3️⃣ 자산 관리 부서");
		System.out.println();
		System.out.print("⏩ ");
	}

	// 폐기 메뉴 화면
	public void disposeEquipmentMenu() {
		System.out.println();
		System.out.println("  ▌│█║▌║▌ 𝘿 𝙄 𝙎 𝙋 𝙊 𝙎 𝘼 𝙇 ▌║▌║█│▌");
		System.out.println("▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 폐기할 장비 선택");
		System.out.println();
		System.out.print("⏩ ");
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
