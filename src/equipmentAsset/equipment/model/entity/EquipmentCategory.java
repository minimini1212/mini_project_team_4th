package equipmentAsset.equipment.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비 분류 카테고리 정보를 담는 엔티티 클래스
 */

@Getter
@Setter

public class EquipmentCategory {
    private int categoryId;        // 카테고리 ID
    private String categoryName;   // 카테고리명 (의료영상장비, 환자모니터링장비 등)
    private String categoryCode;   // 카테고리 코드

    @Override
    public String toString() {
        return String.format("\n" +
                        "━━━━━  📁 \033[1;36m장비 카테고리 정보\033[0m 📁 ━━━━\n" +
                        "\n" +
                        "  🔑 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  📋 \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "  🏷️ \t\033[1;34m%-10s\033[0m\t: \t\033[0;37m%s\033[0m\n" +
                        "\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━\n",
                " 카테고리 ID", categoryId,
                " 카테고리명", categoryName != null ? categoryName : "미입력",
                " 카테고리 코드", categoryCode != null ? categoryCode : "미입력");
    }
}