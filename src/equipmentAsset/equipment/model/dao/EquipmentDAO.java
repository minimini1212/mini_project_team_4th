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
import lombok.Getter;


/** =-=-=-=-=-=-=-=-=-=-=-=-=-= equipment.EquipmentDAO Class =-=-=-=-=-=-=-=-=-=-=-=-=-=

	--- 장비조회
	boolean findAllEquipment() : 모든 장비 목록 조회
	boolean findByIdEquipment (int equipmentId) : ID로 특정 장비 조회
	boolean findByCategoryEquipment(int categoryId) : 특정 카테고리의 장비 조회
	boolean findByDepartmentEquipment(int departmentId) : 특정 부서의 장비 조회
	boolean findByStatusEquipment(String status) : 특정 상태(정상, 점검필요, 수리중 등)의 장비 조회
	
	--- 장비등록
	boolean saveEquipment(Equipment equipment) : 새 장비 정보 저장
	
	--- 장비수정
	boolean updateStatusEquipment(int equipmentId, String status) : 장비 상태 수정
	boolean updateManagerEquipment(int equipmentId, int managerId) : 장비 담당자 수정
	
	--- 장비삭제
	boolean deleteEquipment(int equipmentId) : 장비 정보 삭제
	
	--- 집계관련
	boolean countByStatus() : 상태별 장비 개수 집계
	boolean countByCategory() : 카테고리별 장비 개수 집계
	boolean countByDepartment() : 부서별 장비 개수 집계
	boolean sumPurchasePriceByCategory() : 카테고리별 구매 가격 합계
	boolean getRecentlyUpdatedEquipments() : 최근에 업데이트된 장비 목록

	=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= **/

@Getter

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
		public boolean findAllEquipment() {
			try {
				rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL ORDER BY EQUIPMENT_ID");
				equipmentVIew.displayEquipmentResults(rs);
				return true;
			} catch (SQLException e) {
				return false;
			} 		
		} // end findAll
	
	// - ID로 특정 장비 조회	
	public boolean findByIdEquipment (int equipmentId) {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE EQUIPMENT_ID IN (" + equipmentId + ")");
			equipmentVIew.displayEquipmentResults(rs);
			return true;
		} catch (SQLException e) {
			return false;
		} 
	} // end findById
	
	// - 특정 카테고리의 장비 조회
	public boolean findByCategoryEquipment(String categoryName) {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE CATEGORY_NAME IN ('" + categoryName + "')");
			equipmentVIew.displayEquipmentResults(rs);
			return true;
		} catch (SQLException e) {
			return false;
		} 	
	} // end findByCategory
	
	// - 특정 부서의 장비 조회
	public boolean findByDepartmentEquipment(String departmentName) {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE DEPARTMENT_NAME IN ('" + departmentName + "')");
			equipmentVIew.displayEquipmentResults(rs);
			return true;
		} catch (SQLException e) {
			return false;
		} 	
	}
	
	// - 특정 상태(정상, 점검필요, 수리중 등)의 장비 조회
	public boolean findByStatusEquipment(String status) {
		try {
			rs = stmt.executeQuery("SELECT * FROM V_EQUIPMENT_DETAIL WHERE STATUS IN ('" + status + "')");
			equipmentVIew.displayEquipmentResults(rs);
			return true;
		} catch (SQLException e) {
			return false;
		} 	
	}	
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 등록 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	// - 새 장비 정보 저장
	public boolean saveEquipment(Equipment equipment) {
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
	        
	        pstmt.executeUpdate();
	        return true;

		} catch (SQLException e) {
			return false;
		}			
	} // end save
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 수정 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	//- 장비 상태 업데이트 > '정상', '점검필요', '수리중', '폐기예정', '폐기완료' 만 선택가능
	public boolean updateStatusEquipment(int equipmentId, String status) {
		String sql = "UPDATE EQUIPMENT SET STATUS = ?, LAST_UPDATED_DATE = SYSDATE WHERE EQUIPMENT_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setInt(2, equipmentId);
	       
	        pstmt.executeUpdate();
	        return true;

		} catch (SQLException e) {
			return false;
		}		
	} // end updateStatus
	
	//- 장비 담당자 업데이트
	public boolean updateManagerEquipment(int equipmentId, int managerId) {
		String sql = "UPDATE EQUIPMENT SET MANAGER_ID = ?, LAST_UPDATED_DATE = SYSDATE WHERE EQUIPMENT_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, managerId);
			pstmt.setInt(2, equipmentId);
	       
	        pstmt.executeUpdate();
			
	        return true;
		} catch (SQLException e) {
			return false;
		}	
	} // end updateManager
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 삭제 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	//- 장비 정보 삭제
	public boolean deleteEquipment(int equipmentId) {
		String sql = "DELETE FROM EQUIPMENT WHERE EQUIPMENT_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, equipmentId);
	       
	        pstmt.executeUpdate();
	        return true;
	        
		} catch (SQLException e) {
			return false;
		}	
	} // end deleteEquipment
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 집계 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	
	// - 상태별 장비 개수 집계
	public boolean countByStatus() {
		try {
			rs = stmt.executeQuery("SELECT STATUS, COUNT(*) FROM V_EQUIPMENT_DETAIL GROUP BY STATUS ORDER BY COUNT(*) DESC");
			equipmentVIew.countByStatus(rs);
			return true;
		} catch (SQLException e) {
			return false;
		} 	
	}
	// - 카테고리별 장비 개수 집계
	public boolean countByCategory() {
		try {
			rs = stmt.executeQuery("SELECT CATEGORY_NAME, COUNT(*) FROM V_EQUIPMENT_DETAIL GROUP BY CATEGORY_NAME ORDER BY COUNT(*) DESC;");
			equipmentVIew.countByCategory(rs);
			return true;
		} catch (SQLException e) {
			return false;
		} 			
	}
	// - 부서별 장비 개수 집계
	public boolean countByDepartment() {
		try {
			rs = stmt.executeQuery("SELECT DEPARTMENT_NAME, COUNT(*) FROM V_EQUIPMENT_DETAIL GROUP BY DEPARTMENT_NAME ORDER BY COUNT(*) DESC");
			equipmentVIew.countByDepartment(rs);
			return true;
		} catch (SQLException e) {
			return false;
		} 
	}
	// - 카테고리별 구매 가격 합계
	public boolean sumPurchasePriceByCategory() {
		try {
			rs = stmt.executeQuery("SELECT CATEGORY_NAME, SUM(PURCHASE_PRICE) FROM V_EQUIPMENT_DETAIL GROUP BY CATEGORY_NAME ORDER BY SUM(PURCHASE_PRICE) DESC");
			equipmentVIew.sumPurchasePriceByCategory(rs);
			return true;
		} catch (SQLException e) {
			return false;
		} 
	}
	// - 최근에 업데이트된 장비 목록
	public boolean getRecentlyUpdatedEquipments() {
		try {
			rs = stmt.executeQuery("SELECT * FROM (SELECT EQUIPMENT_ID, EQUIPMENT_NAME, STATUS, LAST_UPDATED_DATE"
									+ " FROM EQUIPMENT ORDER BY LAST_UPDATED_DATE DESC) WHERE ROWNUM <=5");
			equipmentVIew.getRecentlyUpdatedEquipments(rs);
			return true;
		} catch (SQLException e) {
			return false;
		} 
	}
} //end class
