package equipmentAsset.equipment.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import equipmentAsset.common.util.CloseHelper;
import equipmentAsset.common.util.ConnectionSingletonHelper;
import equipmentAsset.equipment.model.entity.Equipment;
import equipmentAsset.equipment.view.EquipmentView;


/** =-=-=-=-=-=-=-=-=-=-=-=-=-= equipment.EquipmentDAO Class =-=-=-=-=-=-=-=-=-=-=-=-=-=

	--- 장비조회
	void findByIdEquipment (int equipmentId) : ID로 특정 장비 조회
	void findAllEquipment() : 모든 장비 목록 조회
	void findByCategoryEquipment(int categoryId) : 특정 카테고리의 장비 조회
	void findByDepartmentEquipment(int departmentId) : 특정 부서의 장비 조회
	void findByStatusEquipment(String status) : 특정 상태(정상, 점검필요, 수리중 등)의 장비 조회
	
	--- 장비등록
	void saveEquipment(Equipment equipment) : 새 장비 정보 저장
	
	--- 장비수정
	void updateStatusEquipment(int equipmentId, String status) : 장비 상태 수정
	void updateManagerEquipment(int equipmentId, int managerId) : 장비 담당자 수정
	
	--- 장비삭제
	void deleteEquipment(int equipmentId) : 장비 정보 삭제
	
	--- 집계관련
	void countByStatus() : 상태별 장비 개수 집계
	void countByCategory() : 카테고리별 장비 개수 집계
	void countByDepartment() : 부서별 장비 개수 집계
	void sumPurchasePriceByCategory() : 카테고리별 구매 가격 합계
	void getRecentlyUpdatedEquipments() : 최근에 업데이트된 장비 목록

	=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= **/

public class EquipmentDAO {
	private final String TABLE_NAME = "EQUIPMENT";
	
	private EquipmentView equipmentVIew = new EquipmentView();
	
	
	/** =-=-=-=-=-=-=-=-=-=-=-=-= 연결 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-= **/
	// 연결, 삽입, 삭제, 수정, 검색,......
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Connection conn = null;
	
