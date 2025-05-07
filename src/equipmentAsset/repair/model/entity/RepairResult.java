package equipmentAsset.repair.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비 수리 결과 정보를 담는 엔티티 클래스
 */
@Getter
@Setter

public class RepairResult {
    private int resultId;            // 결과 ID
    private int requestId;           // 요청 ID
    private String repairContent;    // 수리 내용
    private int repairCost;          // 수리 비용
    private String result;           // 결과 (수리완료, 수리불가)
    private Date lastUpdatedDate;    // 최종 업데이트 일자

    @Override
    public String toString() {
        return String.format("\n" +
                        "━━━━━━  🔨 \033[1;36m수리 결과 정보\033[0m 🔨 ━━━━━━\n" +
                        "\n" +
                        "  🔑 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🛠️ \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  📋 \t\033[1;35m%-10s\033[0m\t: \t\033[0;35m\"%s\"\033[0m\n" +
                        "  💰 \t\033[1;33m%-10s\033[0m\t: \t\033[1;33m%s\033[0m\n" +
                        "  ✅ \t\033[1;34m%-10s\033[0m\t: \t\033[1;%sm\"%s\"\033[0m\n" +
                        "  🕒 \t\033[1;36m%-10s\033[0m\t: \t\033[0;36m%s\033[0m\n" +
                        "\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━\n",
                " 결과 ID", resultId,
                " 요청 ID", requestId,
                " 수리 내용", repairContent != null ? repairContent : "미입력",
                " 수리 비용", repairCost > 0 ? String.format("%,d", repairCost) : "미입력",
                " 결과", getRepairResultColor(result), result != null ? result : "미입력",
                " 최종수정일", lastUpdatedDate != null ? lastUpdatedDate.toString() : "미입력");
    }

    // 수리 결과에 따른 색상 코드를 반환하는 메서드
    private String getRepairResultColor(String result) {
        if (result == null) return "37"; // 기본 흰색

        switch (result.toUpperCase()) {
            case "수리완료":
                return "32"; // 녹색
            case "수리불가":
                return "31"; // 빨간색
            case "부품교체":
                return "33"; // 노란색
            default:
                return "37"; // 기본 흰색
        }
    }
}