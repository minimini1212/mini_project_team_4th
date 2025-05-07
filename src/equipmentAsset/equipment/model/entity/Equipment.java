package equipmentAsset.equipment.model.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 장비 마스터 정보를 담는 엔티티 클래스
 */

@Getter
@Setter

public class Equipment {
	private int equipmentId; // 장비 ID
	private String equipmentName; // 장비명
	private String modelName; // 모델명
	private String manufacturer; // 제조사
	private String serialNumber; // 시리얼 번호
	private Date purchaseDate; // 구매일
	private int purchasePrice; // 구매가격
	private int categoryId; // 카테고리 ID
	private int departmentId; // 부서 ID
	private int managerId; // 담당자 ID
	private String status; // 상태 (정상, 점검필요, 수리중, 폐기예정, 폐기완료)
	private String description; // 설명
	private Date lastUpdatedDate; // 최종 업데이트 일자
	private String categoryName;
	private String departmentName;
	private String managerName;    // 담당자 이름
	private String positionName;   // 직위 이름
	private String jobName;        // 직무 이름


	@Override
	public String toString() {
		return String.format("\n" +
						"━━━━━━  🔧 \033[1;36m장비 상세 정보\033[0m 🔧 ━━━━━\n" +
						"\n" +
						"  🔑 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  📝 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  📋 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  🏭 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  🔢 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  📅 \t\033[1;34m%-10s\033[0m\t: \t\033[1;37m%s\033[0m\n" +
						"  💰 \t\033[1;33m%-10s\033[0m\t: \t\033[1;33m%s\033[0m\n" +
						"  🏷️ \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  🏢 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  👤 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  💼 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  🛠️ \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
						"  🚦 \t\033[1;31m%-10s\033[0m\t: \t\033[1;%sm\"%s\"\033[0m\n" +
						"  📄 \t\033[1;35m%-10s\033[0m\t: \t\033[0;35m\"%s\"\033[0m\n" +
						"  🕒 \t\033[1;36m%-10s\033[0m\t: \t\033[0;36m%s\033[0m\n" +
						"\n" +
						"━━━━━━━━━━━━━━━━━━━━━━━━\n",
				" 장비 ID", equipmentId,
				" 장비명", equipmentName != null ? equipmentName : "미입력",
				" 모델명", modelName != null ? modelName : "미입력",
				" 제조사", manufacturer != null ? manufacturer : "미입력",
				" 시리얼번호", serialNumber != null ? serialNumber : "미입력",
				" 구매일", purchaseDate != null ? purchaseDate.toString() : "미입력",
				" 구매가격", purchasePrice > 0 ? String.format("%,d", purchasePrice) : "미입력",
				" 카테고리", categoryName != null ? categoryName : "미입력",
				" 부서", departmentName != null ? departmentName : "미입력",
				" 담당자", managerName != null ? managerName : "미입력",
				" 직위", positionName != null ? positionName : "미입력",
				" 직무", jobName != null ? jobName : "미입력",
				" 상태", getStatusColor(status), status != null ? status : "미입력",
				" 설명", description != null ? description : "미입력",
				" 최종수정일", lastUpdatedDate != null ? lastUpdatedDate.toString() : "미입력");
	}

	// 상태에 따른 색상 코드를 반환하는 메서드
	private String getStatusColor(String status) {
		if (status == null) return "37"; // 기본 흰색

		switch (status.toUpperCase()) {
			case "정상":
				return "32"; // 녹색
			case "점검필요":
				return "33"; // 노란색
			case "수리필요":
			case "수리중":
				return "31"; // 빨간색
			case "폐기예정":
				return "35"; // 자주색
			case "폐기완료":
				return "90"; // 회색
			default:
				return "37"; // 기본 흰색
		}
	}


}
