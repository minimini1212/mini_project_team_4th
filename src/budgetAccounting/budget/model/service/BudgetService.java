package budgetAccounting.budget.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import budgetAccounting.budget.model.dao.BudgetDao;
import budgetAccounting.budget.model.entity.Budget;
import budgetAccounting.budgetRequest.model.entity.BudgetRequest;

public class BudgetService {

	private final BudgetDao budgetDao;

	public BudgetService(Connection conn) {
		this.budgetDao = new BudgetDao(conn);
	}

	// 예산 생성
	public void createBudget(Budget budget) throws SQLException {
		budgetDao.insertBudget(budget);
	}

	// 전체 예산 신청 목록
	public List<Budget> getAllBudget() throws SQLException {
		return budgetDao.findAllBudget();
	}

	// 특정 예산 신청 조회
	public List<Budget> getBudgetById(int requestId) throws SQLException {
		return budgetDao.findByBudgetId(requestId);
	}

	// 예산 신청 수정
	public void updateBudget(Budget budget, int requestId) throws SQLException {
		budgetDao.updateByBudgetId(budget, requestId);
	}

	// 소프트 삭제
	public void softDeleteBudget(int requestId) throws SQLException {
		budgetDao.softDeleteByBudgetId(requestId);
	}
}
