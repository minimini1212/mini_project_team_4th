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
	    return "\n" +
	           "=================== 연도별 통계 =====================\n" +
	           "{\n" +
	           "  \"항목명\"          : " + categoryName + ",\n" +
	           "  \"연도\"            : " + year + ",\n" +
	           "  \"총 예산\"         : " + totalBudget + ",\n" +
	           "  \"총 지출\"         : " + totalExpenditure + ",\n" +
	           "  \"지출 비율 (%)\"    : " + usageRatePercent + ",\n" +
	           "}\n" +
	           "=================================================\n";
	}
}

