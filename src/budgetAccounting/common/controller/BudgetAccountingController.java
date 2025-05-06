package budgetAccounting.common.controller;

import java.util.Scanner;

import budgetAccounting.budget.controller.BudgetController;
import budgetAccounting.budgetRequest.controller.BudgetRequestController;
import budgetAccounting.common.view.BudgetAccountingView;
import budgetAccounting.expenditure.controller.ExpenditureController;
import budgetAccounting.expenditureRequest.controller.ExpenditureRequestController;
import budgetAccounting.statistics.controller.StatisticsController;

public class BudgetAccountingController {
	private BudgetAccountingView budgetAccountingView = new BudgetAccountingView();
	private BudgetController budgetController = new BudgetController();
	private BudgetRequestController budgetRequestController = new BudgetRequestController();
	private ExpenditureController expenditureController = new ExpenditureController();
	private ExpenditureRequestController expenditureRequestController = new ExpenditureRequestController();
	private StatisticsController statisticsController = new StatisticsController();

	public void budgetAccountingMenu(Scanner sc, int rankOrder) {
		while (true) {
			budgetAccountingView.budgetAccountingMenu();
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
		}
	}
}
