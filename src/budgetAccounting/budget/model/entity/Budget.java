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
	private int remainingAmount;
    private String departmentName;
    private String categoryName;

	@Override
	public String toString() {
		return String.format("\n" +
						"━━━━━━━  📊 \033[1;36m예산 정보\033[0m 📊 ━━━━━━━\n" +
						"\n" +
						"  🔑 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  🏢 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  🏛️ \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  📅 \t\033[1;34m%-10s\033[0m\t: \t\033[1;37m%s\033[0m\n" +
						"  💵 \t\033[1;33m%-10s\033[0m\t: \t\033[1;33m%s\033[0m\n" +
						"  💰 \t\033[1;32m%-10s\033[0m\t: \t\033[1;32m%s\033[0m\n" +
						"  🏷️ \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  📋 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  📝 \t\033[1;35m%-10s\033[0m\t: \t\033[0;35m\"%s\"\033[0m\n" +
						"\n" +
						"━━━━━━━━━━━━━━━━━━━━━━━━\n",
				" 예산 ID", budgetId,
				" 부서 ID", departmentId,
				" 부서명", departmentName,
				" 년도", year,
				" 예산 금액", String.format("%,d", budgetAmount),
				" 잔여 예산", String.format("%,d", remainingAmount),
				" 카테고리 ID", categoryId,
				" 카테고리명", categoryName,
				" 설명", description);
	}
}
