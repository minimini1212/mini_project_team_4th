package equipmentAsset.inspection.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비 점검 비용 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class InspectionCost {
    private int costId;              // 비용 ID
    private int resultId;            // 점검 결과 ID
    private double amount;           // 비용 금액
    private String paymentStatus;    // 지급 상태 (지급예정, 지급완료 등)
    private Date lastUpdatedDate;    // 최종 업데이트 일자
}
