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

	@Override
	public String toString() {
	    return "\n" +
	           "=================== 지출 정보 =====================\n" +
	           "{\n" +
	           "  \"지출 ID\"         : " + expenditureId + ",\n" +
	           "  \"지출 신청 ID\"     : " + expenditureRequestId + ",\n" +
	           "  \"부서 ID\"         : " + departmentId + ",\n" +
	           "  \"지출일\"           : " + expenditureDate + ",\n" +
	           "  \"지출 금액\"        : " + amount + ",\n" +
	           "  \"카테고리 ID\"      : " + categoryId + ",\n" +
	           "  \"설명\"            : \"" + description + "\"\n" +
	           "  \"연도\"            : \"" + year + "\"\n" +
	           "}\n" +
	           "=================================================\n";
	}
}
