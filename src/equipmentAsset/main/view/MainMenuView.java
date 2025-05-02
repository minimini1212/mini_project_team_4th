package equipmentAsset.main.view;
import equipmentAsset.equipment.model.dao.CategoryDAO;
import equipmentAsset.equipment.model.dao.EquipmentDAO;

public class MainMenuView {
	public static void main(String[] args) {
		EquipmentDAO dao = new EquipmentDAO();
		dao.connect();
		
		CategoryDAO dao2 = new CategoryDAO();
		dao2.connect();
		
		//dao.findAllEquipment();           //완료
		//dao.findByIdEquipment(1);        //완료
		//dao.findByCategoryEquipment("수술장비");   //완료
		//dao.findByDepartmentEquipment("장비팀"); //완료
		//dao.findByStatusEquipment("정상"); //완료
		////=========여기까지 조회 메소드 테스트 완료=========////

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
		////=========여기까지 등록 메소드 테스트 완료=========////
		
		//dao.updateStatus(11, "정상");
		//dao.updateManager(11, 110);
		////=========여기까지 수정 메소드 테스트 완료=========////
		
		//dao.delete(11);	
		////=========여기까지 삭제 메소드 테스트 완료=========////
		
		//dao.countByStatus();
		//dao.countByCategory();
		//dao.countByDepartment();
		//dao.sumPurchasePriceByCategory();
		//dao.getRecentlyUpdatedEquipments();
		////=========여기까지 집계 메소드 테스트 완료=========////
		
//		EquipmentCategory testCategory = new EquipmentCategory();
//		testCategory.setCategoryId(6);
//		testCategory.setCategoryName("테스트장비");
//		testCategory.setCategoryCode("TEST2");
		
		//dao2.saveCategory(testCategory);
		//dao2.deleteCategory(6);
		//dao2.findAllCategories();
		////========여기까지 카테고리 관련 테스트 완료========////
		
		dao.close();
		dao2.close();
	}
}
