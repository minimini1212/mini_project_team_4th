package budgetAccounting.statistics.model.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeptYearlyBudgetStats {
    private String departmentName;
    private int year;
    private int totalBudget;
    private int totalExpenditure;
    private int budgetRemaining;
    private double usageRatePercent;

    @Override
	public String toString() {
	    return "\n" +
	           "=================== 부서별 통계 =====================\n" +
	           "{\n" +
	           "  \"부서명\"          : " + departmentName + ",\n" +
	           "  \"연도\"            : " + year + ",\n" +
	           "  \"총 예산\"         : " + totalBudget + ",\n" +
	           "  \"총 지출\"         : " + totalExpenditure + ",\n" +
	           "  \"잔여 예산\"        : " + budgetRemaining + ",\n" +
	           "  \"지출 비율 (%)\"    : " + usageRatePercent + ",\n" +
	           "}\n" +
	           "=================================================\n";
	}
}
