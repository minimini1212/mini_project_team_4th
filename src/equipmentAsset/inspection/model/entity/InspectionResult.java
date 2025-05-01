package equipmentAsset.inspection.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비 점검 결과 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class InspectionResult {
    private int resultId;                // 결과 ID
    private int scheduleId;              // 점검 일정 ID
    private Date inspectionDate;         // 점검 실시일
    private String inspectionResult;     // 점검 결과 (양호, 조치필요 등)
    private String inspectionContent;    // 점검 내용
    private String specialNote;          // 특이사항
    private int actualDuration;          // 실제 소요 시간
    private String followUpRequired;     // 후속 조치 필요 여부 (Y/N)
    private Date lastUpdatedDate;        // 최종 업데이트 일자
}
