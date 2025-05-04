package main.view;

import common.SessionContext;
import dbConn.ConnectionSingletonHelper;
import humanResource.employee.model.entity.Employee;
import humanResource.position.model.dao.PositionDao;
import humanResource.userAccount.controller.UserAccountController;
import humanResource.userAccount.model.service.UserAccountService;
import stockManagement.view.StockManagementView;

import java.sql.Connection;
import java.util.Scanner;

// 최상위 View
public class MainEntry {
	public static void main(String[] args) {
		try {
			Connection conn = ConnectionSingletonHelper.getConnection("oracle");
			Scanner scanner = new Scanner(System.in);

			// 로그인 및 회원가입 처리
			UserAccountController userAccountController = new UserAccountController(scanner, new UserAccountService(conn));
			userAccountController.menu();

			// 로그인 성공 시 세션 정보 확인
			if (!SessionContext.isLoggedIn()) return;

			Employee emp = SessionContext.getUser();

			int rankOrder = SessionContext.getRankOrder();
			int deptId = SessionContext.getDeptId();

			if (rankOrder == 1) {
				System.out.println("1. 인사 관리 부서");
				System.out.println("2. 예산/회계 관리 부서");
				System.out.println("3. 자산 관리 부서");
				System.out.print("선택: ");
				int choice = Integer.parseInt(scanner.nextLine());
				switch (choice) {
					case 1 -> {
						// TODO: HRMenu 클래스 생성 후 동일하게 위임
					}
					case 2 -> {
						// TODO: FinanceMenu 클래스 생성 후 동일하게 위임
					}
					case 3 -> {
//						StockManagementView stockManagementView = new StockManagementView(conn);
//						stockManagementView.run(scanner);
					}
					default -> System.out.println("⚠ 알 수 없는 부서입니다.");
				}
			} else {
				switch (deptId) {
					case 1 -> {
						// TODO: HRMenu 클래스 생성 후 동일하게 위임
					}
					case 2 -> {
						// TODO: FinanceMenu 클래스 생성 후 동일하게 위임
					}
					case 3 -> {
//						StockManagementView stockManagementView = new StockManagementView(conn);
//						stockManagementView.run(scanner);
					}
					default -> System.out.println("⚠ 알 수 없는 부서입니다.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}