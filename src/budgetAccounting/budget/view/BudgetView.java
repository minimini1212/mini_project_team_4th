package budgetAccounting.budget.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import budgetAccounting.budget.controller.BudgetController;
import budgetAccounting.budgetRequest.controller.BudgetRequestController;
import dbConn.ConnectionHelper;

public class BudgetView {

	public static void main(String[] args) {
		System.out.println("========== 예산 회계 시스템에 오신 것을 환영합니다 ==========");

		try (Connection conn = ConnectionHelper.getConnection("oracle");) {
			BudgetView budgetView = new BudgetView();
			BudgetController controller = new BudgetController(conn, budgetView);
			controller.run(); // 메인 메뉴 및 기능 실행
		} catch (SQLException e) {
			System.err.println("데이터베이스 연결 실패: " + e.getMessage());
		}
	}

	// 예산 관리 메뉴
	public void menu() {
		System.out.println("\n==== 예산 신청 관리 ====");
		System.out.println("1. 예산 등록");
		System.out.println("2. 예산 전체 조회");
		System.out.println("3. 특정 예산 조회");
		System.out.println("4. 예산 수정");
		System.out.println("5. 예산 삭제");
		System.out.println("0. 종료");
		System.out.print("선택: ");
	}
}