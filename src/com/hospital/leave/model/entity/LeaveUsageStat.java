package com.hospital.leave.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveUsageStat {
    private Long usageStatId;      // PK
    private Long departmentId;     // FK → DEPARTMENT.department_id
    private int year;
    private int month;             // 1~12
    private double totalLeaveDays;
    private double annualUsed;
    private double sickUsed;
    private double otherUsed;

    @Override
    public String toString() {
        return String.format("\n" +
                        "━━━━━━  📊 \033[1;36m휴가 사용 통계\033[0m 📊 ━━━━━\n" +
                        "\n" +
                        "  🔑 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%d\033[0m\n" +
                        "  🏢 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%d\033[0m\n" +
                        "  📅 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%d년 %d월\033[0m\n" +
                        "  📊 \t\033[1;33m%-10s\033[0m\t: \t\033[1;33m%.1f일\033[0m\n" +
                        "  🌴 \t\033[1;32m%-10s\033[0m\t: \t\033[0;32m%.1f일\033[0m\n" +
                        "  🏥 \t\033[1;31m%-10s\033[0m\t: \t\033[0;31m%.1f일\033[0m\n" +
                        "  🔖 \t\033[1;35m%-10s\033[0m\t: \t\033[0;35m%.1f일\033[0m\n" +
                        "\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━\n",
                " 통계 ID", usageStatId,
                " 부서 ID", departmentId,
                " 기간", year, month,
                " 총휴가일수", totalLeaveDays,
                " 연차", annualUsed,
                " 병가", sickUsed,
                " 기타", otherUsed);
    }
}