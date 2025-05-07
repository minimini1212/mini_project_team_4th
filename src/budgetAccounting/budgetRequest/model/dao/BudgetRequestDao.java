package budgetAccounting.budgetRequest.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import budgetAccounting.budgetRequest.model.entity.BudgetRequest;
import common.SessionContext;

public class BudgetRequestDao {
	private Connection conn;

	public BudgetRequestDao(Connection conn) {
		this.conn = conn;
	}

	// 예산 신청서 생성
	public void insertBudgetRequest(BudgetRequest budgetRequest) throws SQLException {
		if (conn == null) {
			System.out.println("conn is null!");
			return;
		}
		if (conn.isClosed()) {
			System.out.println("conn is closed!");
			return;
		}

		String sql = "INSERT INTO budget_request (BUDGET_REQUEST_ID, DEPARTMENT_ID, YEAR, REQUESTED_AMOUNT, CATEGORY_ID, REQUESTER_ID, APPROVER_ID, REQUEST_DATE, DESCRIPTION, STATUS, DEL_YN)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, ?, NULL, CURRENT_TIMESTAMP, ?, 'REQUESTED', 'N')";

		String sequenceSql = "SELECT SEQ_BUDGET_REQUEST_ID.NEXTVAL AS id FROM DUAL";

		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sequenceSql)) {

			if (rs.next()) {
				int sequence = rs.getInt("id");

				try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setInt(1, sequence);
					pstmt.setInt(2, budgetRequest.getDepartmentId());
					pstmt.setInt(3, budgetRequest.getYear());
					pstmt.setInt(4, budgetRequest.getRequestedAmount());
					pstmt.setInt(5, budgetRequest.getCategoryId());
					pstmt.setString(6, SessionContext.getEmpNumber());
					pstmt.setString(7, budgetRequest.getDescription());

					int departmentId = budgetRequest.getDepartmentId();
					int categoryId = budgetRequest.getCategoryId();

					int[] departmentIds = { 2, 3, 4 }; // 허용된 부서 ID

					if (!contains(departmentIds, departmentId)) {
						throw new IllegalArgumentException("❌존재하지 않는 부서 ID입니다.");
					}

					int[] categoryIds = { 1, 2, 3, 4, 5 }; // 허용된 항목 ID

					if (!contains(categoryIds, categoryId)) {
						throw new IllegalArgumentException("❌존재하지 않는 항목 ID입니다.");
					}

					int year = budgetRequest.getYear();

					if (year < 1000 || year > 9999) {
						throw new IllegalArgumentException("❌연도는 4자리로 입력해주세요.");
					}

					pstmt.executeUpdate();
					System.out.println("✅ 예산 신청이 완료되었습니다.");
				}
			}

		} catch (SQLException e) {
			System.out.println("SQL 오류 발생: " + e.getMessage());
			System.out.println("SQL 상태: " + e.getSQLState());
			System.out.println("오류 코드: " + e.getErrorCode());
			e.printStackTrace();
		}
	}

	// 예산 신청 승인
	public void approve(int requestId) throws SQLException {
		String sql = "UPDATE budget_request SET status = 'APPROVED', " + "approver_id = ?, approval_date = SYSDATE "
				+ "WHERE budget_request_id = ? AND del_yn = 'N'";
		String selectSql = "SELECT * FROM budget_request WHERE budget_request_id = ? AND del_yn IN ('N', 'n')";

		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				PreparedStatement pstmt1 = conn.prepareStatement(selectSql)) {
			pstmt1.setInt(1, requestId);

			try (ResultSet rs = pstmt1.executeQuery()) {
				if (!rs.next()) {
					throw new SQLException("해당 조건에 맞는 예산 신청이 존재하지 않습니다.");
				}

				if (SessionContext.getEmpNumber().equals(rs.getString("requester_id"))) {
					throw new IllegalArgumentException("신청자와 승인자는 같을 수 없습니다.");
				}

				pstmt.setString(1, SessionContext.getEmpNumber());
				pstmt.setInt(2, requestId);
				pstmt.executeUpdate();
			}

		}
	}

	// 예산 신청 전체 조회
	public List<BudgetRequest> findAllBudgetRequest() throws SQLException {

		String sql = "SELECT * FROM budget_request WHERE del_yn IN ('N', 'n')";
		List<BudgetRequest> list = new ArrayList<>();

		if (conn == null || conn.isClosed()) {
			throw new SQLException("DB 연결이 유효하지 않습니다.");
		}

		// try-with-resources 문법을 사용 -> 자원을 자동으로 닫도록 설정
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				BudgetRequest budgetRequest = new BudgetRequest();
				budgetRequest.setBudgetRequestId(rs.getInt("budget_request_id"));
				budgetRequest.setDepartmentId(rs.getInt("department_id"));
				budgetRequest.setYear(rs.getInt("year"));
				budgetRequest.setRequestedAmount(rs.getInt("requested_amount"));
				budgetRequest.setCategoryId(rs.getInt("category_id"));
				budgetRequest.setStatus(rs.getString("status"));
				budgetRequest.setRequesterId(rs.getString("requester_id"));
				budgetRequest.setApproverId(rs.getString("approver_id"));
				budgetRequest.setRequestDate(rs.getDate("request_date"));
				budgetRequest.setApprovalDate(rs.getDate("approval_date"));
				budgetRequest.setDescription(rs.getString("description"));

				// 부서 이름, 카테고리 이름 추가
				int departmentId = rs.getInt("department_id");
				int categoryId = rs.getInt("category_id");
				String departmentName = getDepartmentNameById(departmentId);
				String categoryName = getCategoryNameById(categoryId);

				budgetRequest.setDepartmentName(departmentName);
				budgetRequest.setCategoryName(categoryName);

				list.add(budgetRequest);

			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 발생: " + e.getMessage());
			System.out.println("SQL 상태: " + e.getSQLState());
			System.out.println("오류 코드: " + e.getErrorCode());
			e.printStackTrace();
		}

		return list;
	}

	// 예산 신청 부분 조회 - budget_request_id로 검색
	public List<BudgetRequest> findByBudgetRequestId(int requestId) throws SQLException {
		String sql = "SELECT * FROM budget_request WHERE budget_request_id = ? AND del_yn IN ('N', 'n')";
		List<BudgetRequest> list = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, requestId);
			try (ResultSet rs = pstmt.executeQuery()) {

				boolean hasData = false;
				while (rs.next()) {
					hasData = true;

					BudgetRequest budgetRequest = new BudgetRequest();
					budgetRequest.setBudgetRequestId(rs.getInt("budget_request_id"));
					budgetRequest.setDepartmentId(rs.getInt("department_id"));
					budgetRequest.setYear(rs.getInt("year"));
					budgetRequest.setRequestedAmount(rs.getInt("requested_amount"));
					budgetRequest.setCategoryId(rs.getInt("category_id"));
					budgetRequest.setStatus(rs.getString("status"));
					budgetRequest.setRequesterId(rs.getString("requester_id"));
					budgetRequest.setApproverId(rs.getString("approver_id"));
					budgetRequest.setRequestDate(rs.getDate("request_date"));
					budgetRequest.setApprovalDate(rs.getDate("approval_date"));
					budgetRequest.setDescription(rs.getString("description"));

					// 부서 이름, 카테고리 이름 추가
					int departmentId = rs.getInt("department_id");
					int categoryId = rs.getInt("category_id");
					String departmentName = getDepartmentNameById(departmentId);
					String categoryName = getCategoryNameById(categoryId);

					budgetRequest.setDepartmentName(departmentName);
					budgetRequest.setCategoryName(categoryName);

					list.add(budgetRequest);
				}

				if (!hasData) {
					throw new SQLException("해당 조건에 맞는 예산 신청이 존재하지 않습니다.");
				}
			}
		}

		return list;
	}

	// 예산 신청 수정 - 금액과 설명
	public void updateByBudgetRequestId(BudgetRequest budgetRequest, int requestId) throws SQLException {
		String sql = "UPDATE budget_request SET requested_amount = ?, description = ? WHERE budget_request_id = ?";
		String selectSql = "SELECT * FROM budget_request WHERE budget_request_id = ? AND del_yn IN ('N', 'n')";

		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				PreparedStatement pstmt1 = conn.prepareStatement(selectSql)) {
			pstmt1.setInt(1, requestId);

			try (ResultSet rs = pstmt1.executeQuery()) {
				if (!rs.next()) {
					throw new SQLException("해당 조건에 맞는 예산 신청이 존재하지 않습니다.");
				}

				pstmt.setInt(1, budgetRequest.getRequestedAmount());
				pstmt.setString(2, budgetRequest.getDescription());
				pstmt.setInt(3, budgetRequest.getBudgetRequestId());

				pstmt.executeUpdate();
				System.out.println("예산 신청이 수정되었습니다.");
			}

		}
	}

	// 예산 신청 소프트딜리트
	public void softDeleteByBudgetRequestId(int requestId) throws SQLException {
		String sql = "UPDATE budget_request SET del_yn = 'Y' WHERE budget_request_id = ?";
		String selectSql = "SELECT * FROM budget_request WHERE budget_request_id = ? AND del_yn IN ('N', 'n')";

		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				PreparedStatement pstmt1 = conn.prepareStatement(selectSql)) {
			pstmt1.setInt(1, requestId);

			try (ResultSet rs = pstmt1.executeQuery()) {
				if (!rs.next()) {
					throw new SQLException("해당 조건에 맞는 예산 신청이 존재하지 않습니다.");
				}

				pstmt.setInt(1, requestId);

				pstmt.executeUpdate();
				System.out.println("예산 신청이 소프트 삭제되었습니다.");
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

	// 존재하는 값인지 아닌지 비교 (부서ID, 카테고리 ID)
	private boolean contains(int[] arr, int value) {
		for (int num : arr) {
			if (num == value) {
				return true;
			}
		}
		return false;
	}

}
