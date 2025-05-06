package budgetAccounting.budgetRequest.model.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class BudgetRequest {
	private int budgetRequestId;
	private int departmentId;
	private int year;
	private int requestedAmount;
	private int categoryId;
	private String status;
	private String requesterId;
	private String approverId;
	private Date requestDate;
	private Date approvalDate;
	private String description;
	private String departmentName;
    private String categoryName;
	
	@Override
	public String toString() {
	    return "\n" +
	           "================ 예산 요청 정보 =================\n" +
	           "{\n" +
	           "  \"요청 ID\"         : " + budgetRequestId + ",\n" +
	           "  \"부서 ID\"         : " + departmentId + ",\n" +
	           "  \"부서명\"           : " + departmentName + ",\n" +
	           "  \"년도\"            : " + year + ",\n" +
	           "  \"요청 금액\"        : " + requestedAmount + ",\n" +
	           "  \"카테고리 ID\"      : " + categoryId + ",\n" +
	           "  \"카테고리명\"        : " + categoryName + ",\n" +
	           "  \"상태\"            : \"" + status + "\",\n" +
	           "  \"요청자 ID\"        : " + requesterId + ",\n" +
	           "  \"승인자 ID\"        : " + approverId + ",\n" +
	           "  \"요청일\"           : " + requestDate + ",\n" +
	           "  \"승인일\"           : " + approvalDate + ",\n" +
	           "  \"설명\"            : \"" + description + "\"\n" +
	           "}\n" +
	           "=================================================\n";
	}
}
