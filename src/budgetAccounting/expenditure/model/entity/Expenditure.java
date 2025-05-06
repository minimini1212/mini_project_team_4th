package budgetAccounting.expenditure.model.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/*
	expenditure_id NUMBER PRIMARY KEY,
    expenditure_request_id NUMBER UNIQUE,
    department_id NUMBER NOT NULL,
    expenditure_date DATE DEFAULT SYSDATE,
    amount NUMBER NOT NULL,
    category_id NUMBER NOT NULL,
    description VARCHAR2(255),
    FOREIGN KEY (expenditure_request_id) REFERENCES expenditure_request(expenditure_request_id),
    FOREIGN KEY (department_id) REFERENCES department(department_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id)      
 */

@Setter
@Getter
public class Expenditure {
	private int expenditureId;
	private int expenditureRequestId;
	private int departmentId;
	private Date expenditureDate;
	private int amount;
	private int categoryId;
	private String description;
	private int year;
	private String departmentName;
    private String categoryName;

	@Override
	public String toString() {
		return String.format("\n" +
						"━━━━━━━  💳 \033[1;36m지출 정보\033[0m 💳 ━━━━━━━\n" +
						"\n" +
						"  🧾 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  📝 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  🏢 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  🏛️ \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  📅 \t\033[1;36m%-10s\033[0m\t: \t\033[0;36m%s\033[0m\n" +
						"  💰 \t\033[1;33m%-10s\033[0m\t: \t\033[1;33m%s\033[0m\n" +
						"  🏷️ \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  📋 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  📝 \t\033[1;35m%-10s\033[0m\t: \t\033[0;35m\"%s\"\033[0m\n" +
						"  📆 \t\033[1;34m%-10s\033[0m\t: \t\033[1;37m\"%s\"\033[0m\n" +
						"\n" +
						"━━━━━━━━━━━━━━━━━━━━━━━━\n",
				" 지출 ID", expenditureId,
				" 지출 신청 ID", expenditureRequestId,
				" 부서 ID", departmentId,
				" 부서명", departmentName,
				" 지출일", expenditureDate,
				" 지출 금액", String.format("%,d", amount),
				" 카테고리 ID", categoryId,
				" 카테고리명", categoryName,
				" 설명", description,
				" 연도", year);
	}
}
