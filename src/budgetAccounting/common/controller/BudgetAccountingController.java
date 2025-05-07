package budgetAccounting.common.controller;

import java.util.Scanner;

import budgetAccounting.budget.controller.BudgetController;
import budgetAccounting.budgetRequest.controller.BudgetRequestController;
import budgetAccounting.common.view.BudgetAccountingView;
import budgetAccounting.expenditure.controller.ExpenditureController;
import budgetAccounting.expenditureRequest.controller.ExpenditureRequestController;
import budgetAccounting.statistics.controller.StatisticsController;
import common.SessionContext;
import main.view.MainEntry;

public class BudgetAccountingController {
	private BudgetAccountingView budgetAccountingView = new BudgetAccountingView();
	private BudgetController budgetController = new BudgetController();
	private BudgetRequestController budgetRequestController = new BudgetRequestController();
	private ExpenditureController expenditureController = new ExpenditureController();
	private ExpenditureRequestController expenditureRequestController = new ExpenditureRequestController();
	private StatisticsController statisticsController = new StatisticsController();

	public void budgetAccountingMenu(Scanner sc, int rankOrder) {
		while (true) {
			if (rankOrder == 1) {
				budgetAccountingView.budgetAccountingMenuForMaster();
				switch (sc.nextLine()) {
					case "0":
						return;
					case "1":
						budgetController.run(sc, rankOrder);
						break;
					case "2":
						budgetRequestController.run(sc, rankOrder);
						break;
					case "3":
						expenditureController.run(sc, rankOrder);
						break;
					case "4":
						expenditureRequestController.run(sc, rankOrder);
						break;
					case "5":
						statisticsController.run(sc, rankOrder);
						break;
					default:
						System.out.println("잘못된 입력입니다.");
						break;
				}
			} else {
				budgetAccountingView.budgetAccountingMenu();
				switch (sc.nextLine()) {
					case "0":
						if (confirmLogout()) {
							SessionContext.clear();
							System.out.println("✅ 로그아웃되었습니다. 로그인 메뉴로 돌아갑니다.\n");
							MainEntry.main(null);
							return;
						}
						continue;
					case "1":
						budgetController.run(sc, rankOrder);
						break;
					case "2":
						budgetRequestController.run(sc, rankOrder);
						break;
					case "3":
						expenditureController.run(sc, rankOrder);
						break;
					case "4":
						expenditureRequestController.run(sc, rankOrder);
						break;
					case "5":
						statisticsController.run(sc, rankOrder);
						break;
					default:
						System.out.println("잘못된 입력입니다.");
						break;
				}
			}

		}
	}
	private boolean confirmLogout() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.print("로그아웃 하시겠습니까? (y/n): ");
			String input = sc.nextLine().trim().toLowerCase();

			if (input.equals("y")) return true;
			if (input.equals("n")) return false;

			System.out.println("❌ 잘못된 입력입니다. y 또는 n을 입력해주세요.");
		}
	}
}
