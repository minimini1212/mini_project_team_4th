package budgetAccounting.expenditureRequest.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import budgetAccounting.expenditure.model.dao.ExpenditureDao;
import budgetAccounting.expenditure.model.entity.Expenditure;
import budgetAccounting.expenditureRequest.model.dao.ExpenditureRequestDao;
import budgetAccounting.expenditureRequest.model.entity.ExpenditureRequest;

public class ExpenditureRequestService {
	private ExpenditureRequestDao expenditureRequestDao;
	private ExpenditureDao expenditureDao;
	private Connection conn;

	public ExpenditureRequestService(Connection conn) {
		this.conn = conn;
		this.expenditureRequestDao = new ExpenditureRequestDao(conn);
		this.expenditureDao = new ExpenditureDao(conn);
	}

	// 지출 신청
	public void createExpenditureRequest(ExpenditureRequest request) throws SQLException {
		expenditureRequestDao.insertExpenditureRequest(request);
	}

	// 지출 승인 및 예산 테이블에 삽입
	public void approveAndInsertToExpenditure(int requestId, int approverId) throws SQLException {
		try {
			// 자동 커밋 false
			conn.setAutoCommit(false);

			// status = 'APPROVED'로
			expenditureRequestDao.approve(requestId, approverId);

			// 지출 신청 ID로 특정 예산 신청 가져오기
			ExpenditureRequest expenditureRequest = expenditureRequestDao.findByExpenditureRequestId(requestId).get(0);

			// getNextexpenditureId로 시퀀스를 가져와 지출 Id로 추가
			int newExpenditureId = expenditureDao.getNextExpenditureId();
			// 타입 변경
			Expenditure expenditure = convertToExpenditure(expenditureRequest);
			// expenditure의 id와 expenditure_request id 넣기
			expenditure.setExpenditureId(newExpenditureId);
			expenditure.setExpenditureRequestId(requestId);

			// 지출 생성
			expenditureDao.insertExpenditure(expenditure);
			System.out.println("승인 및 지출 등록이 완료되었습니다.");
			// 커밋
			conn.commit();
		} catch (SQLException e) {
			// 하나라도 오류가 나면 롤백
			conn.rollback();
			throw e;
		} finally {
			conn.setAutoCommit(true);
		}
	}

	// expenditureRequest 타입을 expenditure 타입으로 변환
	private Expenditure convertToExpenditure(ExpenditureRequest request) {
		Expenditure expenditure = new Expenditure();
		expenditure.setDepartmentId(request.getDepartmentId());
		expenditure.setYear(request.getYear());
		expenditure.setAmount(request.getAmount());
		expenditure.setCategoryId(request.getCategoryId());
		expenditure.setDescription(request.getDescription());
		expenditure.setExpenditureDate(new Date());

		return expenditure;
	}

	// 전체 지출 신청 목록
	public List<ExpenditureRequest> getAllExpenditureRequests() throws SQLException {
		return expenditureRequestDao.findAllExpenditureRequest();
	}

	// 특정 지출 신청 조회
	public List<ExpenditureRequest> getExpenditureRequestById(int requestId) throws SQLException {
		return expenditureRequestDao.findByExpenditureRequestId(requestId);
	}

	// 지출 신청 수정
	public void updateExpenditureRequest(ExpenditureRequest request, int requestId) throws SQLException {
		expenditureRequestDao.updateByExpenditureRequestId(request, requestId);
	}

	// 소프트 삭제
	public void softDeleteExpenditureRequest(int requestId) throws SQLException {
		expenditureRequestDao.softDeleteByExpenditureRequestId(requestId);
	}

}
