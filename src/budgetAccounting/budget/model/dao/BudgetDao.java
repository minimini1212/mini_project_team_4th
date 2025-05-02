package budgetAccounting.budget.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import budgetAccounting.budget.model.entity.Budget;

public class BudgetDao {
	private Connection conn;
	private int sequence;

	public BudgetDao(Connection conn) {
		this.conn = conn;
	}

	// 예산 생성
	public void insertBudget(Budget budget) throws SQLException {
		String sql = "INSERT INTO budget (budget_id, budget_request_id, department_id, year, budget_amount, category_id, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String sequenceSql = "SELECT SEQ_BUDGET_ID.NEXTVAL id FROM DUAL";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sequenceSql)) {
			if (rs.next()) {
				sequence = rs.getInt(1);
			}
		}
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, sequence);
			pstmt.setInt(2, budget.getBudgetRequestId());
			pstmt.setInt(3, budget.getDepartmentId());
			pstmt.setInt(4, budget.getYear());
			pstmt.setInt(5, budget.getBudgetAmount());
			pstmt.setInt(6, budget.getCategoryId());
			pstmt.setString(7, budget.getDescription());
			
			pstmt.executeUpdate();
		}
	}

	// 예산 전체 조회
	public void findAllBudget(Budget budget) throws SQLException {
		String sql = "SELECT * from budget";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, budget.getBudgetId());
			pstmt.setInt(2, budget.getBudgetRequestId());
			pstmt.setInt(3, budget.getDepartmentId());
			pstmt.setInt(4, budget.getYear());
			pstmt.setInt(5, budget.getBudgetAmount());
			pstmt.setInt(6, budget.getCategoryId());
			pstmt.setString(7, budget.getDescription());

			pstmt.executeUpdate();
		}
	}

	// 예산 부분 조회
	public void findBudgetById(Budget budget) throws SQLException {
		String sql = "SELECT * from budget";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, budget.getBudgetId());
			pstmt.setInt(2, budget.getDepartmentId());
			pstmt.setInt(3, budget.getYear());
			pstmt.setInt(4, budget.getBudgetAmount());
			pstmt.setInt(5, budget.getCategoryId());
			pstmt.setString(6, budget.getDescription());

			pstmt.executeUpdate();
		}
	}

	// 예산 수정 
	public void updateBudget(Budget budget) throws SQLException {
		String sql = "INSERT INTO budget (budget_id, department_id, year, budget_amount, category_id, description) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, budget.getBudgetId());
			pstmt.setInt(2, budget.getDepartmentId());
			pstmt.setInt(3, budget.getYear());
			pstmt.setInt(4, budget.getBudgetAmount());
			pstmt.setInt(5, budget.getCategoryId());
			pstmt.setString(6, budget.getDescription());

			pstmt.executeUpdate();
		}
	}

	// 예산 삭제
	public void deleteBudget(Budget budget) throws SQLException {
		String sql = "INSERT INTO budget (budget_id, department_id, year, budget_amount, category_id, description) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, budget.getBudgetId());
			pstmt.setInt(2, budget.getDepartmentId());
			pstmt.setInt(3, budget.getYear());
			pstmt.setInt(4, budget.getBudgetAmount());
			pstmt.setInt(5, budget.getCategoryId());
			pstmt.setString(6, budget.getDescription());

			pstmt.executeUpdate();
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
