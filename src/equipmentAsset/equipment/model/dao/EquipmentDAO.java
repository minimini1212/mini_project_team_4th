package equipmentAsset.equipment.model.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;

import equipmentAsset.common.util.CloseHelper;
import equipmentAsset.common.util.ConnectionSingletonHelper;
import equipmentAsset.equipment.model.entity.Equipment;
import equipmentAsset.equipment.view.EquipmentView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EquipmentDAO {
	private static final String TABLE_NAME = "EQUIPMENT";
	
	private Equipment equipment = new Equipment();
	private EquipmentView equipmentVIew = new EquipmentView();
	
	// 연결, 삽입, 삭제, 수정, 검색,......
	private Scanner sc = new Scanner(System.in);
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Connection conn = null;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
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
	
	// - ID로 특정 장비 조회
	public void findById (int equipmentId) {
		try {
			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE EQUIPMENT_ID = " + equipmentId);
			equipmentVIew.displayEquipmentResults(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	} // end findById
	
	// - 모든 장비 목록 조회
	public void findAll() {
		try {
			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME);
			equipmentVIew.displayEquipmentResults(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
	} // end findAll
	
	// - 특정 카테고리의 장비 조회
	public void findByCategory(int categoryId) {
		try {
			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE CATEGORY_ID = " + categoryId);
			equipmentVIew.displayEquipmentResults(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	} // end findByCategory
	
	// - 특정 부서의 장비 조회
	public void findByDepartment(int departmentId) {
		try {
			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE DEPARTMENT_ID = " + departmentId);
			equipmentVIew.displayEquipmentResults(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	}
	
	// - 특정 상태(정상, 점검필요, 수리중 등)의 장비 조회
	public void findByStatus(String status) {
		try {
			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE STATUS = '" + status +"'");
			equipmentVIew.displayEquipmentResults(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	}	
	
	/**=-=-=-=-=-=-=-=-=-=-=-=-= 등록 관련 메소드 =-=-=-=-=-=-=-=-=-=-=-=-=**/
	// - 새 장비 정보 저장
	public void save(Equipment equipment) {
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
	public void updateStatus(int equipmentId, String status) {
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
	public void updateManager(int equipmentId, int managerId) {
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
	public void delete(int equipmentId) {
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
			rs = stmt.executeQuery("SELECT STATUS, COUNT(*) FROM EQUIPMENT GROUP BY STATUS ORDER BY COUNT(*) DESC");
			equipmentVIew.countByStatus(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
	}
	// - 카테고리별 장비 개수 집계
	public void countByCategory() {
		try {
			rs = stmt.executeQuery("SELECT CATEGORY_ID, COUNT(*) FROM EQUIPMENT GROUP BY CATEGORY_ID ORDER BY COUNT(*) DESC");
			equipmentVIew.countByCategory(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 			
	}
	// - 부서별 장비 개수 집계
	public void countByDepartment() {
		try {
			rs = stmt.executeQuery("SELECT DEPARTMENT_ID, COUNT(*) FROM EQUIPMENT GROUP BY DEPARTMENT_ID ORDER BY COUNT(*) DESC");
			equipmentVIew.countByDepartment(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	// - 카테고리별 구매 가격 합계
	public void sumPurchasePriceByCategory() {
		try {
			rs = stmt.executeQuery("SELECT CATEGORY_ID, SUM(PURCHASE_PRICE) FROM EQUIPMENT GROUP BY CATEGORY_ID ORDER BY SUM(PURCHASE_PRICE) DESC");
			equipmentVIew.sumPurchasePriceByCategory(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	// - 최근에 업데이트된 장비 목록
	public void getRecentlyUpdatedEquipments() {
		try {
			rs = stmt.executeQuery("SELECT CATEGORY_ID, SUM(PURCHASE_PRICE) FROM EQUIPMENT GROUP BY CATEGORY_ID ORDER BY SUM(PURCHASE_PRICE) DESC");
			equipmentVIew.sumPurchasePriceByCategory(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	
} //end class
