package main.view;

import dbConn.ConnectionSingletonHelper;
import humanResource.department.model.dao.DepartmentDao;
import humanResource.employee.model.dao.EmployeeDao;
import humanResource.employee.model.entity.Employee;
import humanResource.position.model.dao.PositionDao;
import humanResource.userAccount.controller.UserAccountController;
import humanResource.userAccount.model.dao.UserAccountDao;
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

			PositionDao positionDao = new PositionDao(conn);

			UserAccountService userAccountService = new UserAccountService(conn);
			UserAccountController userAccountController = new UserAccountController(scanner, userAccountService);


			userAccountController.menu();

			Employee emp = userAccountController.getLoggedInUser();
			String role = userAccountController.getUserRole();

			if (emp == null) return;

			int rankOrder = positionDao.findRankOrderByPositionId(emp.getPositionId());
			int deptId = emp.getDepartmentId();

			if (rankOrder == 1) {
				System.out.println("1. 인사 관리 부서");
				System.out.println("2. 예산/회계 관리 부서");
				System.out.println("3. 자산 관리 부서");
				System.out.print("선택: ");
				int choice2 = Integer.parseInt(scanner.nextLine());
				switch (choice2) {
					case 1 -> {
						// TODO: HRMenu 클래스 생성 후 동일하게 위임
					}
					case 2 -> {
						// TODO: FinanceMenu 클래스 생성 후 동일하게 위임
					}
					case 3 -> {
						StockManagementView stockManagementView = new StockManagementView(conn);
						stockManagementView.run(scanner);
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
						StockManagementView stockManagementView = new StockManagementView(conn);
						stockManagementView.run(scanner);
					}
					default -> System.out.println("⚠ 알 수 없는 부서입니다.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}