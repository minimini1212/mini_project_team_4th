package budgetAccounting.budget.controller;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import budgetAccounting.budget.model.entity.Budget;
import budgetAccounting.budget.model.service.BudgetService;
import budgetAccounting.budget.view.BudgetView;

public class BudgetController {

	private BudgetService budgetservice;
	private BudgetView budgetView = new BudgetView();

	public BudgetController() {
		this.budgetservice = new BudgetService();
	}

	public void run(Scanner sc, int rankOrder) {
		boolean running = true;

		while (running) {
			budgetView.menu();
			int choice = sc.nextInt();

			try {
				switch (choice) {
				case 1:
					if (rankOrder >= 2) {
						running = false;
						return;
					}
					createBudget(sc);
					break;
				case 2:
					listRequests();
					break;
				case 3:
					findOneRequest(sc);
					break;
				case 4:
					if (rankOrder >= 2) {
						running = false;
						return;
					}
					updateRequest(sc);
					break;
				case 5:
					if (rankOrder >= 2) {
						running = false;
						return;
					}
					softDeleteRequest(sc);
					break;
				case 0:
					
					return;
				default:
					System.out.println("잘못된 입력입니다.");
				}
			} catch (SQLException e) {
				System.err.println("오류: " + e.getMessage());
			}
		}

		sc.close();
	}

	// 예산 생성
	private void createBudget(Scanner sc) throws SQLException {
		while (true) {
			try {
				System.out.print("부서 ID (자산 1번, 인사 2번, 회계 3번): ");
				int deptId = sc.nextInt();
				System.out.print("연도를 입력하세요(4자리): ");
				int year = sc.nextInt();
				System.out.print("예산 금액을 입력하세요: ");
				int amount = sc.nextInt();
				System.out.print("카테고리 ID (인건비 1번, 수리비 2번, 소모품비 3번, 출장비 4번, 운영비 5번): ");
				int categoryId = sc.nextInt();
				sc.nextLine();
				System.out.print("설명: ");
				String description = sc.nextLine();

				Budget request = new Budget();
				request.setDepartmentId(deptId);
				request.setYear(year);
				request.setBudgetAmount(amount);
				request.setCategoryId(categoryId);
				request.setDescription(description);
				request.setRemainingBudget(amount);

				budgetservice.createBudget(request);

				break;

			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("해당 부서에 이미 동일한 항목이 존재합니다.");
			} catch (SQLException e) {
				System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("예산 등록 중 오류가 발생했습니다. ");
				sc.nextLine();
			}
		}
	}

	// 전체 예산 조회
	private void listRequests() throws SQLException {
		List<Budget> requests = budgetservice.getAllBudget();
		System.out.println("\n[전체 예산 신청 목록]");
		for (Budget br : requests) {
			System.out.println(br);
		}
	}

	// 특정 예산 조회
	private void findOneRequest(Scanner sc) throws SQLException {

		while (true) {
			try {
				System.out.print("조회할 예산 ID: ");
				int requestId = sc.nextInt();
				List<Budget> requests = budgetservice.getBudgetById(requestId);
				for (Budget br : requests) {
					System.out.println(br);
				}
				break;

			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLException e) {
				if ("해당 조건에 맞는 지출 신청이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("예산 등록 중 오류가 발생했습니다.");
				sc.nextLine();
			}
		}

	}

	// 예산 수정
	private void updateRequest(Scanner sc) throws SQLException {

		while (true) {
			try {
				System.out.print("수정할 예산 ID: ");
				int requestId = sc.nextInt();
				System.out.print("예산 금액: ");
				int amount = sc.nextInt();
				sc.nextLine();
				System.out.print("설명: ");
				String description = sc.nextLine();

				Budget request = new Budget();
				request.setBudgetRequestId(requestId);
				request.setBudgetAmount(amount);
				request.setDescription(description);

				budgetservice.updateBudget(request, requestId);

				break;

			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLException e) {
				if ("해당 조건에 맞는 지출 신청이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("예산 등록 중 오류가 발생했습니다: ");
				sc.nextLine();
			}
		}

	}

	// 예산 소프트딜리트
	private void softDeleteRequest(Scanner sc) throws SQLException {

		while (true) {
			try {
				System.out.print("삭제할 예산 ID: ");
				int requestId = sc.nextInt();
				budgetservice.softDeleteBudget(requestId);

				break;

			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLException e) {
				if ("해당 조건에 맞는 지출 신청이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("예산 등록 중 오류가 발생했습니다: ");
				sc.nextLine();
			}
		}

	}

}