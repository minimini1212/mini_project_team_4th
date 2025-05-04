package com.hospital.certification.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certification {
    private Long certId;          // PK
    private String certName;
    private String issuingOrg;
    private String description;
}