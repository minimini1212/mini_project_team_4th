package budgetAccounting.budget.view;

import java.util.Scanner;

import budgetAccounting.budget.controller.BudgetController;

public class BudgetView {

	private final BudgetController budgetController;

	public BudgetView(BudgetController budgetController) {
		this.budgetController = budgetController;

	}

	public void show() {
		Scanner sc = new Scanner(System.in);
		System.out.print("부서 입력: ");
		String name = sc.nextLine();
		System.out.print("카테고리 입력: ");
		String category = sc.nextLine();

		budgetController.handleNewBudget(name, category);
	}

}
