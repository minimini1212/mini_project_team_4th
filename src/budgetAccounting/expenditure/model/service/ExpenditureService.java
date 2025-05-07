package budgetAccounting.expenditure.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import budgetAccounting.budget.model.dao.BudgetDao;
import budgetAccounting.expenditure.model.dao.ExpenditureDao;
import budgetAccounting.expenditure.model.entity.Expenditure;
import dbConn.ConnectionSingletonHelper;

public class ExpenditureService {
	private ExpenditureDao expenditureDao;
	private Connection conn;

	// 지출 생성
	public void createExpenditure(Expenditure expenditure) throws SQLException {
		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			expenditureDao = new ExpenditureDao(conn);
			expenditureDao.insertExpenditure(expenditure);
		} catch (SQLException e) {
			System.err.println("데이터베이스 연결 실패: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			if ("연도는 4자리로 입력해주세요.".equals(e.getMessage())) {
				System.out.println(e.getMessage());
			} else {
				System.out.println("존재하지 않는 부서 ID 또는 존재하지 않는 항목입니다.");
			}
		} catch (Exception e) {
			System.out.println("서버 오류: " + e.getMessage());
		} finally {
			conn.close();
		}

	}

	// 전체 지출 신청 목록
	public List<Expenditure> getAllExpenditure() throws SQLException {
		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			expenditureDao = new ExpenditureDao(conn);
			return expenditureDao.findAllExpenditure();
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

	// 특정 지출 신청 조회
	public List<Expenditure> getExpenditureById(int requestId) throws SQLException {

		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			expenditureDao = new ExpenditureDao(conn);
			return expenditureDao.findByExpenditureId(requestId);
		} finally {
			conn.close();
		}

	}

	// 지출 신청 수정
	public void updateExpenditure(Expenditure expenditure, int requestId) throws SQLException {

		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			expenditureDao = new ExpenditureDao(conn);
			expenditureDao.updateByExpenditureId(expenditure, requestId);
		} finally {
			conn.close();
		}

	}

	// 소프트 삭제
	public void softDeleteExpenditure(int requestId) throws SQLException {

		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			expenditureDao = new ExpenditureDao(conn);
			expenditureDao.softDeleteByExpenditureId(requestId);
		} finally {
			conn.close();
		}

	}
}
