package budgetAccounting.budgetRequest.controller;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import budgetAccounting.budgetRequest.model.entity.BudgetRequest;
import budgetAccounting.budgetRequest.model.service.BudgetRequestService;
import budgetAccounting.budgetRequest.view.BudgetRequestView;

public class BudgetRequestController {

	private BudgetRequestService budgetRequestservice;
	private BudgetRequestView budgetRequestView = new BudgetRequestView();

	public BudgetRequestController() {
		this.budgetRequestservice = new BudgetRequestService();
	}

	public void run(Scanner sc, int rankOrder) {
		boolean running = true;

		while (running) {
			budgetRequestView.menu();
			int choice = sc.nextInt();
			sc.nextLine();
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
					if (rankOrder >= 3) {
						System.out.println("해당 기능에 대한 권한이 없습니다.");
						running = false;
						return;
					}
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

	// 예산 신청 생성
	private void createRequest(Scanner sc) throws SQLException {

		while (true) {
			try {
				System.out.println();
				System.out.println("━━━━━━━ 생성 정보 등록 ━━━━━━━");
				System.out.println();
				System.out.println("⏩ 부서 ID (인사 2번, 예산/회계 3번, 자산 4번)");
				System.out.print("⏩ 입력: ");
				int deptId = Integer.parseInt(sc.nextLine());
				
				System.out.println();
				System.out.println("⏩ 연도를 입력하세요(4자리)");
				System.out.print("⏩ 입력: ");
				int year = Integer.parseInt(sc.nextLine());
				
				System.out.println();
				System.out.println("⏩ 예산 신청 금액을 입력하세요");
				System.out.print("⏩ 입력: ");
				int amount = Integer.parseInt(sc.nextLine());
				
				System.out.println();
				System.out.println("⏩ 카테고리 ID (인건비 1번, 수리비 2번, 소모품비 3번, 출장비 4번, 운영비 5번)");
				System.out.print("⏩ 입력: ");
				int categoryId = Integer.parseInt(sc.nextLine());
				
				System.out.println("⏩ 설명");
				System.out.print("⏩ 입력: ");
				String description = sc.nextLine();
				System.out.println();
				System.out.println("━━━━━━━ 생성 정보 등록 ━━━━━━━");

				BudgetRequest request = new BudgetRequest();
				request.setDepartmentId(deptId);
				request.setYear(year);
				request.setRequestedAmount(amount);
				request.setCategoryId(categoryId);
				request.setDescription(description);

				budgetRequestservice.createBudgetRequest(request);

				break;

			} catch (InputMismatchException e) {
				System.out.println("❌올바르게 입력해주세요.");
				sc.nextLine();
			} catch (IllegalArgumentException e) {
				if ("❌존재하지 않는 부서 ID입니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					sc.nextLine();
					break;
				} else if ("❌존재하지 않는 항목 ID입니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					sc.nextLine();
					break;
				} else if ("❌연도는 4자리로 입력해주세요.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					sc.nextLine();
					break;
				} else {
					System.out.println("❌알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("❌해당 부서에 이미 동일한 항목이 존재합니다.");
				sc.nextLine();
			} catch (SQLException e) {
				System.out.println("❌알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("❌예산 신청 등록 중 오류가 발생했습니다. " + e.getMessage());
				sc.nextLine();
			}
		}

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

		while (true) {
			try {
				System.out.print("조회할 신청 ID: ");
				int requestId = sc.nextInt();
				List<BudgetRequest> requests = budgetRequestservice.getBudgetRequestById(requestId);
				for (BudgetRequest br : requests) {
					System.out.println(br);
				}
				break;

			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLException e) {
				if ("해당 조건에 맞는 예산 신청이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("예산 신청 조회 중 오류가 발생했습니다. ");
				sc.nextLine();
			}
		}

	}

	// 예산 신청 승인
	private void approveRequest(Scanner sc) throws SQLException {

		while (true) {
			try {

				System.out.print("승인할 신청 ID: ");
				int requestId = sc.nextInt();
				sc.nextLine();

				budgetRequestservice.approveAndInsertToBudget(requestId);
				break;

			} catch (InputMismatchException e) {
				System.out.println("❌올바르게 입력해주세요.");
				sc.nextLine();

			} catch (IllegalArgumentException e) {
				System.out.println("❌신청자와 승인자는 같을 수 없습니다.");
				break;

			} catch (SQLException e) {
				if ("해당 조건에 맞는 예산 신청이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("❌알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("❌예산 신청 승인 중 오류가 발생했습니다. ");
				sc.nextLine();
			}
		}

	}

	// 예산 신청 수정
	private void updateRequest(Scanner sc) throws SQLException {

		while (true) {
			try {

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

				budgetRequestservice.updateBudgetRequest(request, requestId);

				break;

			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLException e) {
				if ("해당 조건에 맞는 예산 신청이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("예산 신청 수정 중 오류가 발생했습니다. ");
				sc.nextLine();
			}
		}

	}

	// 예산 신청 소프트딜리트
	private void softDeleteRequest(Scanner sc) throws SQLException {

		while (true) {
			try {

				System.out.print("삭제할 신청 ID: ");
				int requestId = sc.nextInt();
				budgetRequestservice.softDeleteBudgetRequest(requestId);

				break;

			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLException e) {
				if ("해당 조건에 맞는 예산 신청이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("예산 신청 삭제 중 오류가 발생했습니다. ");
				sc.nextLine();
			}
		}

	}

}
