package budgetAccounting.budget.view;

import budgetAccounting.budget.controller.BudgetController;

public class BudgetView {

	public static void main(String[] args) {
		System.out.println("========== 예산 회계 시스템에 오신 것을 환영합니다 ==========");

		try {
			BudgetView budgetView = new BudgetView();
			BudgetController controller = new BudgetController(budgetView);
			controller.run(); // 메인 메뉴 및 기능 실행
		} catch (Exception e) {
			System.err.println("서버 오류: f" + e.getMessage());
		}
	}
}