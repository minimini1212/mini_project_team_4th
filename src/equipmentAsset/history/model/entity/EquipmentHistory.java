package equipmentAsset.history.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비 이력 통합 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class EquipmentHistory {
    private int historyId;            // 이력 ID
    private int equipmentId;          // 장비 ID
    private String historyType;       // 이력 유형 (점검, 수리요청, 수리진행, 수리완료, 폐기 등)
    private Date occurrenceDate;      // 발생일
    private String description;       // 설명
    private Integer relatedId;        // 관련 ID (점검결과ID, 수리요청ID 등) (null 가능)
    private Date lastUpdatedDate;     // 최종 업데이트 일자
}
