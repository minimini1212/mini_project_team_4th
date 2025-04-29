package equipmentAsset.inspection.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비 점검 일정 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class InspectionSchedule {
    private int scheduleId;           // 일정 ID
    private int equipmentId;          // 장비 ID
    private String inspectionType;    // 점검 유형 (정기점검, 긴급점검 등)
    private String inspectionCycle;   // 점검 주기 (월간, 분기, 반기 등)
    private Date scheduledDate;       // 예정일
    private int estimatedDuration;    // 예상 소요 시간
    private double estimatedCost;     // 예상 비용
    private String status;            // 상태 (예정, 진행중, 완료 등)
    private String description;       // 설명
    private Date lastUpdatedDate;     // 최종 업데이트 일자
}
