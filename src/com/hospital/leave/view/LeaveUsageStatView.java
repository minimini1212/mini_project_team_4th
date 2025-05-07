package com.hospital.leave.view;

import com.hospital.leave.model.entity.LeaveUsageStat;

import java.util.List;

public class LeaveUsageStatView {
    public void showStats(List<LeaveUsageStat> stats) {
        stats.forEach(s -> System.out.println(s.toString()));
    }
}