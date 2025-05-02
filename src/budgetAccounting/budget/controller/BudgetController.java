package budgetAccounting.budget.controller;

import java.sql.Connection;
import java.sql.SQLException;

import budgetAccounting.budget.model.entity.Budget;
import budgetAccounting.budget.model.service.BudgetService;

public class BudgetController {
	
	private final BudgetService budgetService;
	
	public BudgetController(Connection conn) {
		this.budgetService = new BudgetService(conn);
	}
	
	public void handleNewBudget(String DepartmentId, String category) {
        Budget budget = new Budget();
        budget.setBudgetId();
        budget.setDepartmentId();
        budget.setYear();
        budget.setBudgetAmount();
        budget.setCategoryId();
        budget.setDescription();

        try {
        	budgetService.registerBudget(budget);
            System.out.println("예산이 성공적으로 등록되었습니다.");
        } catch (SQLException e) {
            System.out.println("예산 등록 실패: " + e.getMessage());
        }
    }
	
}
