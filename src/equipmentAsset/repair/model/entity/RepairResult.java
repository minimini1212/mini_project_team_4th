package equipmentAsset.repair.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 장비 수리 결과 정보를 담는 엔티티 클래스
 */
@Getter
@Setter

public class RepairResult {
    private int resultId;            // 결과 ID
    private int requestId;           // 요청 ID
    private String repairContent;    // 수리 내용
    private int repairCost;          // 수리 비용
    private String result;           // 결과 (수리완료, 수리불가)
    private Date lastUpdatedDate;    // 최종 업데이트 일자
}