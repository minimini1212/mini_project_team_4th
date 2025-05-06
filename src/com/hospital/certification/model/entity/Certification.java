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
    @Override
    public String toString() {
        return String.format(
                "자격증 ID : %d\n자격증명   : %s\n발급기관   : %s\n설명       : %s\n------------------------------",
                certId, certName, issuingOrg, description
        );
    }
}