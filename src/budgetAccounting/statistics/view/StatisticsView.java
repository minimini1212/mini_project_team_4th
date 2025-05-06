package budgetAccounting.statistics.view;

import budgetAccounting.statistics.controller.StatisticsController;

public class StatisticsView {
	public static void main(String[] args) {
		System.out.println("========== 예산 회계 시스템에 오신 것을 환영합니다 ==========");

		try {
			StatisticsView statisticsView = new StatisticsView();
			StatisticsController controller = new StatisticsController(statisticsView);
			controller.run(); // 메인 메뉴 및 기능 실행
		} catch (Exception e) {
			System.err.println("서버 오류: " + e.getMessage());
		}
	}

	// 예산 관리 메뉴
	public void menu() {
		System.out.println("\n==== 통계 조회 ====");
		System.out.println("1. 연도별 조회");
		System.out.println("2. 부서별 조회");
		System.out.println("3. 카테고리별 조회");
		System.out.println("0. 종료");
		System.out.print("선택: ");
	}
}
