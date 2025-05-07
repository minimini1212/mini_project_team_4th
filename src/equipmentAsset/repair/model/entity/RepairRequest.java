package equipmentAsset.repair.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비 수리 요청 정보를 담는 엔티티 클래스
 */
@Getter
@Setter

public class RepairRequest {
    private int requestId;           // 요청 ID
    private int equipmentId;         // 장비 ID
    private Date requestDate;        // 요청일
    private String failureSymptom;   // 고장 증상
    private String status;           // 상태 (접수, 수리중, 완료, 폐기결정 등)
    private Date lastUpdatedDate;    // 최종 업데이트 일자

    @Override
    public String toString() {
        return String.format("\n" +
                        "━━━━━━  🛠️ \033[1;36m수리 요청 정보\033[0m 🛠️ ━━━━━\n" +
                        "\n" +
                        "  🔑 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🔧 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  📅 \t\033[1;34m%-10s\033[0m\t: \t\033[1;37m%s\033[0m\n" +
                        "  ⚠️ \t\033[1;35m%-10s\033[0m\t: \t\033[0;35m\"%s\"\033[0m\n" +
                        "  🚦 \t\033[1;31m%-10s\033[0m\t: \t\033[1;%sm\"%s\"\033[0m\n" +
                        "  🕒 \t\033[1;36m%-10s\033[0m\t: \t\033[0;36m%s\033[0m\n" +
                        "\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━\n",
                " 요청 ID", requestId,
                " 장비 ID", equipmentId,
                " 요청일", requestDate != null ? requestDate.toString() : "미입력",
                " 고장 증상", failureSymptom != null ? failureSymptom : "미입력",
                " 상태", getRepairStatusColor(status), status != null ? status : "미입력",
                " 최종수정일", lastUpdatedDate != null ? lastUpdatedDate.toString() : "미입력");
    }

    // 수리 요청 상태에 따른 색상 코드를 반환하는 메서드
    private String getRepairStatusColor(String status) {
        if (status == null) return "37"; // 기본 흰색

        switch (status.toUpperCase()) {
            case "접수":
            case "접수됨":
                return "36"; // 청록색
            case "수리중":
                return "33"; // 노란색
            case "완료":
            case "수리완료":
                return "32"; // 녹색
            case "폐기결정":
                return "35"; // 자주색
            case "폐기완료":
                return "90"; // 회색
            default:
                return "37"; // 기본 흰색
        }
    }

}
