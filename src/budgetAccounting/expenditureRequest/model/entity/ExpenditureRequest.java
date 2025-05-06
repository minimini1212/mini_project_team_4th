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
	private String requesterId;
	private int amount;
	private int categoryId;
	private String description;
	private String status;
	private String approverId;
	private Date approvalDate;
	private int year;
	private String departmentName;
    private String categoryName;

	@Override
	public String toString() {
		return String.format("\n" +
						"━━━━━━  📝 \033[1;36m지출 요청 정보\033[0m 📝  ━━━━━\n" +
						"\n" +
						"  🔖 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  🏢 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  🏛️ \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  📅 \t\033[1;34m%-10s\033[0m\t: \t\033[1;37m%s\033[0m\n" +
						"  💰 \t\033[1;33m%-10s\033[0m\t: \t\033[1;33m%s\033[0m\n" +
						"  🏷️ \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  📋 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  🚦 \t\033[1;31m%-10s\033[0m\t: \t\033[1;%sm\"%s\"\033[0m\n" +
						"  👤 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  ✅ \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  📮 \t\033[1;36m%-10s\033[0m\t: \t\033[0;36m%s\033[0m\n" +
						"  📝 \t\033[1;36m%-10s\033[0m\t: \t\033[0;36m%s\033[0m\n" +
						"  📄 \t\033[1;35m%-10s\033[0m\t: \t\033[0;35m\"%s\"\033[0m\n" +
						"\n" +
						"━━━━━━━━━━━━━━━━━━━━━━━━\n",
				" 요청 ID", expenditureRequestId,
				" 부서 ID", departmentId,
				" 부서명", departmentName,
				" 년도", year,
				" 요청 금액", String.format("%,d", amount),
				" 카테고리 ID", categoryId,
				" 카테고리명", categoryName,
				" 상태", getStatusColor(status), status,
				" 요청자 ID", requesterId,
				" 승인자 ID", approverId,
				" 요청일", requestDate,
				" 승인일", approvalDate,
				" 설명", description);
	}

	// 상태에 따른 색상 코드를 반환하는 메서드
	private String getStatusColor(String status) {
		switch (status.toUpperCase()) {
			case "대기중":
			case "PENDING":
				return "33"; // 노란색
			case "승인됨":
			case "APPROVED":
				return "32"; // 녹색
			case "거부됨":
			case "REJECTED":
				return "31"; // 빨간색
			default:
				return "37"; // 기본 흰색
		}
	}
}
