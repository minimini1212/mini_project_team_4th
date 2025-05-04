package com.hospital.leave.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leave {
    private Long leaveId;          // PK
    private Long employeeId;       // FK → EMPLOYEE.employee_id
    private String leaveType;      // 연차/병가/경조사/대체
    private LocalDate startDate;
    private LocalDate endDate;
    private double days;           // 소수점 1자리
    private String reason;
    private String status;         // REQUEST/APPROVED/REJECTED
    private Long approverId;       // FK → EMPLOYEE.employee_id
    private LocalDate applyDate;
}