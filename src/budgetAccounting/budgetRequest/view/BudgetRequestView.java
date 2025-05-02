package budgetAccounting.budgetRequest.view;

import java.sql.Connection;
import java.sql.SQLException;

import budgetAccounting.budgetRequest.controller.BudgetRequestController;
import dbConn.ConnectionHelper;

public class BudgetRequestView {

    public static void main(String[] args) {
        System.out.println("========== 예산 회계 시스템에 오신 것을 환영합니다 ==========");

        try (Connection conn = ConnectionHelper.getConnection("oracle");) {
            BudgetRequestController controller = new BudgetRequestController(conn);
            controller.run(); // 메인 메뉴 및 기능 실행
        } catch (SQLException e) {
            System.err.println("데이터베이스 연결 실패: " + e.getMessage());
        }
    }
}
