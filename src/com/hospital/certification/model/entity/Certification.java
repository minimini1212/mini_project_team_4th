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
        return String.format("\n" +
                        "━━━━━━  📜 \033[1;36m자격증 상세 정보\033[0m 📜 ━━━━\n" +
                        "\n" +
                        "  🔑 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%d\033[0m\n" +
                        "  📝 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🏢 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  📄 \t\033[1;35m%-10s\033[0m\t: \t\033[0;35m\"%s\"\033[0m\n" +
                        "\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━\n",
                " 자격증 ID", certId,
                " 자격증명", certName != null ? certName : "미입력",
                " 발급기관", issuingOrg != null ? issuingOrg : "미입력",
                " 설명", description != null ? description : "미입력");
    }
}