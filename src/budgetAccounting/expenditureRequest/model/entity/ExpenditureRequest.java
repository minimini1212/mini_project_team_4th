package budgetAccounting.expenditureRequest.model.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExpenditureRequest {

	private int expenditureRequestId;
	private int departmentId;
	private Date requestDate;
	private int requesterId;
	private int amount;
	private int categoryId;
	private String description;
	private String status;
	private int approverId;
	private Date approvalDate;
	private int year;
	
	@Override
	public String toString() {
	    return "\n" +
	           "================ 지출 요청 정보 =================\n" +
	           "{\n" +
	           "  \"요청 ID\"         : " + expenditureRequestId + ",\n" +
	           "  \"부서 ID\"         : " + departmentId + ",\n" +
	           "  \"년도\"            : " + year + ",\n" +
	           "  \"요청 금액\"       : " + amount + ",\n" +
	           "  \"카테고리 ID\"     : " + categoryId + ",\n" +
	           "  \"상태\"            : \"" + status + "\",\n" +
	           "  \"요청자 ID\"       : " + requesterId + ",\n" +
	           "  \"승인자 ID\"       : " + approverId + ",\n" +
	           "  \"요청일\"          : " + requestDate + ",\n" +
	           "  \"승인일\"          : " + approvalDate + ",\n" +
	           "  \"설명\"            : \"" + description + "\"\n" +
	           "}\n" +
	           "=================================================\n";
	}
}
