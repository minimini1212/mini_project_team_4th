package com.hospital.leave.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

/**
 * 휴가 신청 정보
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    private Long      leaveId;
    private String      employeeId;
    private String    leaveType;    // 연차/병가/경조사/대체
    private LocalDate startDate;
    private LocalDate endDate;
    private double    days;         // 소수점 1자리
    private String    reason;
    private String    status;       // REQUEST/APPROVED/REJECTED
    private String      approverId;
    private LocalDate applyDate;

    @Override
    public String toString() {
        // 상태에 따른 색상 코드
        String statusColor = getStatusColor(status);

        return String.format("\n" +
                        "━━━━━━  📋 \033[1;36m휴가 신청 정보\033[0m 📋 ━━━━━\n" +
                        "\n" +
                        "  🔑 \t\033[1;34m%-3s \t\t\033[0m\t: \t\033[0;37m%d\033[0m\n" +
                        "  🏷️ \t\033[1;34m%-3s \t\t\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  📅 \t\033[1;34m%-3s \t\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  📅 \t\033[1;34m%-3s \t\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  ⏱️ \t\033[1;33m%-3s \t\t\033[0m\t: \t\033[1;33m%.1f일\033[0m\n" +
                        "  🚦 \t\033[1;%sm%-3s \t\t\033[0m\t: \t\033[1;%sm%s\033[0m\n" +
                        "\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━\n",
                " ID", leaveId,
                " 종류", leaveType != null ? leaveType : "미입력",
                " 시작일", startDate != null ? startDate.toString() : "미입력",
                " 종료일", endDate != null ? endDate.toString() : "미입력",
                " 일수", days,
                statusColor, " 상태", statusColor, status != null ? status : "미입력");
    }

    // 상태에 따른 색상 코드를 반환하는 메서드
    private String getStatusColor(String status) {
        if (status == null) return "37"; // 기본 흰색

        switch (status.toUpperCase()) {
            case "APPROVED":
                return "32"; // 녹색
            case "REQUEST":
                return "33"; // 노란색
            case "REJECTED":
                return "31"; // 빨간색
            default:
                return "37"; // 기본 흰색
        }
    }
}