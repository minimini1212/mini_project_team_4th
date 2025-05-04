package budgetAccounting.expenditure.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import budgetAccounting.expenditure.model.dao.ExpenditureDao;
import budgetAccounting.expenditure.model.entity.Expenditure;

public class ExpenditureService {
	private final ExpenditureDao expenditureDao;

	public ExpenditureService(Connection conn) {
		this.expenditureDao = new ExpenditureDao(conn);
	}

	// 지출 생성
	public void createExpenditure(Expenditure expenditure) throws SQLException {
		expenditureDao.insertExpenditure(expenditure);
	}

	// 전체 지출 신청 목록
	public List<Expenditure> getAllExpenditure() throws SQLException {
		return expenditureDao.findAllExpenditure();
	}

	// 특정 지출 신청 조회
	public List<Expenditure> getExpenditureById(int requestId) throws SQLException {
		return expenditureDao.findByExpenditureId(requestId);
	}

	// 지출 신청 수정
	public void updateExpenditure(Expenditure expenditure) throws SQLException {
		expenditureDao.updateByExpenditureId(expenditure);
	}

	// 소프트 삭제
	public void softDeleteExpenditure(int requestId) throws SQLException {
		expenditureDao.softDeleteByExpenditureId(requestId);
	}
}
