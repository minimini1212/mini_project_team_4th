package com.hospital.certification.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCertification {
    private Long empCertId;       // PK
    private Long employeeId;      // FK → EMPLOYEE.employee_id
    private Long certId;          // FK → CERTIFICATION.cert_id
    private LocalDate acquisitionDate;
    private LocalDate expiryDate;
    private String certNumber;
    private boolean renewalRequired;
    private LocalDate alertDate;  // expiryDate.minusDays(30)
}