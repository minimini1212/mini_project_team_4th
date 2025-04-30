package equipmentAsset.consumable.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 소모품 사용 이력 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class ConsumableUsage {
    private int usageId;             // 사용 ID
    private int consumableId;        // 소모품 ID
    private int equipmentId;         // 장비 ID
    private int quantityUsed;        // 사용 수량
    private Date usageDate;          // 사용일
    private String usagePurpose;     // 사용 목적
    private Integer relatedWorkId;   // 관련 작업 ID (null 가능)
    private Date lastUpdatedDate;    // 최종 업데이트 일자
}
