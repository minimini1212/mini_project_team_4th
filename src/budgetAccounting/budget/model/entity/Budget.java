package budgetAccounting.budget.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class Budget {
	private int budgetId;
	private int budgetRequestId;
	private int departmentId;
	private int year;
	private int budgetAmount;
	private int categoryId;
	private String description;
	private int remainingBudget;
    private String departmentName;
    private String categoryName;
	
	@Override
	public String toString() {
	    return "\n" +
	           "================   예산    정보   =================\n" +
	           "{\n" +
	           "  \"예산 ID\"         : " + budgetId + ",\n" +
	           "  \"부서 ID\"         : " + departmentId + ",\n" +
	           "  \"부서명\"           : " + departmentName + ",\n" +
	           "  \"년도\"            : " + year + ",\n" +
	           "  \"예산 금액\"        : " + budgetAmount + ",\n" +
	           "  \"잔여 예산\"        : " + remainingBudget + ",\n" +
	           "  \"카테고리 ID\"      : " + categoryId + ",\n" +
	           "  \"카테고리명  \"      : " + categoryName + ",\n" +
	           "  \"설명\"            : \"" + description + "\"\n" +
	           "}\n" +
	           "=================================================\n";
	}
}
