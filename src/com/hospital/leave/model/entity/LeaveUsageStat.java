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
}