	// connect
	public void connect() {
		try {
			conn = ConnectionSingletonHelper.getConnection("oracle");
			stmt = conn.createStatement();
			conn.setAutoCommit(false); // 자동커밋 끄기
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end connect()
	
	// close
	public void close() {
		try {
			CloseHelper.close(rs);
			CloseHelper.close(stmt);
			CloseHelper.close(pstmt);
			CloseHelper.close(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //end close
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 조회 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	// - 모든 장비 목록 조회
		public void findAllEquipment() {
			try {
				rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL ORDER BY EQUIPMENT_ID");
				equipmentVIew.displayEquipmentResults(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} 		
		} // end findAll
	
	// - ID로 특정 장비 조회	
	public void findByIdEquipment (int equipmentId) {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE EQUIPMENT_ID IN (" + equipmentId + ")");
			equipmentVIew.displayEquipmentResults(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	} // end findById
	
	// - 특정 카테고리의 장비 조회
	public void findByCategoryEquipment(String categoryName) {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE CATEGORY_NAME IN ('" + categoryName + "')");
			equipmentVIew.displayEquipmentResults(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	} // end findByCategory
	
	// - 특정 부서의 장비 조회
	public void findByDepartmentEquipment(String departmentName) {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE DEPARTMENT_NAME IN ('" + departmentName + "')");
			equipmentVIew.displayEquipmentResults(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	}
	
	// - 특정 상태(정상, 점검필요, 수리중 등)의 장비 조회
	public void findByStatusEquipment(String status) {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE STATUS IN ('" + status + "')");
			equipmentVIew.displayEquipmentResults(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	}	
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 등록 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	// - 새 장비 정보 저장
	public void saveEquipment(Equipment equipment) {
		String sql = "INSERT INTO EQUIPMENT VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, equipment.getEquipmentId());
	        pstmt.setString(2, equipment.getEquipmentName());
	        pstmt.setString(3, equipment.getModelName());
	        pstmt.setString(4, equipment.getManufacturer());
	        pstmt.setString(5, equipment.getSerialNumber());
	        pstmt.setDate(6, new java.sql.Date(equipment.getPurchaseDate().getTime()));
	        pstmt.setDouble(7, equipment.getPurchasePrice());
	        pstmt.setInt(8, equipment.getCategoryId());
	        pstmt.setInt(9, equipment.getDepartmentId());
	        pstmt.setInt(10, equipment.getManagerId());
	        pstmt.setString(11, equipment.getStatus());
	        pstmt.setString(12, equipment.getDescription());
	        pstmt.setDate(13, new java.sql.Date(equipment.getLastUpdatedDate().getTime()));
	        
	        //밑에 나중에 삭제할거임
	        int result = pstmt.executeUpdate();
			System.out.println(result + " 개 데이터 추가 성공!");

		} catch (SQLException e) {
			e.printStackTrace();
		}			
	} // end save
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 수정 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	//- 장비 상태 업데이트 > '정상', '점검필요', '수리중', '폐기예정', '폐기완료' 만 선택가능
	public void updateStatusEquipment(int equipmentId, String status) {
		String sql = "UPDATE EQUIPMENT SET STATUS = ?, LAST_UPDATED_DATE = SYSDATE WHERE EQUIPMENT_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setInt(2, equipmentId);
	       
	        //밑에 나중에 삭제할거임
	        int result = pstmt.executeUpdate();
			System.out.println(result + " 개 데이터 수정 성공!");

		} catch (SQLException e) {
			e.printStackTrace();
		}		
	} // end updateStatus
	
	//- 장비 담당자 업데이트
	public void updateManagerEquipment(int equipmentId, int managerId) {
		String sql = "UPDATE EQUIPMENT SET MANAGER_ID = ?, LAST_UPDATED_DATE = SYSDATE WHERE EQUIPMENT_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, managerId);
			pstmt.setInt(2, equipmentId);
	       
	        //밑에 나중에 삭제할거임
	        int result = pstmt.executeUpdate();
			System.out.println(result + " 개 데이터 수정 성공!");

		} catch (SQLException e) {
			e.printStackTrace();
		}	
	} // end updateManager
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 삭제 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	//- 장비 정보 삭제
	public void deleteEquipment(int equipmentId) {
		String sql = "DELETE FROM EQUIPMENT WHERE EQUIPMENT_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, equipmentId);
	       
	        //밑에 나중에 삭제할거임
	        int result = pstmt.executeUpdate();
			System.out.println(result + " 개 데이터 삭제 성공!");

		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 집계 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	// - 상태별 장비 개수 집계
	public void countByStatus() {
		try {
			rs = stmt.executeQuery("SELECT STATUS, COUNT(*) FROM V_EQUIPMENT_DETAIL GROUP BY STATUS ORDER BY COUNT(*) DESC");
			equipmentVIew.countByStatus(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	}
	// - 카테고리별 장비 개수 집계
	public void countByCategory() {
		try {
			rs = stmt.executeQuery("SELECT CATEGORY_NAME, COUNT(*) FROM V_EQUIPMENT_DETAIL GROUP BY CATEGORY_NAME ORDER BY COUNT(*) DESC;");
			equipmentVIew.countByCategory(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 			
	}
	// - 부서별 장비 개수 집계
	public void countByDepartment() {
		try {
			rs = stmt.executeQuery("SELECT DEPARTMENT_NAME, COUNT(*) FROM V_EQUIPMENT_DETAIL GROUP BY DEPARTMENT_NAME ORDER BY COUNT(*) DESC");
			equipmentVIew.countByDepartment(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	// - 카테고리별 구매 가격 합계
	public void sumPurchasePriceByCategory() {
		try {
			rs = stmt.executeQuery("SELECT CATEGORY_NAME, SUM(PURCHASE_PRICE) FROM V_EQUIPMENT_DETAIL GROUP BY CATEGORY_NAME ORDER BY SUM(PURCHASE_PRICE) DESC");
			equipmentVIew.sumPurchasePriceByCategory(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	// - 최근에 업데이트된 장비 목록
	public void getRecentlyUpdatedEquipments() {
		try {
			rs = stmt.executeQuery("SELECT * FROM (SELECT EQUIPMENT_ID, EQUIPMENT_NAME, STATUS, LAST_UPDATED_DATE"
									+ " FROM EQUIPMENT ORDER BY LAST_UPDATED_DATE DESC) WHERE ROWNUM <=5");
			equipmentVIew.getRecentlyUpdatedEquipments(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
} //end class
