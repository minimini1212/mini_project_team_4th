package equipmentAsset.equipment.view;

import common.view.HospitalBannerUtils;

import java.sql.Date;
import java.sql.ResultSet;

public class EquipmentView {

	/** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 사용 메소드 =-=-=-=-=-=-=-=-=-=-=-= **/

	public void equipmentAdminMenu() {
		System.out.println();
		HospitalBannerUtils.printManageBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 장비 정보 조회");
		System.out.println();
		System.out.println("2️⃣ 장비 정보 등록");
		System.out.println();
		System.out.println("3️⃣ 장비 정보 수정");
		System.out.println();
		System.out.println("4️⃣ 장비 정보 삭제");
		System.out.println();
		System.out.println("5️⃣ 장비 현황 대시보드");
		System.out.println();
		System.out.println("6️⃣ 카테고리 관리");
		System.out.println();
		System.out.println("7️⃣ 장비 폐기");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void equipmentUserMenu() {
		System.out.println();
		HospitalBannerUtils.printManageBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 장비 정보 조회");
		System.out.println();
		System.out.println("2️⃣ 장비 정보 수정");
		System.out.println();
		System.out.println("3️⃣ 장비 현황 대시보드");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void findEquipmentMenu() {
		System.out.println();
		HospitalBannerUtils.printSearchBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 모든 장비 목록 조회");
		System.out.println();
		System.out.println("2️⃣ 특정 ID 장비 조회");
		System.out.println();
		System.out.println("3️⃣ 특정 상태 장비 조회");
		System.out.println();
		System.out.println("4️⃣ 특정 부서 장비 조회");
		System.out.println();
		System.out.println("5️⃣ 특정 카테고리 장비 조회");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void createEquipmentMenu() {
		System.out.println();
		HospitalBannerUtils.printInputBanner();
		System.out.println("1️⃣ 구매정보 입력");
		System.out.println();
		System.out.println("2️⃣ 카테고리 입력");
		System.out.println();
		System.out.println("3️⃣ 담당자 입력");
		System.out.println();
		System.out.println("4️⃣ 상태 및 추가 설명 입력");
		System.out.println();
		System.out.println("5️⃣ 나중에 입력하기");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void inputStatusAndDescriptionMenu() {
		System.out.println();
		HospitalBannerUtils.printStatusBanner();
		System.out.println("1️⃣ 정상");
		System.out.println();
		System.out.println("2️⃣ 점검필요");
		System.out.println();
		System.out.println("3️⃣ 수리필요");
		System.out.println();
		System.out.println("4️⃣ 수리중");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void saveEquipmentMenu() {
		System.out.println();
		HospitalBannerUtils.printRegisterBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 신규 장비 등록");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void updateEquipmentMenu() {
		System.out.println();
		HospitalBannerUtils.printUpdateBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 장비 정보 수정");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void deleteEquipmentMenu() {
		System.out.println();
		HospitalBannerUtils.printDeleteBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 장비 삭제");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void showEquipmentDashboardMenu() {
		System.out.println();
		HospitalBannerUtils.printDashboardBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 상태별 장비 개수 조회");
		System.out.println();
		System.out.println("2️⃣ 부서별 장비 개수 조회");
		System.out.println();
		System.out.println("3️⃣ 카테고리별 장비 개수 조회");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void manageCategoryMenu() {
		System.out.println();
		HospitalBannerUtils.printCategoryBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 모든 카테고리 조회");
		System.out.println();
		System.out.println("2️⃣ 신규 카테고리 추가");
		System.out.println();
		System.out.println("3️⃣ 기존 카테고리 삭제");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void inputDepartment() {
		System.out.println();
		HospitalBannerUtils.printDeptBanner();
		System.out.println("1️⃣ 인사 관리 부서");
		System.out.println();
		System.out.println("2️⃣ 예산/회계 관리 부서");
		System.out.println();
		System.out.println("3️⃣ 자산 관리 부서");
		System.out.println();
		System.out.print("⏩ ");
	}

	// 폐기 메뉴 화면
	public void disposeEquipmentMenu() {
		System.out.println();
		HospitalBannerUtils.printDisposalBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 폐기할 장비 선택");
		System.out.println();
		System.out.print("⏩ ");
	}




	/** =-=-=-=-=-=-=-=-=-=-=-= DAO 사용 메소드 =-=-=-=-=-=-=-=-=-=-=-= **/

	// - 상태별 장비 개수 출력
	public void countByStatus(ResultSet rs) {
		try {
			System.out.println("\n" +
					"━━━━━━  📊 \033[1;36m상태별 장비 통계\033[0m 📊 ━━━━\n");
			System.out.printf("  \033[1;34m%-12s\033[0m\t\033[1;34m%-10s\033[0m\n", "상태", "개수");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━");

			while (rs.next()) {
				String status = rs.getString("STATUS");
				int count = rs.getInt("COUNT(*)");
				String statusColor = getStatusColor(status);
				System.out.printf("  \033[1;%sm%-12s\033[0m\t\033[0;97m%d개\033[0m\n",
						statusColor, status, count);
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end countByStatus

	// 상태에 따른 색상 코드를 반환하는 메서드
	private String getStatusColor(String status) {
		if (status == null) return "37"; // 기본 흰색

		switch (status.toUpperCase()) {
			case "정상":
				return "32"; // 녹색
			case "점검필요":
				return "33"; // 노란색
			case "수리필요":
			case "수리중":
				return "31"; // 빨간색
			case "폐기예정":
				return "35"; // 자주색
			case "폐기완료":
				return "90"; // 회색
			case "대기중":
			case "PENDING":
				return "33"; // 노란색
			case "승인됨":
			case "APPROVED":
				return "32"; // 녹색
			case "거부됨":
			case "REJECTED":
				return "31"; // 빨간색
			default:
				return "37"; // 기본 흰색
		}
	}

	// - 카테고리별 장비 개수 출력
	public void countByCategory(ResultSet rs) {
		try {
			System.out.println("\n" +
					"━━━━━  🏷️ \033[1;36m카테고리별 장비 통계\033[0m 🏷️ ━━━\n");
			System.out.printf("  \033[1;34m%-15s\033[0m\t\033[1;34m%-10s\033[0m\n", "카테고리", "개수");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━");

			while (rs.next()) {
				String category = rs.getString("CATEGORY_NAME");
				int count = rs.getInt("COUNT(*)");
				System.out.printf("  \033[0;97m%-15s\033[0m\t\033[0;97m%d개\033[0m\n",
						category, count);
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end countByCategory

	// - 부서별 장비 개수 출력
	public void countByDepartment(ResultSet rs) {
		try {
			System.out.println("\n" +
					"━━━━━━  🏢 \033[1;36m부서별 장비 통계\033[0m 🏢 ━━━━\n");
			System.out.printf("  \033[1;34m%-15s\033[0m\t\033[1;34m%-10s\033[0m\n", "부서", "개수");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━");

			while (rs.next()) {
				String departmentName = rs.getString("DEPARTMENT_NAME");
				int count = rs.getInt("COUNT(*)");
				System.out.printf("  \033[0;97m%-15s\033[0m\t\033[0;97m%d개\033[0m\n",
						departmentName, count);
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end countByDepartment


	// - 모든 카테고리 출력
	public void findAllCategories(ResultSet rs) {
		try {
			System.out.println("\n" +
					"━━━━━━  🏷️ \033[1;36m카테고리 목록\033[0m 🏷️ ━━━━━\n");
			System.out.printf("  \033[1;34m%-5s\033[0m\t\033[1;34m%-15s\033[0m\t\033[1;34m%-10s\033[0m\n",
					"ID", "카테고리명", "코드");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

			while (rs.next()) {
				int categoryId = rs.getInt("CATEGORY_ID");
				String categoryName = rs.getString("CATEGORY_NAME");
				String categoryCode = rs.getString("CATEGORY_CODE");

				System.out.printf("  \033[0;97m%-5d\033[0m\t\033[0;97m%-15s\033[0m\t\033[0;97m%-10s\033[0m\n",
						categoryId, categoryName, categoryCode);
			}
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end findAllCategories
}
