package budgetAccounting.expenditure.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import budgetAccounting.expenditure.model.entity.Expenditure;
import budgetAccounting.expenditure.model.service.ExpenditureService;
import budgetAccounting.expenditure.view.ExpenditureView;

public class ExpenditureController {
	private ExpenditureService expenditureservice;
	private ExpenditureView expenditureView;

	public ExpenditureController(Connection conn, ExpenditureView expenditureView) {
		this.expenditureservice = new ExpenditureService(conn);
		this.expenditureView = expenditureView;
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean running = true;

		while (running) {
			expenditureView.menu();
			int choice = sc.nextInt();

			try {
				switch (choice) {
				case 1:
					createExpenditure(sc);
					break;
				case 2:
					listRequests();
					break;
				case 3:
					findOneRequest(sc);
					break;
				case 4:
					updateRequest(sc);
					break;
				case 5:
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

	// 지출 생성
	private void createExpenditure(Scanner sc) throws SQLException {
		while (true) {
			try {
				sc.nextLine(); // 개행 제거
				System.out.print("부서 ID (인사, 자산, 회계): ");
				int deptId = Integer.parseInt(sc.nextLine());
				System.out.print("연도: ");
				int year = Integer.parseInt(sc.nextLine());
				System.out.print("지출 금액: ");
				int amount = Integer.parseInt(sc.nextLine());
				System.out.print("카테고리 ID: ");
				int categoryId = Integer.parseInt(sc.nextLine());

				System.out.print("지출일을 입력하세요 (예: 2025-05-03): ");

				String inputDate = sc.nextLine();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(inputDate);
				
				System.out.print("설명: ");
				String description = sc.nextLine();

				Expenditure expenditure = new Expenditure();
				expenditure.setDepartmentId(deptId);
				expenditure.setYear(year);
				expenditure.setAmount(amount);
				expenditure.setCategoryId(categoryId);
				expenditure.setExpenditureDate(date);
				expenditure.setDescription(description);

				expenditureservice.createExpenditure(expenditure);
				System.out.println("지출이 등록되었습니다.");
				break;

			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLException e) {
				System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요."+ e.getMessage());
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("지출 등록 중 오류가 발생했습니다. " + e.getMessage());
				sc.nextLine();
			}
		}
	}

	// 전체 지출 조회
	private void listRequests() throws SQLException {
		List<Expenditure> expenditures = expenditureservice.getAllExpenditure();
		System.out.println("\n[전체 지출 신청 목록]");
		for (Expenditure br : expenditures) {
			System.out.println(br);
		}
	}

	// 특정 지출 조회
	private void findOneRequest(Scanner sc) throws SQLException {

		while (true) {
			try {
				System.out.print("조회할 지출 ID: ");
				int requestId = sc.nextInt();
				List<Expenditure> expenditure = expenditureservice.getExpenditureById(requestId);
				for (Expenditure br : expenditure) {
					System.out.println(br);
				}
				break;

			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLException e) {
				System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("지출 조회 중 오류가 발생했습니다.");
				sc.nextLine();
			}
		}

	}

	// 지출 수정
	private void updateRequest(Scanner sc) throws SQLException {

		while (true) {
			try {
				System.out.print("수정할 지출 ID: ");
				int requestId = sc.nextInt();
				System.out.print("지출 금액: ");
				int amount = sc.nextInt();
				sc.nextLine();
				System.out.print("설명: ");
				String description = sc.nextLine();

				Expenditure expenditure = new Expenditure();
				expenditure.setExpenditureId(requestId);
				expenditure.setAmount(amount);
				expenditure.setDescription(description);

				expenditureservice.updateExpenditure(expenditure);
				System.out.println("지출이 수정되었습니다.");
				break;

			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (Exception e) {
				System.out.println("지출 수정 중 오류가 발생했습니다: ");
				sc.nextLine();
			}
		}

	}

	// 지출 소프트딜리트
	private void softDeleteRequest(Scanner sc) throws SQLException {

		while (true) {
			try {
				System.out.print("삭제할 예산 ID: ");
				int requestId = sc.nextInt();
				expenditureservice.softDeleteExpenditure(requestId);
				System.out.println("지출이 소프트 삭제되었습니다.");
				break;

			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLException e) {
				System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("지출 삭제 중 오류가 발생했습니다: ");
				sc.nextLine();
			}
		}

	}
}
