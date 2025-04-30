package equipmentAsset.main.view;
import java.util.Date;

import equipmentAsset.equipment.model.dao.EquipmentDAO;
import equipmentAsset.equipment.model.entity.Equipment;

public class MainMenuView {
	public static void main(String[] args) {
		EquipmentDAO dao = new EquipmentDAO();
		dao.connect();
		
		//dao.findAll();             //완료
		//dao.findById(10);        //완료
		//dao.findByCategory(1);   //완료
		//dao.findByDepartment(5); //완료
		//dao.findByStatus("정상"); //완료
		
		////=========여기까지 조회 테스트 완료=========////
//		Equipment testEquipment = new Equipment();
//		Date currentDate = new Date();
//		
//	    testEquipment.setEquipmentId(11);  // 새로운 ID 설정 (기존 ID와 겹치지 않도록)
//	    testEquipment.setEquipmentName("테스트 초음파 장비");
//	    testEquipment.setModelName("SonoTest T9");
//	    testEquipment.setManufacturer("Samsung Medison");
//	    testEquipment.setSerialNumber("SM-T9-99999");
//	    testEquipment.setPurchaseDate(currentDate);  // 현재 날짜를 구매일로 설정
//	    testEquipment.setPurchasePrice(52000000);
//	    testEquipment.setCategoryId(1);  // 의료영상장비 카테고리(1)
//	    testEquipment.setDepartmentId(3);  // 영상의학과(3)
//	    testEquipment.setManagerId(105);  // 정과장(105)
//	    testEquipment.setStatus("정상");
//	    testEquipment.setDescription("테스트용 초음파 장비");
//	    testEquipment.setLastUpdatedDate(currentDate);
		
	    //dao.save(testEquipment);  //완료
	    
		////=========여기까지 등록 테스트 완료=========////
		//dao.updateStatus(11, "정상");
		//dao.updateManager(11, 110);
		
		////=========여기까지 수정 테스트 완료=========////
		//dao.delete(11);
		
		////=========여기까지 삭제 테스트 완료=========////
		
		//dao.countByStatus();
		//dao.countByCategory();
		//dao.countByDepartment();
		dao.sumPurchasePriceByCategory();
		
		dao.close();
	}
}
