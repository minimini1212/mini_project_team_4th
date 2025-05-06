package budgetAccounting.statistics.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import budgetAccounting.statistics.model.dao.StatisticsDao;
import budgetAccounting.statistics.model.entity.CategoryYearlyBudgetStats;
import budgetAccounting.statistics.model.entity.DeptYearlyBudgetStats;
import budgetAccounting.statistics.model.entity.YearlyBudgetStats;

public class StatisticsService {
	private final StatisticsDao statisticsDao;

	public StatisticsService(Connection conn) {
		this.statisticsDao = new StatisticsDao(conn);
	}

	// 연도별
	public List<YearlyBudgetStats> getBudgetGroupByYear() throws SQLException {
		return statisticsDao.findBudgetGroupByYear();
	}

	// 부서별
	public List<DeptYearlyBudgetStats> getBudgetGroupByDepartment() throws SQLException {
		return statisticsDao.findBudgetGroupByDepartment();
	}

	// 카테고리별
	public List<CategoryYearlyBudgetStats> getBudgetGroupByCategory() throws SQLException {
		return statisticsDao.findBudgetGroupByCategory();
	}

}
