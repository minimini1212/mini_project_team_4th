package equipmentAsset.purchase.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 소모품 발주 요청 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class PurchaseRequest {
    private int requestId;              // 요청 ID
    private int consumableId;           // 소모품 ID
    private int requestedQuantity;      // 요청 수량
    private Date requestDate;           // 요청일
    private String requestReason;       // 요청 사유
    private String status;              // 상태 (신청, 승인 등)
    private Date lastUpdatedDate;       // 최종 업데이트 일자
}