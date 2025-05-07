package equipmentAsset.inspection.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비 점검 일정 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class InspectionSchedule {
    private int scheduleId;           // 일정 ID
    private int equipmentId;          // 장비 ID
    private String inspectionType;    // 점검 유형 (정기점검, 긴급점검 등)
    private String inspectionCycle;   // 점검 주기 (월간, 분기, 반기 등)
    private Date scheduledDate;       // 예정일
    private String status;            // 상태 (예정, 진행중, 완료 등)
    private String description;       // 설명
    private Date lastUpdatedDate;     // 최종 업데이트 일자

    @Override
    public String toString() {
        return String.format("\n" +
                        "━━━━━━  📅 \033[1;36m점검 일정 정보\033[0m 📅 ━━━━━\n" +
                        "\n" +
                        "  🔑 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🔧 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🔄 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m\"%s\"\033[0m\n" +
                        "  ⏱️ \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m\"%s\"\033[0m\n" +
                        "  📆 \t\033[1;34m%-10s\033[0m\t: \t\033[1;37m%s\033[0m\n" +
                        "  🚦 \t\033[1;31m%-10s\033[0m\t: \t\033[1;%sm\"%s\"\033[0m\n" +
                        "  📝 \t\033[1;35m%-10s\033[0m\t: \t\033[0;35m\"%s\"\033[0m\n" +
                        "  🕒 \t\033[1;36m%-10s\033[0m\t: \t\033[0;36m%s\033[0m\n" +
                        "\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━\n",
                " 일정 ID", scheduleId,
                " 장비 ID", equipmentId,
                " 점검 유형", inspectionType != null ? inspectionType : "미입력",
                " 점검 주기", inspectionCycle != null ? inspectionCycle : "미입력",
                " 예정일", scheduledDate != null ? scheduledDate.toString() : "미입력",
                " 상태", getScheduleStatusColor(status), status != null ? status : "미입력",
                " 설명", description != null ? description : "미입력",
                " 최종수정일", lastUpdatedDate != null ? lastUpdatedDate.toString() : "미입력");
    }

    // 점검 일정 상태에 따른 색상 코드를 반환하는 메서드
    private String getScheduleStatusColor(String status) {
        if (status == null) return "37"; // 기본 흰색

        switch (status.toUpperCase()) {
            case "예정":
            case "대기중":
                return "36"; // 청록색
            case "진행중":
                return "33"; // 노란색
            case "완료":
                return "32"; // 녹색
            case "취소":
                return "31"; // 빨간색
            default:
                return "37"; // 기본 흰색
        }
    }
}
