package budgetAccounting.budget.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import budgetAccounting.budget.model.dao.BudgetDao;
import budgetAccounting.budget.model.entity.Budget;
import dbConn.ConnectionSingletonHelper;

public class BudgetService {

	private BudgetDao budgetDao;
	private Connection conn;

	// 예산 생성
	public void createBudget(Budget budget) throws SQLException {
		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			budgetDao = new BudgetDao(conn);
			budgetDao.insertBudget(budget);
		} catch (SQLException e) {
			System.err.println("데이터베이스 연결 실패: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("서버 오류: " + e.getMessage());
		} finally {
			conn.close();
		}

	}

	// 전체 예산 목록
	public List<Budget> getAllBudget() throws SQLException {

		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			budgetDao = new BudgetDao(conn);
			return budgetDao.findAllBudget();
		} catch (SQLException e) {
			System.err.println("데이터베이스 연결 실패: " + e.getMessage());
			return null;
		} catch (Exception e) {
			System.out.println("서버 오류: " + e.getMessage());
			return null;
		} finally {
			conn.close();
		}

	}

	// 특정 예산 조회
	public List<Budget> getBudgetById(int requestId) throws SQLException {
		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			budgetDao = new BudgetDao(conn);
			return budgetDao.findByBudgetId(requestId);
		} finally {
			conn.close();
		}

	}

	// 예산 수정
	public void updateBudget(Budget budget, int requestId) throws SQLException {
		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			budgetDao = new BudgetDao(conn);
			budgetDao.updateByBudgetId(budget, requestId);
		} finally {
			conn.close();
		}

	}

	// 소프트 삭제
	public void softDeleteBudget(int requestId) throws SQLException {
		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			budgetDao = new BudgetDao(conn);
			budgetDao.softDeleteByBudgetId(requestId);
		} finally {
			conn.close();
		}

	}
}
