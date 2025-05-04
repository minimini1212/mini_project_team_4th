package com.hospital.certification.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

/**
 * 사원별 자격증 매핑 정보
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCertification {
    private Long      empCertId;
    private Long      employeeId;
    private Long      certId;
    private LocalDate acquisitionDate;
    private LocalDate expiryDate;
    private String    certNumber;
    private String    renewalRequired;  // 예: "Y"/"N"
    private LocalDate alertDate;
}