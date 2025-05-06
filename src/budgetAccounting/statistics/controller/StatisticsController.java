package budgetAccounting.statistics.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import budgetAccounting.statistics.model.entity.CategoryYearlyBudgetStats;
import budgetAccounting.statistics.model.entity.DeptYearlyBudgetStats;
import budgetAccounting.statistics.model.entity.YearlyBudgetStats;
import budgetAccounting.statistics.model.service.StatisticsService;
import budgetAccounting.statistics.view.StatisticsView;

public class StatisticsController {

	private StatisticsService statisticsService;
	private StatisticsView statisticsView = new StatisticsView();

	public StatisticsController() {
		this.statisticsService = new StatisticsService();
	}

	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean running = true;

		while (running) {
			statisticsView.menu();
			int choice = sc.nextInt();

			try {
				switch (choice) {
				case 1:
					listStatsByYear();
					break;
				case 2:
					listStatsByDepartment();
					break;
				case 3:
					listStatsByCategory();
					break;
				case 0:
					running = false;
					break;
				default:
					System.out.println("잘못된 입력입니다.");
				}
			} catch (SQLException e) {
				System.err.println("오류: " + e.getMessage());
			}
		}

		sc.close();
	}

	// 연도별
	private void listStatsByYear() throws SQLException {
		List<YearlyBudgetStats> statisticss = statisticsService.getBudgetGroupByYear();
		System.out.println("\n[부서별 통계]");
		for (YearlyBudgetStats br : statisticss) {
			System.out.println(br);
		}
	}

	// 부서별
	private void listStatsByDepartment() throws SQLException {
		List<DeptYearlyBudgetStats> statisticss = statisticsService.getBudgetGroupByDepartment();
		System.out.println("\n[부서별 통계]");
		for (DeptYearlyBudgetStats br : statisticss) {
			System.out.println(br);
		}
	}

	// 카테고리별
	private void listStatsByCategory() throws SQLException {
		List<CategoryYearlyBudgetStats> statisticss = statisticsService.getBudgetGroupByCategory();
		System.out.println("\n[부서별 통계]");
		for (CategoryYearlyBudgetStats br : statisticss) {
			System.out.println(br);
		}
	}

}
