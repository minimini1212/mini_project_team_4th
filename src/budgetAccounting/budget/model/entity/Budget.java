package budgetAccounting.budget.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
	CREATE TABLE budget (
    budget_id NUMBER PRIMARY KEY,                            -- 예산 ID
    department_id NUMBER NOT NULL,                           -- 부서 ID
    year NUMBER(4) NOT NULL,                                 -- 연도
    budget_amount NUMBER NOT NULL,                           -- 금액
    category_id NUMBER NOT NULL,                             -- 예산 항목 ID
    description VARCHAR2(255),
    FOREIGN KEY (department_id) REFERENCES department(department_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);
 */

@Setter
@Getter
@ToString
public class Budget {
	private int budgetId;
	private int departmentId;
	private int year;
	private int budgetAmount;
	private int categoryId;
	private String description;
	
}
