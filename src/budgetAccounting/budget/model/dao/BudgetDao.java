package budgetAccounting.budget.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import budgetAccounting.budget.model.entity.Budget;

public class BudgetDao {
	private Connection conn;

	public BudgetDao(Connection conn) {
		this.conn = conn;
	}

	// 예산 생성
	public void insertBudget(Budget budget) throws SQLException {
		String sql = "INSERT INTO budget (" 
				+ "budget_id, budget_request_id, department_id, "
				+ "year, budget_amount, category_id, description, " 
				+ "remaining_budget"
				+ ") "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		int sequence = getNextBudgetId();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, sequence);
			
			System.out.println(sequence);

			if (budget.getBudgetRequestId() == 0) {
				pstmt.setNull(2, Types.INTEGER);
			} else {
				pstmt.setInt(2, budget.getBudgetRequestId());
			}
			pstmt.setInt(3, budget.getDepartmentId());
			pstmt.setInt(4, budget.getYear());
			pstmt.setInt(5, budget.getBudgetAmount());
			pstmt.setInt(6, budget.getCategoryId());
			pstmt.setString(7, budget.getDescription());
			pstmt.setInt(8, budget.getRemainingBudget());
			
			pstmt.executeUpdate();
			System.out.println("예산이 등록되었습니다.");

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("해당 부서에 이미 동일한 항목이 존재합니다.");
		} 
	}

	// 예산 전체 조회
	public List<Budget> findAllBudget() throws SQLException {

		String sql = "SELECT * FROM budget WHERE del_yn IN ('N', 'n')";
		List<Budget> list = new ArrayList<>();

		if (conn == null || conn.isClosed()) {
			throw new SQLException("DB 연결이 유효하지 않습니다.");
		}

		// try-with-resources 문법을 사용 -> 자원을 자동으로 닫도록 설정
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Budget budget = new Budget();
				budget.setBudgetId(rs.getInt("budget_id"));
				budget.setDepartmentId(rs.getInt("department_id"));
				budget.setYear(rs.getInt("year"));
				budget.setBudgetAmount(rs.getInt("budget_amount"));
				budget.setCategoryId(rs.getInt("category_id"));
				budget.setDescription(rs.getString("description"));
				budget.setRemainingBudget(rs.getInt("remaining_budget"));

				list.add(budget);

			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 발생: " + e.getMessage());
			System.out.println("SQL 상태: " + e.getSQLState());
			System.out.println("오류 코드: " + e.getErrorCode());
			e.printStackTrace();
		}

		return list;
	}

	// 예산 부분 조회
	public List<Budget> findByBudgetId(int requestId) throws SQLException {
		String sql = "SELECT * FROM budget WHERE budget_id = ? AND del_yn IN ('N', 'n')";
		List<Budget> list = new ArrayList<>();

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, requestId);
			try (ResultSet rs = pstmt.executeQuery()) {

				boolean hasData = false;
				while (rs.next()) {
					hasData = true;
					Budget budget = new Budget();
					budget.setBudgetId(rs.getInt("budget_id"));
					budget.setDepartmentId(rs.getInt("department_id"));
					budget.setYear(rs.getInt("year"));
					budget.setBudgetAmount(rs.getInt("budget_amount"));
					budget.setCategoryId(rs.getInt("category_id"));
					budget.setDescription(rs.getString("description"));
					budget.setRemainingBudget(rs.getInt("remaining_budget"));
					list.add(budget);
				}

				if (!hasData) {
					throw new SQLException("해당 조건에 맞는 지출 신청이 존재하지 않습니다.");
				}
			}
		}


		return list;
	}

	// 특정 예산 수정
	public void updateByBudgetId(Budget budget, int requestId) throws SQLException {
		String sql = "UPDATE budget SET budget_amount = ?, description = ? WHERE budget_id = ?";

		String selectSql = "SELECT * FROM budget WHERE budget_id = ? AND del_yn IN ('N', 'n')";

		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				PreparedStatement pstmt1 = conn.prepareStatement(selectSql)) {
			pstmt1.setInt(1, requestId);

			try (ResultSet rs = pstmt1.executeQuery()) {
				if (!rs.next()) {
					throw new SQLException("해당 조건에 맞는 지출 신청이 존재하지 않습니다.");
				}

				pstmt.setInt(1, budget.getBudgetAmount());
				pstmt.setString(2, budget.getDescription());
				pstmt.setInt(3, budget.getBudgetRequestId());

				pstmt.executeUpdate();
				System.out.println("예산이 수정되었습니다.");
			}

		}
	}

	// 예산 소프트딜리트
	public void softDeleteByBudgetId(int requestId) throws SQLException {
		String sql = "UPDATE budget SET del_yn = 'Y' WHERE budget_id = ?";
		String selectSql = "SELECT * FROM budget WHERE budget_id = ? AND del_yn IN ('N', 'n')";

		try (PreparedStatement pstmt = conn.prepareStatement(sql);
				PreparedStatement pstmt1 = conn.prepareStatement(selectSql)) {
			pstmt1.setInt(1, requestId);

			try (ResultSet rs = pstmt1.executeQuery()) {
				if (!rs.next()) {
					throw new SQLException("해당 조건에 맞는 지출 신청이 존재하지 않습니다.");
				}

				pstmt.setInt(1, requestId);

				pstmt.executeUpdate();
				System.out.println("예산이 소프트 삭제되었습니다.");
			}

		}
	}

	// 예산 신청테이블에서 승인된 예산 신청서를 예산 테이블에 넣을 때 쓸 예산 기본키 가져오는 메서드
	public int getNextBudgetId() throws SQLException {
		String sql = "SELECT SEQ_BUDGET_ID.NEXTVAL id FROM DUAL";
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				throw new SQLException("시퀀스 값을 가져오지 못했습니다.");
			}
		}
	}

}