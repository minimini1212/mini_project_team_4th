package budgetAccounting.statistics.model.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryYearlyBudgetStats {
    private String categoryName;
    private int year;
    private int totalBudget;
    private int totalExpenditure;
    private double usageRatePercent;

	@Override
	public String toString() {
		return String.format("\n" +
						"━━━━━━  📊 \033[1;36m카테고리별 통계\033[0m 📊 ━━━━━\n" +
						"\n" +
						"  📋 \t\033[1;34m%-7s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  📅 \t\033[1;34m%-7s\033[0m\t: \t\033[1;37m%s\033[0m\n" +
						"  💼 \t\033[1;34m%-7s\033[0m\t: \t\033[1;32m%s\033[0m\n" +
						"  💸 \t\033[1;34m%-7s\033[0m\t: \t\033[1;33m%s\033[0m\n" +
						"  📈 \t\033[1;34m%-7s\033[0m\t: \t\033[1;%sm%s%%\033[0m\n" +
						"\n" +
						"━━━━━━━━━━━━━━━━━━━━━━━━\n",
				" 항목명", categoryName,
				" 연도", year,
				" 총 예산", String.format("%,d", totalBudget),
				" 총 지출", String.format("%,d", totalExpenditure),
				" 지출 비율", getUsageRateColor(usageRatePercent), usageRatePercent);
	}

	// 지출 비율에 따른 색상 코드를 반환하는 메서드
	private String getUsageRateColor(double usageRatePercent) {
		if (usageRatePercent < 70) {
			return "32"; // 녹색 (안전)
		} else if (usageRatePercent < 90) {
			return "33"; // 노란색 (주의)
		} else {
			return "31"; // 빨간색 (위험)
		}
	}
}

