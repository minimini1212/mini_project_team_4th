package budgetAccounting.budget.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import budgetAccounting.budget.model.dao.BudgetDao;
import budgetAccounting.budget.model.entity.Budget;

public class BudgetService {

	private final BudgetDao budgetDao;

	public BudgetService(Connection conn) {
		this.budgetDao = new BudgetDao(conn);
	}

	public void registerBudget(Budget budget) throws SQLException {
		budgetDao.insertBudget(budget);
	}
}
