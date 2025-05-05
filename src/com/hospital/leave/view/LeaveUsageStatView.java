package com.hospital.leave.view;

import com.hospital.leave.model.entity.LeaveUsageStat;

import java.util.List;

public class LeaveUsageStatView {
    public void showStats(List<LeaveUsageStat> stats) {
        System.out.println("부서ID | 연 | 월 | 총휴가일수 | 연차 | 병가 | 기타");
        stats.forEach(s -> System.out.printf(
                "%d | %d | %d | %.1f | %.1f | %.1f | %.1f\n",
                s.getDepartmentId(),
                s.getYear(), s.getMonth(),
                s.getTotalLeaveDays(),
                s.getAnnualUsed(),
                s.getSickUsed(),
                s.getOtherUsed()
        ));
    }
}