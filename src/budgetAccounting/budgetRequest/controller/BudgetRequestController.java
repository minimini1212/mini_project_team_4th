package budgetAccounting.budgetRequest.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import budgetAccounting.budgetRequest.model.entity.BudgetRequest;
import budgetAccounting.budgetRequest.model.service.BudgetRequestService;

public class BudgetRequestController {

	private BudgetRequestService budgetRequestservice;

	public BudgetRequestController(Connection conn) {
		this.budgetRequestservice = new BudgetRequestService(conn);
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("\n==== 예산 신청 관리 ====");
			System.out.println("1. 예산 신청 등록");
			System.out.println("2. 예산 신청 전체 조회");
			System.out.println("3. 특정 예산 신청 조회");
			System.out.println("4. 예산 신청 승인 및 예산 등록");
			System.out.println("5. 예산 신청 수정");
			System.out.println("6. 예산 신청 삭제");

			System.out.println("0. 종료");
			System.out.print("선택: ");
			int choice = sc.nextInt();

			try {
				switch (choice) {
				case 1:
					createRequest(sc);
					break;
				case 2:
					listRequests();
					break;
				case 3:
					findOneRequest(sc);
					break;
				case 4:
					approveRequest(sc);
					break;
				case 5:
					updateRequest(sc);
					break;
				case 6:
					softDeleteRequest(sc);
					break;
				case 0:
					running = false;
					break;
				default:
					System.out.println("잘못된 입력입니다.");
				}
			} catch (SQLException e) {
				System.err.println("오류: " + e.getMessage());
			}
		}

		sc.close();
	}

	// 예산 신청 생성
	private void createRequest(Scanner sc) throws SQLException {
		System.out.print("부서 ID: ");
		int deptId = sc.nextInt();
		System.out.print("연도: ");
		int year = sc.nextInt();
		System.out.print("요청 금액: ");
		int amount = sc.nextInt();
		System.out.print("카테고리 ID: ");
		int categoryId = sc.nextInt();
		sc.nextLine(); // flush newline
		System.out.print("설명: ");
		String description = sc.nextLine();
		System.out.print("신청자 ID: ");
		int requesterId = sc.nextInt();

		BudgetRequest request = new BudgetRequest();
		request.setDepartmentId(deptId);
		request.setYear(year);
		request.setRequestedAmount(amount);
		request.setCategoryId(categoryId);
		request.setDescription(description);
		request.setRequesterId(requesterId);

		budgetRequestservice.createBudgetRequest(request);
		System.out.println("예산 신청이 완료되었습니다.");
	}

	// 전체 예산 신청 조회
	private void listRequests() throws SQLException {
		List<BudgetRequest> requests = budgetRequestservice.getAllBudgetRequests();
		System.out.println("\n[전체 예산 신청 목록]");
		for (BudgetRequest br : requests) {
			System.out.println(br);
		}
	}

	// 특정 예산 신청 조회
	private void findOneRequest(Scanner sc) throws SQLException {
		System.out.print("조회할 신청 ID: ");
		int requestId = sc.nextInt();
		List<BudgetRequest> requests = budgetRequestservice.getBudgetRequestById(requestId);
		for (BudgetRequest br : requests) {
			System.out.println(br);
		}
	}

	// 예산 신청 승인
	private void approveRequest(Scanner sc) throws SQLException {
		System.out.print("승인할 신청 ID: ");
		int requestId = sc.nextInt();
		System.out.print("승인자 ID: ");
		int approverId = sc.nextInt();

		budgetRequestservice.approveAndInsertToBudget(requestId, approverId);
		System.out.println("승인 및 예산 등록이 완료되었습니다.");
	}

	// 예산 신청 수정
	private void updateRequest(Scanner sc) throws SQLException {
		System.out.print("수정할 신청 ID: ");
		int requestId = sc.nextInt();
		System.out.print("요청 금액: ");
		int amount = sc.nextInt();
		sc.nextLine();
		System.out.print("설명: ");
		String description = sc.nextLine();

		BudgetRequest request = new BudgetRequest();
		request.setBudgetRequestId(requestId);
		request.setRequestedAmount(amount);
		request.setDescription(description);

		budgetRequestservice.updateBudgetRequest(request);
		System.out.println("예산 신청이 수정되었습니다.");
	}

	// 예산 신청 소프트딜리트
	private void softDeleteRequest(Scanner sc) throws SQLException {
		System.out.print("삭제할 신청 ID: ");
		int requestId = sc.nextInt();
		budgetRequestservice.softDeleteBudgetRequest(requestId);
		System.out.println("예산 신청이 소프트 삭제되었습니다.");
	}

}
