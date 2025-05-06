package com.hospital.certification.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 자격증 기본 정보
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certification {
    private Long   certId;
    private String certName;
    private String issuingOrg;
    private String description;
}