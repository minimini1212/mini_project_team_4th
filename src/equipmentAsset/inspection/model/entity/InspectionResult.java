package equipmentAsset.inspection.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비 점검 결과 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class InspectionResult {
    private int resultId;                // 결과 ID
    private int scheduleId;              // 점검 일정 ID
    private Date inspectionDate;         // 점검 실시일
    private String inspectionResult;     // 점검 결과 (양호, 조치필요 등)
    private String inspectionContent;    // 점검 내용
    private String specialNote;          // 특이사항
    private Date lastUpdatedDate;        // 최종 업데이트 일자

    // InspectionResult 클래스의 toString()
    @Override
    public String toString() {
        return String.format("\n" +
                        "━━━━━━  🔍 \033[1;36m점검 결과 정보\033[0m 🔍 ━━━━━━\n" +
                        "\n" +
                        "  🔑 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  📌 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🗓️ \t\033[1;34m%-10s\033[0m\t: \t\033[1;37m%s\033[0m\n" +
                        "  ✅ \t\033[1;34m%-10s\033[0m\t: \t\033[1;%sm\"%s\"\033[0m\n" +
                        "  📋 \t\033[1;35m%-10s\033[0m\t: \t\033[0;35m\"%s\"\033[0m\n" +
                        "  📝 \t\033[1;35m%-10s\033[0m\t: \t\033[0;35m\"%s\"\033[0m\n" +
                        "  🕒 \t\033[1;36m%-10s\033[0m\t: \t\033[0;36m%s\033[0m\n" +
                        "\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━\n",
                " 결과 ID", resultId,
                " 일정 ID", scheduleId,
                " 점검 실시일", inspectionDate != null ? inspectionDate.toString() : "미입력",
                " 점검 결과", getInspectionResultColor(inspectionResult), inspectionResult != null ? inspectionResult : "미입력",
                " 점검 내용", inspectionContent != null ? inspectionContent : "미입력",
                " 특이사항", specialNote != null ? specialNote : "없음",
                " 최종수정일", lastUpdatedDate != null ? lastUpdatedDate.toString() : "미입력");
    }

    // 점검 결과에 따른 색상 코드를 반환하는 메서드
    private String getInspectionResultColor(String inspectionResult) {
        if (inspectionResult == null) return "37"; // 기본 흰색

        switch (inspectionResult.toUpperCase()) {
            case "양호":
            case "정상":
                return "32"; // 녹색
            case "조치필요":
            case "점검필요":
                return "33"; // 노란색
            case "수리필요":
            case "교체필요":
                return "31"; // 빨간색
            default:
                return "37"; // 기본 흰색
        }
    }
}
