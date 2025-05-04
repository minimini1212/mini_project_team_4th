package budgetAccounting.expenditureRequest.view;

import java.sql.Connection;
import java.sql.SQLException;

import budgetAccounting.expenditureRequest.controller.ExpenditureRequestController;
import dbConn.ConnectionHelper;

public class ExpenditureRequestView {
	public static void main(String[] args) {
        System.out.println("========== 예산 회계 시스템에 오신 것을 환영합니다 ==========");

        try (Connection conn = ConnectionHelper.getConnection("oracle");) {
        	ExpenditureRequestController controller = new ExpenditureRequestController(conn);
            controller.run(); // 메인 메뉴 및 기능 실행
        } catch (SQLException e) {
            System.err.println("데이터베이스 연결 실패: " + e.getMessage());
        }
    }
}