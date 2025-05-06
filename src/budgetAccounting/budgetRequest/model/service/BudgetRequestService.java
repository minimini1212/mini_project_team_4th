package budgetAccounting.budgetRequest.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import budgetAccounting.budget.model.dao.BudgetDao;
import budgetAccounting.budget.model.entity.Budget;
import budgetAccounting.budgetRequest.model.dao.BudgetRequestDao;
import budgetAccounting.budgetRequest.model.entity.BudgetRequest;
import dbConn.ConnectionSingletonHelper;

public class BudgetRequestService {
	private BudgetRequestDao budgetRequestDao;
	private BudgetDao budgetDao;
	private Connection conn;

	// 예산 신청
	public void createBudgetRequest(BudgetRequest request) throws SQLException {
		
		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			budgetRequestDao = new BudgetRequestDao(conn);
			budgetRequestDao.insertBudgetRequest(request);
		} catch (SQLException e) {
			System.err.println("데이터베이스 연결 실패: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("서버 오류: " + e.getMessage());
		} finally {
			conn.close();
		}
		
		
	}

	// 예산 승인 및 예산 테이블에 삽입
	public void approveAndInsertToBudget(int requestId, int approverId) throws SQLException {
		try {
			
			conn = ConnectionSingletonHelper.getConnection("oracle");
			budgetRequestDao = new BudgetRequestDao(conn);
			budgetDao = new BudgetDao(conn);
			// 자동 커밋 false
			conn.setAutoCommit(false);

			// status = 'APPROVED'로
			budgetRequestDao.approve(requestId, approverId);

			// 예산 신청 ID로 특정 예산 신청 가져오기
			BudgetRequest budgetRequest = budgetRequestDao.findByBudgetRequestId(requestId).get(0);

			// getNextBudgetId로 시퀀스를 가져와 예산 Id로 추가
			int newBudgetId = budgetDao.getNextBudgetId();
			// 타입 변경
			Budget budget = convertToBudget(budgetRequest);
			// budget의 id와 budget_request id 넣기
			budget.setBudgetId(newBudgetId);
			budget.setBudgetRequestId(requestId);

			// 예산 생성
			budgetDao.insertBudget(budget);
			// 커밋
			conn.commit();
		} catch (SQLException e) {
			// 하나라도 오류가 나면 롤백
			conn.rollback();
			throw e;
		} finally {
			conn.setAutoCommit(true);
			conn.close();
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
		budget.setDescription(request.getDescription());
		budget.setRemainingAmount(request.getRequestedAmount());

		return budget;
	}

	// 전체 예산 신청 목록
	public List<BudgetRequest> getAllBudgetRequests() throws SQLException {
		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			budgetRequestDao = new BudgetRequestDao(conn);
			return budgetRequestDao.findAllBudgetRequest();
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

	// 특정 예산 신청 조회
	public List<BudgetRequest> getBudgetRequestById(int requestId) throws SQLException {
		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			budgetRequestDao = new BudgetRequestDao(conn);
			return budgetRequestDao.findByBudgetRequestId(requestId);
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

	// 예산 신청 수정
	public void updateBudgetRequest(BudgetRequest request, int requestId) throws SQLException {
		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			budgetRequestDao = new BudgetRequestDao(conn);
			budgetRequestDao.updateByBudgetRequestId(request, requestId);
		} catch (SQLException e) {
			System.err.println("데이터베이스 연결 실패: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("서버 오류: " + e.getMessage());
		} finally {
			conn.close();
		}

	}

	// 소프트 삭제
	public void softDeleteBudgetRequest(int requestId) throws SQLException {
		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			budgetRequestDao = new BudgetRequestDao(conn);
			budgetRequestDao.softDeleteByBudgetRequestId(requestId);
		} catch (SQLException e) {
			System.err.println("데이터베이스 연결 실패: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("서버 오류: " + e.getMessage());
		} finally {
			conn.close();
		}

	}
}
