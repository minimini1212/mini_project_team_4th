package equipmentAsset.repair.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비 수리 요청 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class RepairRequest {
    private int requestId;           // 요청 ID
    private int equipmentId;         // 장비 ID
    private Date requestDate;        // 요청일
    private String failureSymptom;   // 고장 증상
    private String status;           // 상태 (접수, 수리중, 완료, 폐기결정 등)
    private Date lastUpdatedDate;    // 최종 업데이트 일자
}
