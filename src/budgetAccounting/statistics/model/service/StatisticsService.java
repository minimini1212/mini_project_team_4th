package budgetAccounting.statistics.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import budgetAccounting.expenditure.model.dao.ExpenditureDao;
import budgetAccounting.statistics.model.dao.StatisticsDao;
import budgetAccounting.statistics.model.entity.CategoryYearlyBudgetStats;
import budgetAccounting.statistics.model.entity.DeptYearlyBudgetStats;
import budgetAccounting.statistics.model.entity.YearlyBudgetStats;
import dbConn.ConnectionSingletonHelper;

public class StatisticsService {
	private StatisticsDao statisticsDao;
	private Connection conn;

	// 연도별
	public List<YearlyBudgetStats> getBudgetGroupByYear() throws SQLException {

		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			statisticsDao = new StatisticsDao(conn);
			return statisticsDao.findBudgetGroupByYear();
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

	// 부서별
	public List<DeptYearlyBudgetStats> getBudgetGroupByDepartment() throws SQLException {

		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			statisticsDao = new StatisticsDao(conn);
			return statisticsDao.findBudgetGroupByDepartment();
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

	// 카테고리별
	public List<CategoryYearlyBudgetStats> getBudgetGroupByCategory() throws SQLException {

		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			statisticsDao = new StatisticsDao(conn);
			return statisticsDao.findBudgetGroupByCategory();
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

}
