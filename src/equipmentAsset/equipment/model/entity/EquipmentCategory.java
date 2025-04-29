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
}