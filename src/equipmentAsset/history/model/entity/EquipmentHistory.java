package equipmentAsset.history.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비 이력 통합 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class EquipmentHistory {
    private int historyId;            // 이력 ID
    private int equipmentId;          // 장비 ID
    private String historyType;       // 이력 유형 (점검, 수리요청, 수리진행, 수리완료, 폐기 등)
    private Date occurrenceDate;      // 발생일
    private String description;       // 설명
    private Integer relatedId;        // 관련 ID (점검결과ID, 수리요청ID 등) (null 가능)

    @Override
    public String toString() {
        return String.format("\n" +
                        "━━━━━━  📜 \033[1;36m장비 이력 정보\033[0m 📜 ━━━━━\n" +
                        "\n" +
                        "  🔑 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🔧 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🔄 \t\033[1;34m%-10s\033[0m\t: \t\033[1;%sm\"%s\"\033[0m\n" +
                        "  📅 \t\033[1;34m%-10s\033[0m\t: \t\033[1;37m%s\033[0m\n" +
                        "  📝 \t\033[1;35m%-10s\033[0m\t: \t\033[0;35m\"%s\"\033[0m\n" +
                        "  🔗 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━\n",
                " 이력 ID", historyId,
                " 장비 ID", equipmentId,
                " 이력 유형", getHistoryTypeColor(historyType), historyType != null ? historyType : "미입력",
                " 발생일", occurrenceDate != null ? occurrenceDate.toString() : "미입력",
                " 설명", description != null ? description : "미입력",
                " 관련 ID", relatedId != null ? relatedId : "해당 없음");
    }

    // 이력 유형에 따른 색상 코드를 반환하는 메서드
    private String getHistoryTypeColor(String historyType) {
        if (historyType == null) return "37"; // 기본 흰색

        switch (historyType.toUpperCase()) {
            case "점검":
                return "36"; // 청록색
            case "수리요청":
                return "33"; // 노란색
            case "수리진행":
                return "35"; // 자주색
            case "수리완료":
                return "32"; // 녹색
            case "폐기":
                return "90"; // 회색
            default:
                return "37"; // 기본 흰색
        }
    }
}