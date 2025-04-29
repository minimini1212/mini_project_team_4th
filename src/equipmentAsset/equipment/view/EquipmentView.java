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
	} //end findById()
	
// 필요없을듯 ? 위에껄로 출력가능해서. 일단은 냅둔다.	
//	public void findAll(ResultSet rs, ResultSetMetaData rsmd) {
//		try {
//			int count = rsmd.getColumnCount();
//			while (rs.next()) {
//				for (int i = 1; i <= count; i++) { // 각 타입별로 출력하기
//
//					switch (rsmd.getColumnType(i)) {
//					case Types.NUMERIC:
//					case Types.INTEGER:
//						System.out.println(rsmd.getColumnName(i) + " : " + rs.getInt(i) + "  ");
//						break;
//					case Types.FLOAT:
//						System.out.println(rsmd.getColumnName(i) + " : " + rs.getFloat(i) + "  ");
//						break;
//
//					case Types.DOUBLE:
//						System.out.println(rsmd.getColumnName(i) + " : " + rs.getDouble(i) + "  ");
//						break;
//
//					case Types.CHAR:
//						System.out.println(rsmd.getColumnName(i) + " : " + rs.getString(i) + "  ");
//						break;
//
//					case Types.DATE:
//						System.out.println(rsmd.getColumnName(i) + " : " + rs.getDate(i) + "  ");
//						break;
//
//					default:
//						System.out.println(rsmd.getColumnName(i) + " : " + rs.getString(i) + "  ");
//						break;
//					} // end switch
//				} // end for
//				System.out.println();
//			} // end while
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	} //emd findAll
}
