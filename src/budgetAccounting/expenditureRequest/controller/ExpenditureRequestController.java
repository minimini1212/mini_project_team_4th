package budgetAccounting.expenditureRequest.controller;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import budgetAccounting.expenditureRequest.model.entity.ExpenditureRequest;
import budgetAccounting.expenditureRequest.model.service.ExpenditureRequestService;
import budgetAccounting.expenditureRequest.view.ExpenditureRequestView;

public class ExpenditureRequestController {
	private ExpenditureRequestService expenditureRequestservice;
	private ExpenditureRequestView expenditureRequestView = new ExpenditureRequestView();

	public ExpenditureRequestController() {
		this.expenditureRequestservice = new ExpenditureRequestService();
	}

	public void run(Scanner sc, int rankOrder) {
		boolean running = true;

		while (running) {
			expenditureRequestView.menu();
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
					if (rankOrder >= 2) {
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

	// 지출 신청 생성
	private void createRequest(Scanner sc) throws SQLException {

		while (true) {
			try {
				System.out.print("부서 ID (인사 1번, 예산/회계 2번, 자산 3번): ");
				int deptId = sc.nextInt();
				System.out.print("연도를 입력하세요(4자리): ");
				int year = sc.nextInt();
				System.out.print("지출 신청 금액을 입력하세요: ");
				int amount = sc.nextInt();
				System.out.print("카테고리 ID (인건비 1번, 수리비 2번, 소모품비 3번, 출장비 4번, 운영비 5번): ");
				int categoryId = sc.nextInt();
				sc.nextLine();
				System.out.print("설명: ");
				String description = sc.nextLine();
				System.out.print("신청자 ID: ");
				int requesterId = sc.nextInt();

				ExpenditureRequest request = new ExpenditureRequest();
				request.setDepartmentId(deptId);
				request.setYear(year);
				request.setAmount(amount);
				request.setCategoryId(categoryId);
				request.setDescription(description);
				request.setRequesterId(requesterId);

				expenditureRequestservice.createExpenditureRequest(request);
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

	// 전체 지출 신청 조회
	private void listRequests() throws SQLException {
		List<ExpenditureRequest> requests = expenditureRequestservice.getAllExpenditureRequests();
		System.out.println("\n[전체 지출 신청 목록]");
		for (ExpenditureRequest br : requests) {
			System.out.println(br);
		}
	}

	// 특정 지출 신청 조회
	private void findOneRequest(Scanner sc) throws SQLException {

		while (true) {
			try {
				System.out.print("조회할 신청 ID: ");
				int requestId = sc.nextInt();
				List<ExpenditureRequest> requests = expenditureRequestservice.getExpenditureRequestById(requestId);
				for (ExpenditureRequest br : requests) {
					System.out.println(br);
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("해당 부서에 이미 동일한 항목이 존재합니다.");
			} catch (SQLException e) {
				if ("해당 조건에 맞는 지출 신청이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("예산 등록 중 오류가 발생했습니다. ");
				sc.nextLine();
			}
		}

	}

	// 지출 신청 승인
	private void approveRequest(Scanner sc) throws SQLException {

		while (true) {
			try {
				System.out.print("승인할 신청 ID: ");
				int requestId = sc.nextInt();
				System.out.print("승인자 ID: ");
				int approverId = sc.nextInt();

				expenditureRequestservice.approveAndInsertToExpenditure(requestId, approverId);
				break;
			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("해당 부서에 이미 동일한 항목이 존재합니다.");
			} catch (SQLException e) {
				if ("해당 조건에 맞는 지출 신청이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("예산 등록 중 오류가 발생했습니다. ");
				sc.nextLine();
			}
		}

	}

	// 지출 신청 수정
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

				ExpenditureRequest request = new ExpenditureRequest();
				request.setExpenditureRequestId(requestId);
				request.setAmount(amount);
				request.setDescription(description);

				expenditureRequestservice.updateExpenditureRequest(request, requestId);
				break;
			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("해당 부서에 이미 동일한 항목이 존재합니다.");
			} catch (SQLException e) {
				if ("해당 조건에 맞는 지출 신청이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
			} catch (Exception e) {
				System.out.println("예산 등록 중 오류가 발생했습니다. ");
				sc.nextLine();
			}
		}

	}

	// 지출 신청 소프트딜리트
	private void softDeleteRequest(Scanner sc) throws SQLException {

		while (true) {
			try {
				System.out.print("삭제할 신청 ID: ");
				int requestId = sc.nextInt();
				expenditureRequestservice.softDeleteExpenditureRequest(requestId);
				break;
			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLIntegrityConstraintViolationException e) {
				System.out.println("해당 부서에 이미 동일한 항목이 존재합니다.");
			} catch (SQLException e) {
				if ("해당 조건에 맞는 지출 신청이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("예산 등록 중 오류가 발생했습니다. ");
				sc.nextLine();
			}
		}

	}
}
