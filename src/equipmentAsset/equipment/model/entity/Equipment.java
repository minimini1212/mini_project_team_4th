package equipmentAsset.equipment.model.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 장비 마스터 정보를 담는 엔티티 클래스
 */

@Getter
@Setter

public class Equipment {
	private int equipmentId; // 장비 ID
	private String equipmentName; // 장비명
	private String modelName; // 모델명
	private String manufacturer; // 제조사
	private String serialNumber; // 시리얼 번호
	private Date purchaseDate; // 구매일
	private int purchasePrice; // 구매가격
	private int categoryId; // 카테고리 ID
	private int departmentId; // 부서 ID
	private int managerId; // 담당자 ID
	private String status; // 상태 (정상, 점검필요, 수리중, 폐기예정, 폐기완료)
	private String description; // 설명
	private Date lastUpdatedDate; // 최종 업데이트 일자
}
