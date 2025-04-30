package equipmentAsset.history.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비와 소모품 간의 연결 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class EquipmentConsumableLink {
    private int linkId;              // 연결 ID
    private int equipmentId;         // 장비 ID
    private int consumableId;        // 소모품 ID
    private int quantityRequired;    // 필요 수량
    private Date lastUpdatedDate;    // 최종 업데이트 일자
}
