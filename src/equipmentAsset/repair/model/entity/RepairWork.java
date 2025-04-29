package equipmentAsset.repair.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비 수리 작업 정보를 담는 엔티티 클래스
 */
@Getter
@Setter
public class RepairWork {
    private int workId;              // 작업 ID
    private int requestId;           // 수리 요청 ID
    private Integer vendorId;        // 수리 업체 ID (null 가능)
    private String repairType;       // 수리 유형 (내부수리, 업체수리 등)
    private Date startDate;          // 시작일
    private Date completionDate;     // 완료일
    private String repairContent;    // 수리 내용
    private String result;           // 결과 (진행중, 완료, 폐기 등)
    private Date lastUpdatedDate;    // 최종 업데이트 일자
}
