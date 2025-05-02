package equipmentAsset.equipment.view;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

public class EquipmentView {
	
	// - 장비 정보 출력
	public void displayEquipmentResults(ResultSet rs) {
		try {
		    System.out.printf("%-7s %-25s\t%-20s\t%-18s\t%-17s\t%-12s\t%-12s\t%-10s\t%-15s\t%-15s\t%-15s\t%-15s\n",
		        "장비ID", "장비명", "모델명", "제조사", "시리얼번호", "구매일", "구매가격", "상태", "담당자", "부서", "직급", "직무");
		    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		    while(rs.next()) {
		        int equipmentId = rs.getInt("equipment_id");
		        String categoryName = rs.getString("category_name");
		        String equipmentName = rs.getString("equipment_name");
		        String modelName = rs.getString("model_name");
		        String manufacturer = rs.getString("manufacturer");
		        String serialNumber = rs.getString("serial_number");
		        Date purchaseDate = rs.getDate("purchase_date");
		        int purchasePrice = rs.getInt("purchase_price");
		        String status = rs.getString("status");
		        String managerName = rs.getString("manager_name");
		        String departmentName = rs.getString("department_name");
		        String positionName = rs.getString("position_name");
		        String jobName = rs.getString("job_name");

		        System.out.printf("%-7d %-25s\t%-20s\t%-18s\t%-17s\t%-8s\t%6d만원\t%-10s\t%-15s\t%-15s\t%-15s\t%-15s\n",
		            equipmentId,
		            equipmentName,
		            modelName,
		            manufacturer,
		            serialNumber,
		            purchaseDate,
		            purchasePrice / 10000,
		            status,
		            managerName,
		            departmentName,
		            positionName,
		            jobName);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	} //end displayEquipmentResults()
	
	
	// - 상태별 장비 개수 출력
	public void countByStatus(ResultSet rs) {
		try {
			System.out.println("상태\t  개수");
			System.out.println("-------------");
			while(rs.next()) {	    
			    String status = rs.getString("status");
			    int count = rs.getInt("count(*)");
			    System.out.printf("%s\t  %d개\n", status, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //end countByStatus
	
	
	// - 카테고리별 장비 개수 출력
	public void countByCategory(ResultSet rs) {
		try {
			System.out.println("카테고리\t  개수");
			System.out.println("-------------");
			while(rs.next()) {	    
			    String category = rs.getString("CATEGORY_ID");
			    int count = rs.getInt("count(*)");
			    System.out.printf("%s\t  %d개\n", category, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //end countByCategory
	
	
	// - 부서별 장비 개수 출력
	public void countByDepartment(ResultSet rs) {
		try {
			System.out.println("부서이름\t  개수");
			System.out.println("-------------");
			while(rs.next()) {	    
			    String departmentId = rs.getString("department_id");
			    int count = rs.getInt("count(*)");
			    System.out.printf("%s\t  %d개\n", departmentId, count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //end countByDepartment
	
	// - 카테고리별 장비 금액 합계
	public void sumPurchasePriceByCategory(ResultSet rs) {
		try {
			System.out.println("카테고리\t     가격");
			System.out.println("----------------");
			while(rs.next()) {	    
			    String category = rs.getString("CATEGORY_ID");
			    int sum = rs.getInt("SUM(PURCHASE_PRICE)");
			    System.out.printf("%s\t%5d만원\n", category, sum / 10000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //end sumPurchasePriceByCategory
	
	// - 최근 수정된 장비 5개 정보 출력
	public void getRecentlyUpdatedEquipments(ResultSet rs) {
		try {
			System.out.println("장비ID\t이름\t               담당자\t상태\t   최종수정일");
			System.out.println("---------------------------------------------------------------");
			while(rs.next()) {	    
				int equipmentId = rs.getInt("equipment_id");
			    String equipmentName = rs.getString("equipment_name");
			    int manager = rs.getInt("manager_id");
			    String status = rs.getString("status");
			    Date lastUpdatedDate = rs.getDate("last_updated_date");
			    
			    System.out.printf("%-7d\t%-15s\t%d\t%-5s\t   %s\n", equipmentId, equipmentName, manager, status, lastUpdatedDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //end getRecentlyUpdatedEquipments
	
	// - 모든 카테고리 출력
	public void findAllCategories(ResultSet rs) {
		try {
			System.out.println("카테고리번호     카테고리이름\t        코드");
			System.out.println("-----------------------------------");
			while(rs.next()) {	    
				int categoryId = rs.getInt("CATEGORY_ID");
			    String categoryName = rs.getString("CATEGORY_NAME");
			    String categoryCode = rs.getString("CATEGORY_CODE");
			    
			    System.out.printf("%-5d\t      %-10s\t%s\n", categoryId, categoryName, categoryCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //end findAllCategories	
	
}
