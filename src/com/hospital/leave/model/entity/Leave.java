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
}