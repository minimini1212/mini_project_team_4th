package equipmentAsset.consumable.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 소모품 분류 카테고리 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class ConsumableCategory {
    private int categoryId;          // 카테고리 ID
    private String categoryName;     // 카테고리명
    private String categoryCode;     // 카테고리 코드
    private String description;      // 설명
    private Date lastUpdatedDate;    // 최종 업데이트 일자
}
