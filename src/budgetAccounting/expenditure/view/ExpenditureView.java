package budgetAccounting.expenditure.view;

import java.sql.Connection;
import java.sql.SQLException;

import budgetAccounting.expenditure.controller.ExpenditureController;
import dbConn.ConnectionHelper;

public class ExpenditureView {
	public static void main(String[] args) {
		System.out.println("========== 예산 회계 시스템에 오신 것을 환영합니다 ==========");

		try (Connection conn = ConnectionHelper.getConnection("oracle");) {
			ExpenditureView expenditureView = new ExpenditureView();
			ExpenditureController controller = new ExpenditureController(conn, expenditureView);
			controller.run(); // 메인 메뉴 및 기능 실행
		} catch (SQLException e) {
			System.err.println("데이터베이스 연결 실패: " + e.getMessage());
		}
	}

	// 예산 관리 메뉴
	public void menu() {
		System.out.println("\n==== 예산 신청 관리 ====");
		System.out.println("1. 지출 등록");
		System.out.println("2. 지출 전체 조회");
		System.out.println("3. 특정 지출 조회");
		System.out.println("4. 지출 수정");
		System.out.println("5. 지출 삭제");
		System.out.println("0. 종료");
		System.out.print("선택: ");
	}
}
