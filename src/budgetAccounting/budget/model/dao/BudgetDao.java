package budgetAccounting.budget.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import budgetAccounting.budget.model.entity.Budget;

public class BudgetDao {
	private Connection conn;

	public BudgetDao(Connection conn) {
		this.conn = conn;
	}

	public void insertBudget(Budget budget) throws SQLException {
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

	public void findAllBudget(Budget budget) throws SQLException {
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

}
