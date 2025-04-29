package equipmentAsset.consumable.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 소모품/부품 기본 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class ConsumablePart {
    private int consumableId;        // 소모품 ID
    private String consumableName;   // 소모품명
    private String manufacturer;     // 제조사
    private String modelNumber;      // 모델 번호
    private String category;         // 카테고리
    private String unit;             // 단위 (개, 팩, 병 등)
    private String description;      // 설명
    private Date lastUpdatedDate;    // 최종 업데이트 일자
}
