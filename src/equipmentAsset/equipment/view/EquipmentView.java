package equipmentAsset.equipment.view;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

public class EquipmentView {
	
	public void displayEquipmentResults(ResultSet rs) {
		try {
			System.out.println("장비ID    장비명      모델명          제조사    시리얼번호  "
					+ "       구매일        구매가격       상태");
			while(rs.next()) {
			    int equipmentId = rs.getInt("equipment_id");
			    String equipmentName = rs.getString("equipment_name");
			    String modelName = rs.getString("model_name");
			    String manufacturer = rs.getString("manufacturer");
			    String serialNumber = rs.getString("serial_number");
			    Date purchaseDate = rs.getDate("purchase_date");
			    int purchasePrice = rs.getInt("purchase_price");
			    String status = rs.getString("status");

			    System.out.println(equipmentId + "\t " + equipmentName + "   " + modelName + "   " + 
	                     manufacturer + "   " + serialNumber + "      " + purchaseDate + "  " + 
	                     purchasePrice + "     " + status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //end displayEquipmentResults()
	
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
	
	
	
	
	
	
	
}
