package budgetAccounting.budget.view;

import common.view.HospitalBannerUtils;

public class BudgetView {

//	public static void main(String[] args) {
//		System.out.println("========== 예산 회계 시스템에 오신 것을 환영합니다 ==========");
//
//		try {
//			BudgetView budgetView = new BudgetView();
//			BudgetController controller = new BudgetController(budgetView);
//			controller.run(); // 메인 메뉴 및 기능 실행
//		} catch (Exception e) {
//			System.err.println("서버 오류: f" + e.getMessage());
//		}
//	}

	public void menu() {
		System.out.println();
		HospitalBannerUtils.printBudgetBanner();
		System.out.println();
		System.out.println("0️⃣ 이전 메뉴 돌아가기");
		System.out.println("1️⃣ 예산 등록");
		System.out.println("2️⃣ 예산 전체 조회");
		System.out.println("3️⃣ 특정 예산 조회");
		System.out.println("4️⃣ 예산 수정");
		System.out.println("5️⃣ 예산 삭제");
		System.out.println();
		System.out.print("⏩ ");
	}

}