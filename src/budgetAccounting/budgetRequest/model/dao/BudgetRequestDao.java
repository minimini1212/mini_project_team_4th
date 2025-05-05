package budgetAccounting.budgetRequest.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import budgetAccounting.budgetRequest.model.entity.BudgetRequest;

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
					pstmt.setInt(6, budgetRequest.getRequesterId());
					pstmt.setString(7, budgetRequest.getDescription());

					pstmt.executeUpdate();
					System.out.println("예산 신청이 완료되었습니다.");
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
	public void approve(int requestId, int approverId) throws SQLException {
		String sql = "UPDATE budget_request SET status = 'APPROVED', " + "approver_id = ?, approval_date = SYSDATE "
				+ "WHERE budget_request_id = ? AND del_yn = 'N'";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, approverId);
			pstmt.setInt(2, requestId);
			pstmt.executeUpdate();
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
				budgetRequest.setRequesterId(rs.getInt("requester_id"));
				budgetRequest.setApproverId(rs.getInt("approver_id"));
				budgetRequest.setRequestDate(rs.getDate("request_date"));
				budgetRequest.setApprovalDate(rs.getDate("approval_date"));
				budgetRequest.setDescription(rs.getString("description"));

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
			pstmt.setInt(1, requestId); // 먼저 값 바인딩
			try (ResultSet rs = pstmt.executeQuery()) { // 그리고 실행
				while (rs.next()) {
					BudgetRequest budgetRequest = new BudgetRequest();
					budgetRequest.setBudgetRequestId(rs.getInt("budget_request_id"));
					budgetRequest.setDepartmentId(rs.getInt("department_id"));
					budgetRequest.setYear(rs.getInt("year"));
					budgetRequest.setRequestedAmount(rs.getInt("requested_amount"));
					budgetRequest.setCategoryId(rs.getInt("category_id"));
					budgetRequest.setStatus(rs.getString("status"));
					budgetRequest.setRequesterId(rs.getInt("requester_id"));
					budgetRequest.setApproverId(rs.getInt("approver_id"));
					budgetRequest.setRequestDate(rs.getDate("request_date"));
					budgetRequest.setApprovalDate(rs.getDate("approval_date"));
					budgetRequest.setDescription(rs.getString("description"));

					list.add(budgetRequest);
				}
			} catch (SQLException e) {
				System.out.println("SQL 오류 발생: " + e.getMessage());
				System.out.println("SQL 상태: " + e.getSQLState());
				System.out.println("오류 코드: " + e.getErrorCode());
				e.printStackTrace();
			}
		}

		return list;
	}

	// 예산 신청 수정 - 금액과 설명
	public void updateByBudgetRequestId(BudgetRequest budgetRequest) throws SQLException {
		String sql = "UPDATE budget_request SET requested_amount = ?, description = ? WHERE budget_request_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, budgetRequest.getRequestedAmount());
			pstmt.setString(2, budgetRequest.getDescription());
			pstmt.setInt(3, budgetRequest.getBudgetRequestId());

			pstmt.executeUpdate();
			System.out.println("예산 신청이 수정되었습니다.");
		}
	}

	// 예산 신청 소프트딜리트
	public void softDeleteByBudgetRequestId(int requestId) throws SQLException {
		String sql = "UPDATE budget_request SET del_yn = 'Y' WHERE budget_request_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, requestId);

			pstmt.executeUpdate();
			System.out.println("예산 신청이 소프트 삭제되었습니다.");
		}
	}

	// 예산 신청 삭제
	public void deleteByBudgetRequestId(int requestId) throws SQLException {
		String sql = "DELETE FROM budget_request WHERE budget_request_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, requestId);

			pstmt.executeUpdate();
		}
	}
}
