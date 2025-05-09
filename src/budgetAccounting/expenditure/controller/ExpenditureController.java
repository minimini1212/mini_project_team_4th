package budgetAccounting.expenditure.controller;

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
	private ExpenditureView expenditureView = new ExpenditureView();

	public ExpenditureController() {
		this.expenditureservice = new ExpenditureService();
	}

	public void run(Scanner sc, int rankOrder) {
		boolean running = true;

		while (running) {
			expenditureView.menu();
			int choice = sc.nextInt();
			sc.nextLine();
			try {
				switch (choice) {
				case 1:
					if (rankOrder >= 3) {
						System.out.println("해당 기능에 대한 권한이 없습니다.");
						running = false;
						return;
					}
					createExpenditure(sc);
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
					updateRequest(sc);
					break;
				case 5:
					if (rankOrder >= 3) {
						System.out.println("해당 기능에 대한 권한이 없습니다.");
						running = false;
						return;
					}
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

	// 지출 생성
	private void createExpenditure(Scanner sc) throws SQLException {
		while (true) {
			try {
				sc.nextLine(); // 개행 제거
				System.out.print("부서 ID (인사 2번, 예산/회계 3번, 자산 4번): ");
				int deptId = Integer.parseInt(sc.nextLine());
				System.out.print("연도를 입력하세요(4자리): ");
				int year = Integer.parseInt(sc.nextLine());
				System.out.print("지출 금액을 입력하세요: ");
				int amount = Integer.parseInt(sc.nextLine());
				System.out.print("카테고리 ID (인건비 1번, 수리비 2번, 소모품비 3번, 출장비 4번, 운영비 5번): ");
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

				break;

			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (IllegalArgumentException e) {
				if ("존재하지 않는 부서 ID입니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					sc.nextLine();
					break;
				} else if ("존재하지 않는 항목 ID입니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					sc.nextLine();
					break;
				} else if ("연도는 4자리로 입력해주세요.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					sc.nextLine();
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
			} catch (SQLException e) {
				if ("잔여 예산이 부족합니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else if ("해당 조건에 맞는 예산이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요. " + e.getMessage());
				}
			} catch (Exception e) {
				System.out.println("지출 등록 중 오류가 발생했습니다. ");
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
				if ("해당 조건에 맞는 지출이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
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
				sc.nextLine();
				System.out.print("설명: ");
				String description = sc.nextLine();

				Expenditure expenditure = new Expenditure();
				expenditure.setExpenditureId(requestId);
				expenditure.setDescription(description);

				expenditureservice.updateExpenditure(expenditure, requestId);

				break;

			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLException e) {
				if ("해당 조건에 맞는 지출이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
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

				break;

			} catch (InputMismatchException e) {
				System.out.println("올바르게 입력해주세요.");
				sc.nextLine();

			} catch (SQLException e) {
				if ("해당 조건에 맞는 지출이 존재하지 않습니다.".equals(e.getMessage())) {
					System.out.println(e.getMessage());
					break;
				} else {
					System.out.println("알맞지 않은 입력값이 있습니다. 다시 살펴봐주세요.");
				}
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("지출 삭제 중 오류가 발생했습니다: ");
				sc.nextLine();
			}
		}

	}
}
