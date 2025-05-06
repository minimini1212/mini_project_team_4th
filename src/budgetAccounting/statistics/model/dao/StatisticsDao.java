package budgetAccounting.statistics.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import budgetAccounting.statistics.model.entity.CategoryYearlyBudgetStats;
import budgetAccounting.statistics.model.entity.DeptYearlyBudgetStats;
import budgetAccounting.statistics.model.entity.YearlyBudgetStats;

public class StatisticsDao {
	private Connection conn;

	public StatisticsDao(Connection conn) {
		this.conn = conn;
	}

	// 연도별 조회
	public List<YearlyBudgetStats> findBudgetGroupByYear() throws SQLException {

		String sql = "SELECT * FROM view_yearly_budget_stats";
		List<YearlyBudgetStats> list = new ArrayList<>();

		if (conn == null || conn.isClosed()) {
			throw new SQLException("DB 연결이 유효하지 않습니다.");
		}

		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				YearlyBudgetStats stats = new YearlyBudgetStats();
				stats.setYear(rs.getInt("year"));
				stats.setTotalBudget(rs.getInt("total_budget"));
				stats.setTotalExpenditure(rs.getInt("total_expenditure"));
				stats.setBudgetRemaining(rs.getInt("budget_remaining"));
				stats.setUsageRatePercent(rs.getDouble("usage_rate_percent"));

				list.add(stats);

			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 발생: " + e.getMessage());
			System.out.println("SQL 상태: " + e.getSQLState());
			System.out.println("오류 코드: " + e.getErrorCode());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("오류가 생겼습니다." + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	// 부서별 조회
	public List<DeptYearlyBudgetStats> findBudgetGroupByDepartment() throws SQLException {
		String sql = "SELECT * FROM view_dept_yearly_budget_stats";
		List<DeptYearlyBudgetStats> list = new ArrayList<>();

		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				DeptYearlyBudgetStats stats = new DeptYearlyBudgetStats();
				stats.setDepartmentName(rs.getString("department_name"));
				stats.setYear(rs.getInt("year"));
				stats.setTotalBudget(rs.getInt("total_budget"));
				stats.setTotalExpenditure(rs.getInt("total_expenditure"));
				stats.setBudgetRemaining(rs.getInt("budget_remaining"));
				stats.setUsageRatePercent(rs.getDouble("usage_rate_percent"));

				list.add(stats);

			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 발생: " + e.getMessage());
			System.out.println("SQL 상태: " + e.getSQLState());
			System.out.println("오류 코드: " + e.getErrorCode());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("오류가 생겼습니다." + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

	// 카테고리별 조회
	public List<CategoryYearlyBudgetStats> findBudgetGroupByCategory() throws SQLException {
		String sql = "SELECT * FROM view_category_year_bg_stats";
		List<CategoryYearlyBudgetStats> list = new ArrayList<>();

		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				CategoryYearlyBudgetStats stats = new CategoryYearlyBudgetStats();
				stats.setCategoryName(rs.getString("category_name"));
				stats.setYear(rs.getInt("year"));
				stats.setTotalBudget(rs.getInt("total_budget"));
				stats.setTotalExpenditure(rs.getInt("total_expenditure"));
				stats.setUsageRatePercent(rs.getDouble("usage_rate_percent"));

				list.add(stats);

			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 발생: " + e.getMessage());
			System.out.println("SQL 상태: " + e.getSQLState());
			System.out.println("오류 코드: " + e.getErrorCode());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("오류가 생겼습니다." + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}

}
