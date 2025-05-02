package budgetAccounting.budgetRequest.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import budgetAccounting.budget.model.dao.BudgetDao;
import budgetAccounting.budget.model.entity.Budget;
import budgetAccounting.budgetRequest.model.dao.BudgetRequestDao;
import budgetAccounting.budgetRequest.model.entity.BudgetRequest;

public class BudgetRequestService {
	private BudgetRequestDao budgetRequestDao;
	private BudgetDao budgetDao;
	private Connection conn;

	public BudgetRequestService(Connection conn) {
		this.conn = conn;
		this.budgetRequestDao = new BudgetRequestDao(conn);
		this.budgetDao = new BudgetDao(conn);
	}

	// 예산 신청
	public void createBudgetRequest(BudgetRequest request) throws SQLException {
		budgetRequestDao.insertBudgetRequest(request);
	}

	// 예산 승인 및 예산 테이블에 삽입
	public void approveAndInsertToBudget(int requestId, int approverId) throws SQLException {
		try {
			conn.setAutoCommit(false);

			budgetRequestDao.approve(requestId, approverId);

			BudgetRequest budgetRequest = budgetRequestDao.findByBudgetRequestId(requestId).get(0);

			int newBudgetId = budgetDao.getNextBudgetId();
			Budget budget = convertToBudget(budgetRequest);
			budget.setBudgetId(newBudgetId);

			budgetDao.insertBudget(budget);

			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			conn.setAutoCommit(true);
		}
	}

	// budgetRequest 타입을 budget 타입으로 변환
	private Budget convertToBudget(BudgetRequest request) {
		Budget budget = new Budget();
		budget.setDepartmentId(request.getDepartmentId());
		budget.setYear(request.getYear());
		budget.setBudgetAmount(request.getRequestedAmount());
		budget.setCategoryId(request.getCategoryId());
		budget.setDescription(request.getDescription());

		return budget;
	}

	// 전체 예산 신청 목록
	public List<BudgetRequest> getAllBudgetRequests() throws SQLException {
		return budgetRequestDao.findAllBudgetRequest();
	}

	// 특정 예산 신청 조회
	public List<BudgetRequest> getBudgetRequestById(int requestId) throws SQLException {
		return budgetRequestDao.findByBudgetRequestId(requestId);
	}

	// 예산 신청 수정
	public void updateBudgetRequest(BudgetRequest request) throws SQLException {
		budgetRequestDao.updateByBudgetRequestId(request);
	}

	// 소프트 삭제
	public void softDeleteBudgetRequest(int requestId) throws SQLException {
		budgetRequestDao.softDeleteByBudgetRequestId(requestId);
	}

	// 완전 삭제
	public void deleteBudgetRequest(int requestId) throws SQLException {
		budgetRequestDao.deleteByBudgetRequestId(requestId);
	}
}
