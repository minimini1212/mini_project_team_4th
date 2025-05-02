package view;

import dbConn.ConnectionSingletonHelper;
import hr.department.model.dao.DepartmentDao;
import hr.employee.model.dao.EmployeeDao;
import hr.employee.model.entity.Employee;
import hr.position.model.dao.PositionDao;
import hr.userAccount.model.dao.UserAccountDao;
import hr.userAccount.model.service.UserAccountService;
import stockManagement.view.StockManagementView;

import java.sql.Connection;
import java.util.Scanner;

// 최상위 View
public class MainEntry {
	public static void main(String[] args) {
		try {
			Connection conn = ConnectionSingletonHelper.getConnection("oracle");

			// DAO 생성
			EmployeeDao employeeDao = new EmployeeDao(conn);
			DepartmentDao departmentDao = new DepartmentDao(conn);
			PositionDao positionDao = new PositionDao(conn);
			UserAccountDao userAccountDao = new UserAccountDao(conn);

			// 서비스 생성
			UserAccountService authService = new UserAccountService(userAccountDao, employeeDao, departmentDao);

			Scanner scanner = new Scanner(System.in);
			while (true) {
				System.out.println("\n====== 병원 관리 시스템 ======");
				System.out.println("1. 로그인");
				System.out.println("2. 회원가입");
				System.out.println("0. 종료");
				System.out.print("선택: ");

				int choice = Integer.parseInt(scanner.nextLine());
				switch (choice) {
					case 1 -> {
						System.out.print("사번(empId): ");
						String id = scanner.nextLine();
						System.out.print("비밀번호: ");
						String pw = scanner.nextLine();

						Employee emp = authService.login(id, pw);
						if (emp != null) {
							System.out.println("로그인 성공! 안녕하세요." + emp.getName() + "님");

							int rankOrder = positionDao.findRankOrderByPositionId(emp.getPositionId());
							int deptId = emp.getDepartmentId();

							String role = null;
							if (rankOrder >= 1 && rankOrder <= 4) {
								role = "admin";
							} else {
								role = "basic";
							}

							if (rankOrder == 1) {
								System.out.println("1. 인사 관리 부서");
								System.out.println("2. 예산/회계 관리 부서");
								System.out.println("3. 자산 관리 부서");

								System.out.print("선택: ");
								int choice2 = scanner.nextInt();
								switch (choice2) {
									case 1 -> {	// 인사 관리 부서
										// TODO: HRMenu 클래스 생성 후 동일하게 위임
									}
									case 2 -> {	// "예산/회계관리부서"
										// TODO: FinanceMenu 클래스 생성 후 동일하게 위임
									}
									case 3 -> {	// "자산관리부서"
										StockManagementView stockManagementView = new StockManagementView(conn);
										stockManagementView.run(scanner, role, emp);
									}
									default -> System.out.println("⚠ 알 수 없는 부서입니다.");
								}
							}

							// 유저 정보 및 권한에 따라 해당 부서 view 호출
							switch (deptId) {
								case 1 -> {	// 인사 관리 부서
									// TODO: HRMenu 클래스 생성 후 동일하게 위임
								}
								case 2 -> {	// "예산/회계관리부서"
									// TODO: FinanceMenu 클래스 생성 후 동일하게 위임
								}
								case 3 -> {	// "자산관리부서"
									StockManagementView stockManagementView = new StockManagementView(conn);
									stockManagementView.run(scanner, role, emp);
								}
								default -> System.out.println("⚠ 알 수 없는 부서입니다.");
							}
						} else {
							System.out.println("로그인 실패. 정보가 일치하지 않습니다.");
						}
					}
					case 2 -> authService.register();
					case 0 -> {
						System.out.println("프로그램 종료");
						return;
					}
					default -> System.out.println("잘못된 입력입니다.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}