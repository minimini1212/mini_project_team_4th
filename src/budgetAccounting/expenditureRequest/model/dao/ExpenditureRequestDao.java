package budgetAccounting.expenditureRequest.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import budgetAccounting.expenditure.model.entity.Expenditure;
import budgetAccounting.expenditureRequest.model.entity.ExpenditureRequest;
import common.SessionContext;

public class ExpenditureRequestDao {
	private Connection conn;

	public ExpenditureRequestDao(Connection conn) {
		this.conn = conn;
	}

	// 지출 신청서 생성
	public void insertExpenditureRequest(ExpenditureRequest expenditureRequest) throws SQLException {
		if (conn == null) {
			System.out.println("conn is null!");
			return;
		}
		if (conn.isClosed()) {
			System.out.println("conn is closed!");
			return;
		}

		String sql = "INSERT INTO expenditure_request (expenditure_request_id, "
				+ "department_id, year, amount, category_id, "
				+ "requester_id, approver_id, request_date, approval_date, description, " + "status, DEL_YN)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, NULL, CURRENT_TIMESTAMP, NULL, ?,'REQUESTED', 'N')";

		String sequenceSql = "SELECT SEQ_expenditure_REQUEST_ID.NEXTVAL AS id FROM DUAL";

		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sequenceSql)) {

			if (rs.next()) {
				int sequence = rs.getInt("id");

				try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setInt(1, sequence);
					pstmt.setInt(2, expenditureRequest.getDepartmentId());
					pstmt.setInt(3, expenditureRequest.getYear());
					pstmt.setInt(4, expenditureRequest.getAmount());
					pstmt.setInt(5, expenditureRequest.getCategoryId());
					pstmt.setString(6, SessionContext.getEmpNumber());
					pstmt.setString(7, expenditureRequest.getDescription());

					int year = expenditureRequest.getYear();

					if (year < 1000 || year > 9999) {
						throw new IllegalArgumentException("연도는 4자리로 입력해주세요.");
					}

					pstmt.executeUpdate();
					System.out.println("지출 신청이 완료되었습니다.");
				}
			}

		} catch (SQLException e) {
			System.out.println("SQL 오류 발생: " + e.getMessage());
			System.out.println("SQL 상태: " + e.getSQLState());
			System.out.println("오류 코드: " + e.getErrorCode());
			e.printStackTrace();
		}
	}

	// 지출 신청 승인
	public void approve(int requestId) throws SQLException {
		String sql = "UPDATE expenditure_request SET status = 'APPROVED', "
				+ "approver_id = ?, approval_date = SYSDATE " + "WHERE expenditure_request_id = ? AND del_yn = 'N'";
		String selectSql = "SELECT * FROM expenditure_request WHERE expenditure_request_id = ? AND del_yn IN ('N', 'n')";

		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				PreparedStatement pstmt1 = conn.prepareStatement(selectSql)) {
			pstmt1.setInt(1, requestId);

			try (ResultSet rs = pstmt1.executeQuery()) {
				if (!rs.next()) {
					throw new SQLException("해당 조건에 맞는 지출 신청이 존재하지 않습니다.");
				}

				pstmt.setString(1, SessionContext.getEmpNumber());
				pstmt.setInt(2, requestId);
				pstmt.executeUpdate();
			}

		}

	}

	// 지출 신청 전체 조회
	public List<ExpenditureRequest> findAllExpenditureRequest() throws SQLException {

		String sql = "SELECT * FROM expenditure_request WHERE del_yn IN ('N', 'n')";
		List<ExpenditureRequest> list = new ArrayList<>();

		if (conn == null || conn.isClosed()) {
			throw new SQLException("DB 연결이 유효하지 않습니다.");
		}

		// try-with-resources 문법을 사용 -> 자원을 자동으로 닫도록 설정
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				ExpenditureRequest expenditureRequest = new ExpenditureRequest();
				expenditureRequest.setExpenditureRequestId(rs.getInt("expenditure_request_id"));
				expenditureRequest.setDepartmentId(rs.getInt("department_id"));
				expenditureRequest.setYear(rs.getInt("year"));
				expenditureRequest.setAmount(rs.getInt("amount"));
				expenditureRequest.setCategoryId(rs.getInt("category_id"));
				expenditureRequest.setStatus(rs.getString("status"));
				expenditureRequest.setRequesterId(rs.getString("requester_id"));
				expenditureRequest.setApproverId(rs.getString("approver_id"));
				expenditureRequest.setRequestDate(rs.getDate("request_date"));
				expenditureRequest.setApprovalDate(rs.getDate("approval_date"));
				expenditureRequest.setDescription(rs.getString("description"));

				// 부서 이름, 카테고리 이름 추가
				int departmentId = rs.getInt("department_id");
				int categoryId = rs.getInt("category_id");
				String departmentName = getDepartmentNameById(departmentId);
				String categoryName = getCategoryNameById(categoryId);

				expenditureRequest.setDepartmentName(departmentName);
				expenditureRequest.setCategoryName(categoryName);

				list.add(expenditureRequest);

			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 발생: " + e.getMessage());
			System.out.println("SQL 상태: " + e.getSQLState());
			System.out.println("오류 코드: " + e.getErrorCode());
			e.printStackTrace();
		}

		return list;
	}

	// 지출 신청 부분 조회 - expenditure_request_id로 검색
	public List<ExpenditureRequest> findByExpenditureRequestId(int requestId) throws SQLException {
		String sql = "SELECT * FROM expenditure_request WHERE expenditure_request_id = ? AND del_yn IN ('N', 'n')";
		List<ExpenditureRequest> list = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, requestId);
			try (ResultSet rs = pstmt.executeQuery()) {

				boolean hasData = false;
				while (rs.next()) {
					hasData = true;

					ExpenditureRequest expenditureRequest = new ExpenditureRequest();
					expenditureRequest.setExpenditureRequestId(rs.getInt("expenditure_request_id"));
					expenditureRequest.setDepartmentId(rs.getInt("department_id"));
					expenditureRequest.setYear(rs.getInt("year"));
					expenditureRequest.setAmount(rs.getInt("amount"));
					expenditureRequest.setCategoryId(rs.getInt("category_id"));
					expenditureRequest.setStatus(rs.getString("status"));
					expenditureRequest.setRequesterId(rs.getString("requester_id"));
					expenditureRequest.setApproverId(rs.getString("approver_id"));
					expenditureRequest.setRequestDate(rs.getDate("request_date"));
					expenditureRequest.setApprovalDate(rs.getDate("approval_date"));
					expenditureRequest.setDescription(rs.getString("description"));

					// 부서 이름, 카테고리 이름 추가
					int departmentId = rs.getInt("department_id");
					int categoryId = rs.getInt("category_id");
					String departmentName = getDepartmentNameById(departmentId);
					String categoryName = getCategoryNameById(categoryId);

					expenditureRequest.setDepartmentName(departmentName);
					expenditureRequest.setCategoryName(categoryName);

					list.add(expenditureRequest);
				}

				if (!hasData) {
					throw new SQLException("해당 조건에 맞는 지출 신청이 존재하지 않습니다.");
				}
			}
		}

		return list;
	}

	// 지출 신청 수정 - 금액과 설명
	public void updateByExpenditureRequestId(ExpenditureRequest expenditureRequest, int requestId) throws SQLException {
		String sql = "UPDATE expenditure_request SET amount = ?, description = ? WHERE expenditure_request_id = ?";
		String selectSql = "SELECT * FROM expenditure_request WHERE expenditure_request_id = ? AND del_yn IN ('N', 'n')";

		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				PreparedStatement pstmt1 = conn.prepareStatement(selectSql)) {
			pstmt1.setInt(1, requestId);

			try (ResultSet rs = pstmt1.executeQuery()) {
				if (!rs.next()) {
					throw new SQLException("해당 조건에 맞는 지출 신청이 존재하지 않습니다.");
				}

				pstmt.setInt(1, expenditureRequest.getAmount());
				pstmt.setString(2, expenditureRequest.getDescription());
				pstmt.setInt(3, expenditureRequest.getExpenditureRequestId());

				pstmt.executeUpdate();
				System.out.println("지출 신청이 수정되었습니다.");
			}

		}
	}

	// 지출 신청 소프트딜리트
	public void softDeleteByExpenditureRequestId(int requestId) throws SQLException {
		String sql = "UPDATE expenditure_request SET del_yn = 'Y' WHERE expenditure_request_id = ?";
		String selectSql = "SELECT * FROM expenditure_request WHERE expenditure_request_id = ? AND del_yn IN ('N', 'n')";

		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				PreparedStatement pstmt1 = conn.prepareStatement(selectSql)) {

			pstmt1.setInt(1, requestId);

			try (ResultSet rs = pstmt1.executeQuery()) { // 그리고 실행

				if (!rs.next()) {
					throw new SQLException("해당 조건에 맞는 지출 신청이 존재하지 않습니다.");
				}

				pstmt.setInt(1, requestId);

				pstmt.executeUpdate();
				System.out.println("지출 신청이 소프트 삭제되었습니다.");
			}

		}
	}

	// 카테고리ID로 카테고리명 찾는 메서드
	public String getCategoryNameById(int categoryId) throws SQLException {
		String sql = "SELECT category_name FROM vw_category_name WHERE category_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, categoryId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString("category_name");
				} else {
					throw new SQLException("카테고리명을 가져오지 못했습니다.");
				}
			}
		}
	}

	// 부서ID로 부서명 찾는 메서드
	public String getDepartmentNameById(int departmentId) throws SQLException {
		String sql = "SELECT department_name FROM vw_department_name WHERE department_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, departmentId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getString("department_name");
				} else {
					throw new SQLException("부서명을 가져오지 못했습니다.");
				}
			}
		}
	}

}
