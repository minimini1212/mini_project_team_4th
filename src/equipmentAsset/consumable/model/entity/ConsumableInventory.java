package equipmentAsset.consumable.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 소모품 재고 현황 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class ConsumableInventory {
    private int inventoryId;         // 재고 ID
    private int consumableId;        // 소모품 ID
    private int currentQuantity;     // 현재 수량
    private String storageLocation;  // 보관 위치
    private Date lastUpdatedDate;    // 최종 업데이트 일자
}
