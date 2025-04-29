package equipmentAsset.repair.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비 수리 비용 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class RepairCost {
    private int costId;              // 비용 ID
    private int workId;              // 수리 작업 ID
    private double amount;           // 비용 금액
    private String paymentStatus;    // 지급 상태
    private Date lastUpdatedDate;    // 최종 업데이트 일자
}