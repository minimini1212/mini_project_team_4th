package equipmentAsset.inspection.view;

import java.sql.Date;
import java.sql.ResultSet;

import common.view.HospitalBannerUtils;

public class InspectionView {

	/** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 사용 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/

	public void inspectionMenu() {
		System.out.println();
		HospitalBannerUtils.printInspectionBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 점검 일정 관리");
		System.out.println();
		System.out.println("2️⃣ 점검 결과 관리");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void inspectionScheduleMenu() {
		System.out.println();
		HospitalBannerUtils.printScheduleBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 점검 일정 등록");
		System.out.println();
		System.out.println("2️⃣ 점검 일정 조회");
		System.out.println();
		System.out.println("3️⃣ 점검 일정 수정");
		System.out.println();
		System.out.println("4️⃣ 점검 일정 삭제");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void saveInspectionScheduleMenu() {
		System.out.println();
		HospitalBannerUtils.printRegisterBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 점검 일정 등록");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void findInspectionScheduleMenu() {
		System.out.println();
		HospitalBannerUtils.printSearchBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 모든 점검 일정 조회");
		System.out.println();
		System.out.println("2️⃣ 장비 번호로 조회");
		System.out.println();
		System.out.println("3️⃣ 일정 번호로 조회");
		System.out.println();
		System.out.println("4️⃣ 점검 상태별 조회");
		System.out.println();
		System.out.println("5️⃣ 점검 주기별 조회");
		System.out.println();
		System.out.println("6️⃣ 점검 유형별 조회");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void updateInspectionScheduleMenu() {
		System.out.println();
		HospitalBannerUtils.printUpdateBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 점검 일정 수정");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void deleteInspectionScheduleMenu() {
		System.out.println();
		HospitalBannerUtils.printDeleteBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 점검 일정 삭제");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void inspectionResultMenu() {
		System.out.println();
		HospitalBannerUtils.printResultsBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 점검 결과 등록");
		System.out.println();
		System.out.println("2️⃣ 점검 결과 조회");
		System.out.println();
		System.out.println("3️⃣ 점검 결과 수정");
		System.out.println();
		System.out.println("4️⃣ 점검 결과 삭제");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void saveInspectionResultMenu() {
		System.out.println();
		HospitalBannerUtils.printRegisterBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 점검 결과 등록");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void findInspectionResultMenu() {
		System.out.println();
		HospitalBannerUtils.printSearchBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 모든 점검 결과 조회");
		System.out.println();
		System.out.println("2️⃣ 장비 번호로 조회");
		System.out.println();
		System.out.println("3️⃣ 결과 번호로 조회");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void updateInspectionResultMenu() {
		System.out.println();
		HospitalBannerUtils.printUpdateBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 점검 결과 수정");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void deleteInspectionResultMenu() {
		System.out.println();
		HospitalBannerUtils.printDeleteBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 점검 결과 삭제");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void updateInspectionScheduleItemMenu() {
		System.out.println();
		HospitalBannerUtils.printUpdateBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 점검 유형 수정");
		System.out.println();
		System.out.println("2️⃣ 점검 주기 수정");
		System.out.println();
		System.out.println("3️⃣ 예정 일자 수정");
		System.out.println();
		System.out.println("4️⃣ 상태 수정");
		System.out.println();
		System.out.println("5️⃣ 설명 수정");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void updateInspectionResultItemMenu() {
		System.out.println();
		HospitalBannerUtils.printUpdateBanner();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println();
		System.out.println("1️⃣ 점검 일자 수정");
		System.out.println();
		System.out.println("2️⃣ 점검 결과 유형 수정");
		System.out.println();
		System.out.println("3️⃣ 점검 내용 수정");
		System.out.println();
		System.out.println("4️⃣ 특이사항 수정");
		System.out.println();
		System.out.print("⏩ ");
	}

	/** =-=-=-=-=-=-=-=-=-=-=-= 컨트롤러 안내 출력 =-=-=-=-=-=-=-=-=-=-=-= **/

	public void getInspectionStatus() {
		System.out.println();
		HospitalBannerUtils.printStatusBanner();
		System.out.println("1️⃣ 예정");
		System.out.println();
		System.out.println("2️⃣ 완료");
		System.out.println();
		System.out.println("3️⃣ 기타");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void getInspectionCycle() {
		System.out.println();
		HospitalBannerUtils.printCycleBanner();
		System.out.println("1️⃣ 월간");
		System.out.println();
		System.out.println("2️⃣ 분기");
		System.out.println();
		System.out.println("3️⃣ 반기");
		System.out.println();
		System.out.println("4️⃣ 연간");
		System.out.println();
		System.out.println("5️⃣ 비정기");
		System.out.println();
		System.out.println("6️⃣ 기타");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void getInspectionType() {
		System.out.println();		System.out.println();
		HospitalBannerUtils.printTypeBanner();
		System.out.println("1️⃣ 초기점검");
		System.out.println();
		System.out.println("2️⃣ 정기점검");
		System.out.println();
		System.out.println("3️⃣ 긴급점검");
		System.out.println();
		System.out.println("4️⃣ 기타");
		System.out.println();
		System.out.print("⏩ ");
	}

	public void getInspectionResultType() {
		System.out.println();
		HospitalBannerUtils.printResultsBanner();
		System.out.println("1️⃣ 양호");
		System.out.println();
		System.out.println("2️⃣ 수리필요");
		System.out.println();
		System.out.print("⏩ ");
	}

} // end class
